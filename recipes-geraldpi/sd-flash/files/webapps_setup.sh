#!/usr/bin/env bash

# This script can be run on any Linxu machine to prepare the USB drive.
# I decided not to put this script into the yocto image since preparing the USB
# drive is something that shoudl be done rarely.  Only when a system is setup
# for the first time should you need to format and populate the USB drive.

# If i put this script on the Yocto target, I would need some sort of "trigger"
# that forces a reformat.  I worry there could be false triggers that
# accidentally wipe all of my devices.  By making this a manual proces, it is
# unlikely these USB drives get accidentally wiped.  I could make this
# "trigger" very purposeful.  I.e. you need to format the USB drive as a blank
# FAT32 partition with a single file named "WIPEME".  Maybe one day.

# User provdes the disk devices
if [ -z $1 ]
then
	echo "Please provide a device name to format"
	exit 128
fi


(
echo g # Create a new empty GPT partition table
echo n # Add a new partition
echo " "  # Partition number
echo " "  # First sector (Accept default: 1)
echo " "  # Last sector (Accept default: varies)
echo " "  # Last sector (Accept default: varies)
echo " "  # Last sector (Accept default: varies)
echo " "  # Last sector (Accept default: varies)
echo w # Write changes
) | fdisk $1

# Format the entire partition as an ext4 filesystem. The label is set to
# webapps so the right partition is mounted
mkfs.ext4 -L webapps "$11"

mkdir temp

mount "$11" ./temp

cd ./temp

mkdir episodes

mkdir todos

cd ..

sync

umount ./temp

rm -r temp

