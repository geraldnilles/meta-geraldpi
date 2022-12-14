#!/bin/sh

# Mount the required system folders
mount -t proc proc /proc
mount -t sysfs sysfs /sys
mount -t devtmpfs devtmpfs /dev

# Wait a few seconds for everything to load-up
sleep 5

# Generate a tmpfs to hold all the new mountpoints
mount -t tmpfs inittemp /mnt

mkdir -p /mnt/flash
mkdir -p /mnt/ram
mkdir -p /mnt/boot
mkdir -p /mnt/root

echo "Fixing any disk errors before boot" > /dev/kmsg
fsck.vfat /dev/mmcblk0p1 -p
sleep 1
mount /dev/mmcblk0p1 /mnt/boot
sleep 1
fsck.ext4 /mnt/boot/SYSTEM.img -p
sleep 1
umount /mnt/boot

echo "Mouting the system for booting" > /dev/kmsg
# Mout the main boot partition (this will be the only partition on the SD card
# TODO Investigte how robust Fat32 is to unexpected shutdowns
mount -o ro /dev/mmcblk0p1 /mnt/boot

# TODO Look for SYSTEM.new and overwrite SYSTEM.img

if [[ -f /mnt/boot/readonly ]]
then
	echo "Mouting in READONLY mode" > /dev/kmsg

	# Read-Only Mode - any changes will not persist when the device reboots
	mount -o ro,noload /mnt/boot/SYSTEM.img /mnt/flash

	# Setup a volitile overlay partition for Read-only boots
	mount -t tmpfs volpart /mnt/ram
	sleep 1

	mkdir -p /mnt/ram/upper
	mkdir -p /mnt/ram/work

	mount -t overlay -o lowerdir=/mnt/flash,upperdir=/mnt/ram/upper,workdir=/mnt/ram/work overlayfs-root /mnt/root

else
	echo "Mouting in RW mode" > /dev/kmsg
	# RW Mode.  Chnages to the rootfs will persist reboots
	# Remount the boot partition as RO
	umount /mnt/boot
	mount -o rw /dev/mmcblk0p1 /mnt/boot
	# Directly mount the SYSTEM.img as the root partition
	mount  /mnt/boot/SYSTEM.img /mnt/root
fi

echo "Mouting complete" > /dev/kmsg


cd /mnt/root

mkdir -p boot
mkdir -p flash
mkdir -p ram

echo "Adjusting the Hostname" > /dev/kmsg

if [[ -f etc/hostname.orig ]]
then
	# If a .orig file exists, that means this script has already run, so we
	# will use the original version before overriding the hostname
	HOSTNAME=$( cat etc/hostname.orig )
else
	HOSTNAME=$( cat etc/hostname )
fi

# Get the serial number of the SoC
SERIAL=$( cat /sys/firmware/devicetree/base/serial-number )

# Grab the last 8 digits of the serial and add a dash to be beginning
SUFFIX="-${SERIAL: -8}"

# Set a new Hostname for the newroot dir
echo "$HOSTNAME$SUFFIX" > etc/hostname
echo "$HOSTNAME" > etc/hostname.orig

sync

pivot_root . mnt
exec chroot . sh -c "$(cat <<END
# move ro and rw mounts to the new root
mount --move /mnt/mnt/boot  /boot
mount --move /mnt/mnt/flash /flash
mount --move /mnt/mnt/ram   /ram

# unmount unneeded mounts so we can unmout the old readonly root
umount /mnt/sys
umount /mnt/dev
umount /mnt/proc
umount /mnt/mnt
umount /mnt
# continue with regular init
exec /sbin/init
END
)"

