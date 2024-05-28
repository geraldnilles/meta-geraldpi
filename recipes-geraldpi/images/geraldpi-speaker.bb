# Bluetooth Speaker
################
# This is a rasbperry pi based bluetooth speaker


# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL:append = " \
	wireplumber \
	wireplumber-modules-meta \
	wireplumber-default-config \
	wireplumber-scripts \
	bluez5 \
	pipewire \
	pipewire-tools \
	alsa-utils \
	pipewire-alsa \
	pipewire-modules-meta \
	pipewire-spa-plugins-meta \
"

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-speaker" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " overwrite_hostname; "


