#!/bin/sh

# Mount the required system folders
mount -t proc proc /proc
mount -t sysfs sysfs /sys
mount -t devtmpfs devtmpfs /dev

sleep 5

# Generate a tmpfs to hold all the new mountpoints
mount -t tmpfs inittemp /mnt
cd /mnt

# Mout the main boot partition (this will be the only partition on the SD card
# TODO Investigte how robust Fat32 is to unexpected shutdowns
mkdir /mnt/boot
mount /dev/mmcblk0p1 /mnt/boot

# Mount the Rootfs
mkdir /mnt/root
mount  /mnt/boot/SYSTEM.img /mnt/root

cd /mnt/root

# Get the Hostname from the Read-only Rootfs
HOSTNAME=$( cat /mnt/squash_part/etc/hostname )

# Get the serial number of the SoC
SERIAL=$( cat /sys/firmware/devicetree/base/serial-number )

# Grab the last 8 digits of the serial and add a dash to be beginning
SUFFIX="-${SERIAL: -8}"

# Set a new Hostname for the newroot dir
echo "$HOSTNAME$SUFFIX" > etc/hostname

pivot_root . mnt
exec chroot . sh -c "$(cat <<END
# move ro and rw mounts to the new root
mount --move /mnt/mnt/boot /boot
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

