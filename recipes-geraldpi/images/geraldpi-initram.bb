# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb


# Include these packages in rootfs
IMAGE_INSTALL:append = " \
	bash \
	readonly-rootfs \
"

IMAGE_FEATURES = " "

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-init" > ${IMAGE_ROOTFS}/etc/hostname
	rm -f ${IMAGE_ROOTFS}/sbin/init
	cp ${IMAGE_ROOTFS}/sbin/myinit.sh ${IMAGE_ROOTFS}/sbin/init
}


# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " overwrite_hostname; "

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

PACKAGE_INSTALL = "${IMAGE_INSTALL}"


