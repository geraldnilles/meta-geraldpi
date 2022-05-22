#!/bin/sh

# Mount the required system folders
mount -t proc proc /proc
mount -t sysfs sysfs /sys

# Generate a tmpfs to hold all the new mountpoints
mount -t tmpfs inittemp /mnt
cd /mnt

# Enable the OverlayFS
modprobe overlay

# Generate new Mountpoint directories
mkdir /mnt/squash_part
mkdir /mnt/nvol_part
mkdir /mnt/newroot

# Mount the base OS Squash Partition 
mount /dev/mmcblk0p2 /mnt/squash_part

# Setup a non-volitile overlay partition
mount /dev/mmcblk0p3 /mnt/nvol_part
mkdir -p /mnt/nvol_part/upper
mkdir -p /mnt/nvol_part/work

# Setup a volitile overlay partition for Read-only boots
mkdir /mnt/vol_part
mount -t tmpfs volpart /mnt/vol_part
mkdir -p /mnt/vol_part/upper
mkdir -p /mnt/vol_part/work


# Mount the Boot Partition to check for a readonly file
mount /dev/mmcblk0p1 /boot

# Adjust the overlay directories depending on the mount type...
# THis is hardcoded for now, later, i will look for a boot flag or some other indicator
if [[ -f /boot/readonly ]]
then
	# Read-Only Mode - any changes will not persist when the device reboots
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

