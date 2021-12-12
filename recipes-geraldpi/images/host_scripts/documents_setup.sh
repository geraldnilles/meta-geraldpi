#!/usr/bin/env bash

# This script can be run on any Linxu machine to prepare the USB drive.
# I decided not to put this script into the yocto image since preparing the USB
# drive is something that shoudl be done rarely.  Only when a system is setup
# for the first time should you need to format and popate the USB drive.

# If i put this script on the Yocto target, I would need some sort of "trigger"
# that forces a reformat.  I worry there coudl be false triggers that
# accidentally wipe all of my devices.  By making this a manual proces, it is
# unlikely these uSB drives get corrupted

# USer provdes the disk devices

fdisk... to create 1 partition

# Format the entire partition as an ext4 filesystem. The label is set to
# documents so the right partition is mounted
mkfs.ext4 -L documents

mkdir temp

mount /sd... ./temp

cd ./temp


mkdir dropbear

mkdir gnupg

mkdir passwords

mkdir documents

cd ..

sync

umount ./temp

rm -rf temp

