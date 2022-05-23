# Webapps
################
# This server will run a handful of Python Flask Webapps
# It will run


# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL_append += " \
	episode-player \
"


#	python3-setuptools \

# Add an fstab entry to automount the USB drive
add_usb_automount() {

	echo "LABEL=storage	/media	ext4	defaults,nofail	0	0" >> ${IMAGE_ROOTFS}/etc/fstab

}

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-webapps" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " add_usb_automount; overwrite_hostname; "


