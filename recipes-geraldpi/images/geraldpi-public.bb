# Public Server
################
# This will be the only public-facing server on my network.  Everything will
# pass through ere


# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL:append = " \
	episode-server \
	todos \
	nginx \
	certbot \
"



# Add an fstab entry to automount the USB drive
# This will contain the certbox certificates so i can upgrade the image without wiping out the HTTPS certs
add_usb_automount() {

	echo "LABEL=storage	/media	ext4	defaults,nofail	0	0" >> ${IMAGE_ROOTFS}/etc/fstab

}

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-public" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " add_usb_automount; overwrite_hostname; "


