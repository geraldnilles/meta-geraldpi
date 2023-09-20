# Webapps
################
# This server will run a handful of Python Flask Webapps


# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL:append = " \
	cec-control \
"

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-cec" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " overwrite_hostname; "


#TODO need to add this line to the config.txt file to get cec0 working:
#	dtoverlay=vc4-kms-v3d
# This shoudl work but i'm not sure if i can add it at the image level or if i
# need to add it in a local config

