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

mkfs.ext4

mount

cd /media/

mkdir dropbear

mkdir gnupg

mkdir passwords


