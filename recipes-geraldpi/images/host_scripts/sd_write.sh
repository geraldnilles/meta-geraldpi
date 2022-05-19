#!/usr/bin/env bash


if [ -z $1 ]
then
	echo "Please provide a device name to format"
	exit 128
fi

cd ../../../../build/tmp/deploy/images/raspberrypi4-64/

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


(
echo o # Create a new empty DOS partition table

echo n # Add a new partition
echo "p"  # Partition Type (primary)
echo " "  # Partition Number (1)
echo " "  # First sector (Accept default: 1)
echo "+200M"  # Last sector (200MB
echo "t"  # Set Partition Type)
echo "c"  # Set W95 LBA TYpe

echo n # Add a new partition
echo "p"  # Partition Type (primary)
echo " "  # Partition Number (2)
echo " "  # First sector (Accept default: 1)
echo "+2G"  # Last sector (200MB

echo n # Add a new partition
echo "p"  # Partition Type (primary)
echo " "  # Partition Number (3)
echo " "  # First sector (Accept default: 1)
echo " "  # Last sector (200MB

echo w # Write changes
) | fdisk -W always -w always $1


# Format the entire partition as an ext4 filesystem. The label is set to
# documents so the right partition is mounted
mkfs.vfat -n boot "$11"

sleep 1

MOUNTPOINT=/tmp/gpimount
mkdir $MOUNTPOINT

mount "$11" $MOUNTPOINT

source $3

for pair in $IMAGE_BOOT_FILES
do
	#echo $pair
	arrIN=(${pair//;/ })
	src=${arrIN[0]} 
	dst=${arrIN[1]}

	if [ -z $dst ]
	then
		echo "Copy $src to boot partition"
		cp $src $MOUNTPOINT/
	else
		echo "Copy $src to $dst"
		mkdir -p $MOUNTPOINT/$( dirname $dst)
		cp $src $MOUNTPOINT/$dst
	fi
	

done

sync

umount $MOUNTPOINT

rm -r $MOUNTPOINT

echo "Copying the Root Parition"
dd if=$2 of=$12 bs=1M

sync

