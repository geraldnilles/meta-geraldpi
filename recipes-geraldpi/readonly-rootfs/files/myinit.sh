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


if [[ -f /mnt/boot/SYSTEM.img.gz ]]
then
	echo "Upgrading the RootFS" > /dev/kmsg
	rm /mnt/boot/SYSTEM.img

	gunzip /mnt/boot/SYSTEM.img.gz
	sync

	echo "Upgrade complete " > /dev/kmsg

fi

fsck.ext4 /mnt/boot/SYSTEM.img -p
sleep 1


sync

umount /mnt/boot

echo "Mouting the system for booting" > /dev/kmsg
# Mout the main boot partition (this will be the only partition on the SD card
mount -o ro /dev/mmcblk0p1 /mnt/boot

echo "Mouting in READONLY mode" > /dev/kmsg

# Read-Only Mode - any changes will not persist when the device reboots
mount -o ro,noload /mnt/boot/SYSTEM.img /mnt/flash

# Setup a volitile overlay partition for Read-only boots
mount -t tmpfs volpart /mnt/ram
sleep 1

mkdir -p /mnt/ram/upper
mkdir -p /mnt/ram/work

mount -t overlay -o lowerdir=/mnt/flash,upperdir=/mnt/ram/upper,workdir=/mnt/ram/work overlayfs-root /mnt/root

echo "Mouting complete" > /dev/kmsg

if [[ -f /mnt/boot/dropbear.key ]]
then
	echo "Installing Dropbear key from boot partition" > /dev/kmsg
	cp /mnt/boot/dropbear.key /mnt/root/etc/dropbear/dropbear_rsa_host_key
fi


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

