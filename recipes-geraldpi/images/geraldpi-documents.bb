
# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL_append += " \
        git \
	document-server \
	password-store \
	utsushi \
	tesseract \
"
# TODO Add my own "scan, convert to PDF and commit to git" script that requires
# utsushi and tesseract


# Add an fstab entry to automount the USB drive
# This must be an EXT4 partition with the label "documents"
add_usb_automount() {

	echo "LABEL=documents	/media	ext4	defaults,nofail	0	0" >> ${IMAGE_ROOTFS}/etc/fstab

}

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-documents" > ${IMAGE_ROOTFS}/etc/hostname
}


# Link the external drive dropbear RSA key to the /etc folder
move_rsa_key() {
	rm -r ${IMAGE_ROOTFS}/etc/dropbear
	ln -s /media/dropbear ${IMAGE_ROOTFS}/etc/dropbear
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " add_usb_automount; overwrite_hostname; move_rsa_key; "


# CORE_IMAGE_EXTRA_INSTALL += " ldd "

