
# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL_append += " \
	aircast \
	dog-camera \
	cputweaks \
"



# Add an fstab entry to automount the USB drive
# This must be an EXT4 partition with the label "documents"

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-scam" > ${IMAGE_ROOTFS}/etc/hostname
}


# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " overwrite_hostname; "


