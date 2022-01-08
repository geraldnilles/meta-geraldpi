#!/usr/bin/env bash

# This script can be run on any Linxu machine to prepare the USB drive.
# I decided not to put this script into the yocto image since preparing the USB
# drive is something that shoudl be done rarely.  Only when a system is setup
# for the first time should you need to format and populate the USB drive.

# If i put this script on the Yocto target, I would need some sort of "trigger"
# that forces a reformat.  I worry there coudl be false triggers that
# accidentally wipe all of my devices.  By making this a manual proces, it is
# unlikely these uSB drives get corrupted.  I could make this "trigger" very
# purposeful.  I.e. you need to format the USB drive as a blank FAT32 partition
# with a single file named "WIPEME".  Maybe one day.

# USer provdes the disk devices

if [ -z $1 ]
then
	echo "Please provide a device name to format"
fi

# TODO THIs script is currently just an outline and is not tested
echo "Script not Tested"
exit

(
echo g # Create a new empty DOS partition table
echo n # Add a new partition
echo p # Primary partition
echo 1 # Partition number
echo   # First sector (Accept default: 1)
echo   # Last sector (Accept default: varies)
echo w # Write changes
) | fdisk $1

# Format the entire partition as an ext4 filesystem. The label is set to
# documents so the right partition is mounted
mkfs.ext4 -L documents "$11"

mkdir temp

mount "$11" ./temp

cd ./temp

mkdir dropbear

mkdir gnupg

mkdir passwords

mkdir documents

cd ..

sync

umount ./temp

rm -rf temp

