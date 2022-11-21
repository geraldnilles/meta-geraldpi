# Webapps
################
# This server will run a handful of Python Flask Webapps


# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL:append = " \
	cec-controller \
"

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-cec" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " overwrite_hostname; "


