#!/usr/bin/env bash

cd "$(dirname "$0")"

if [ -z $1 ]
then
	echo "Please provide a device name to format"
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

# If not in SD_UPDATE mode, the script will repartition and format the SDcard
if [ -z $SD_UPDATE ]
then

echo "Creting new SD Card"

(
echo o # Create a new empty DOS partition table

echo n # Add a new partition
echo "p"  # Partition Type (primary)
echo " "  # Partition Number (1)
echo " "  # First sector (Accept default: 1)
echo " "  # 1Gig boot partition
echo "t"  # Set Partition Type)
echo "c"  # Set W95 LBA TYpe

echo "w" # Write changes
) | fdisk -W always -w always $1

sleep 1

# Format the Boot and Storage partitions
mkfs.vfat -n boot "$11"

else

echo "Updating SD Card"

fi

# Wait a beat for the parititons to be detected by the kernel
sleep 1

# Copy all the boot files to the boot parition
MOUNTPOINT=/tmp/gpimount
mkdir $MOUNTPOINT

mount "$11" $MOUNTPOINT

# Get the Image boog files from the .env file
source $3
for pair in $IMAGE_BOOT_FILES
do
	#echo $pair
	arrIN=(${pair//;/ })
	src=${arrIN[0]} 
	dst=${arrIN[1]}

	if [ -z $dst ]
	then
		cp $src $MOUNTPOINT/
	else
		mkdir -p $MOUNTPOINT/$( dirname $dst)
		cp $src $MOUNTPOINT/$dst
	fi
	

done

cp $2 $MOUNTPOINT/SYSTEM.img

# Copy a githash into the boot partition
cp ../githash $MOUNTPOINT/

cp Image-initramfs-raspberrypi*.bin $MOUNTPOINT/kernel8.img

sync

umount $MOUNTPOINT

rm -r $MOUNTPOINT



sync

