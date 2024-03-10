
# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL:append = " \
	document-server \
	password-store \
	scanner-scripts \
	sync-scripts \
	cputweaks \
"
# TODO Add my own "scan, convert to PDF and commit to git" script that requires
# utsushi and tesseract

# TODO Generate dropbear keys at buildtime to allow for seemless syncing
# between devices

# Add an fstab entry to automount the USB drive
# This must be an EXT4 partition with the label "documents"
add_usb_automount() {

	echo "LABEL=documents	/media	ext4	defaults,nofail	0	0" >> ${IMAGE_ROOTFS}/etc/fstab

}

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-documents" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " add_usb_automount; overwrite_hostname; "

# Dont require a root password for docs
EXTRA_USERS_PARAMS = ""
