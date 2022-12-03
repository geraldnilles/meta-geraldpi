#!/usr/bin/env bash

# Customized SSH and SCP commands that do not track or check the host key

# Use a temporary Known Host file to prevent getting spammed by SSH mesages
KHOSTFILE=/tmp/khosts
rm $KHOSTFILE

function my_ssh() {
	ssh -o "UserKnownHostsFile=$KHOSTFILE" -o "StrictHostKeyChecking=no" $@
}

function my_scp() {
	scp -o "UserKnownHostsFile=$KHOSTFILE" -o "StrictHostKeyChecking=no" $@
}

if [ -z $1 ]
then
	echo "Please provide the hostname to update"
	exit 128
fi


if [ -z $2 ]
then
	echo "Please provide an image name"
	for x in $( ls *.squashfs* )
	do
		echo $x

	done
	exit 128
fi

if [ -z $3 ]
then
	echo "Please provide an env filename"
	for x in $( ls *.env )
	do
		echo $x

	done
	exit 128
fi

# Push this machine's public key to the authorized keys
MYKEY=$( cat ~/.ssh/id_rsa.pub )
my_ssh root@$1 " mkdir -p /home/root/.ssh ; echo $MYKEY >> /home/root/.ssh/authorized_keys"

my_ssh root@$1 mount /dev/mmcblk0p1 /boot

# Get the Image boog files from the .env file
source $3
for pair in $IMAGE_BOOT_FILES
do
	echo $pair
	arrIN=(${pair//;/ })
	src=${arrIN[0]} 
	dst=${arrIN[1]}

	if [ -z $dst ]
	then
		my_scp $src root@$1:/boot/$dst
	else
		my_ssh root@$1 mkdir -p /boot/$( dirname $dst)
		my_scp $src root@$1:/boot/$dst
	fi
done

# Copy the root filesystem
my_scp $2 root@$1:/boot/SYSTEM.img

# Copy the InitRamFS version of the kernel. not the vanilla kernel which is included in the IMAGE_BOOT_FILES above
my_scp Image-initramfs-$MACHINE.bin root@$1:/boot/kernel8.img

my_ssh root@$1 sync
my_ssh root@$1 umount /boot

echo "Update complete.  Restarting in 1 minutes"
my_ssh root@$1 /sbin/shutdown -r 1

