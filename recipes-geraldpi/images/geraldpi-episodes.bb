# Episode Server
################
# This is a server that will host TV episodes that can be played via a
# chromecast.  This will host an HTTP app that kicks up a chromecast playlist
# and allows the chromecast to read the MP4 files
#
# All of the video files will be stored the /media/episodes folder. 


# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL_append += " \
	episode-server \
	rsync \
	python3-flask \
	python3-setuptools \
"



# Add an fstab entry to automount the USB drive
add_usb_automount() {

	echo "LABEL=storage	/media	ext4	defaults,nofail	0	0" >> ${IMAGE_ROOTFS}/etc/fstab

}

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-episodes" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " add_usb_automount; overwrite_hostname; "


