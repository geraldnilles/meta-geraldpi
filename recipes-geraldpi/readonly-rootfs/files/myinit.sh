#!/bin/sh

# Generate a tmpfs to hold all the new mountpoints
mount -t proc proc /proc
mount -t tmpfs inittemp /mnt

cd /mnt

# Enable the OverlayFS
modprobe overlay

# Generate new Mountpoint directories
mkdir /mnt/lower
mkdir /mnt/newroot
mkdir /mnt/rw

# Mount the rootfs here
mount /dev/mmcblk0p2 /mnt/lower

# Mounts a RAMFS for the overlay's RW partition
mount -t tmpfs overlaytemp /mnt/rw

# Generate Work Directories needed for the overlayFS
mkdir /mnt/rw/upper
mkdir /mnt/rw/work

mount -t overlay -o lowerdir=/mnt/lower,upperdir=/mnt/rw/upper,workdir=/mnt/rw/work overlayfs-root /mnt/newroot

cd /mnt/newroot

# Make mountpoints for the underlying drive
mkdir rw
mkdir ro 


pivot_root . mnt
exec chroot . sh -c "$(cat <<END
# move ro and rw mounts to the new root
mount --move /mnt/mnt/lower/ /ro
mount --move /mnt/mnt/rw /rw
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

