#!/bin/sh

# Mount the required system folders
mount -t proc proc /proc
mount -t sysfs sysfs /sys

# Generate a tmpfs to hold all the new mountpoints
mount -t tmpfs inittemp /mnt
cd /mnt

# Generate new Mountpoint directories
mkdir /mnt/squash_part
mkdir /mnt/nvol_part
mkdir /mnt/vol_part
mkdir /mnt/newroot


# Setup a non-volitile overlay partition
mount /dev/mmcblk0p3 /mnt/nvol_part
mkdir -p /mnt/nvol_part/upper
mkdir -p /mnt/nvol_part/work

# Setup a volitile overlay partition for Read-only boots
mount -t tmpfs volpart /mnt/vol_part
mkdir -p /mnt/vol_part/upper
mkdir -p /mnt/vol_part/work


# Mount the Boot Partition to check for a readonly file
mount /dev/mmcblk0p1 /boot

# Mount the base OS Squash Partition 
if [[ -f /boot/SYSTEM.img ]]
then
	# Copy SquashFS Image to RAM before mounting
	cp /boot/SYSTEM.img /mnt/vol_part/
	sync
	mount /mnt/vol_part/SYSTEM.img /mnt/squash_part
else
	# Otherwise, mount the SD partition image
	mount /dev/mmcblk0p2 /mnt/squash_part
fi

# Adjust the overlay directories depending on the mount type...
# THis is hardcoded for now, later, i will look for a boot flag or some other indicator
if [[ -f /boot/readonly ]]
then
	# Read-Only Mode - any changes will not persist when the device reboots

	# Unmount and Remount wtih RO mode
	sync
	umount /mnt/nvol_part
	mount -o ro /dev/mmcblk0p3 /mnt/nvol_part
	LOWERDIR=/mnt/nvol_part/upper:/mnt/squash_part
	UPPERDIR=/mnt/vol_part/upper
	WORKDIR=/mnt/vol_part/work
else
	# RW Mode - Any changes will be saved in the NVol-partition and persist
	# after reboot
	LOWERDIR=/mnt/squash_part
	UPPERDIR=/mnt/nvol_part/upper
	WORKDIR=/mnt/nvol_part/work
	
fi

# Unmount the boot partition since we no longer need it
umount /boot

mount -t overlay -o lowerdir=$LOWERDIR,upperdir=$UPPERDIR,workdir=$WORKDIR overlayfs-root /mnt/newroot


cd /mnt/newroot

# Get the Hostname from the Read-only Rootfs
HOSTNAME=$( cat /etc/hostname )

# Get the serial number of the SoC
SERIAL=$( cat /sys/firmware/devicetree/base/serial-number )

# Grab the last 8 digits of the serial and add a dash to be beginning
SUFFIX="-${SERIAL: -8}"

# Set a new Hostname for the newroot dir
echo "$HOSTNAME$SUFFIX" > etc/hostname

# Make mountpoints for the underlying drive
mkdir -p os
mkdir -p nvol
mkdir -p vol

pivot_root . mnt
exec chroot . sh -c "$(cat <<END
# move ro and rw mounts to the new root
mount --move /mnt/mnt/squash_part /os
mount --move /mnt/mnt/nvol_part /nvol
mount --move /mnt/mnt/vol_part /vol
# unmount unneeded mounts so we can unmout the old readonly root
umount /mnt/sys
umount /mnt/dev
umount /mnt/proc
umount /mnt/run
umount /mnt/mnt
umount /mnt
# continue with regular init
exec /sbin/init
END
)"

