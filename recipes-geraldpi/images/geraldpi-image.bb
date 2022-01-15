
# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb


# Include these packages in rootfs
IMAGE_INSTALL_append = " \
	wpa-supplicant \
	linux-firmware-bcm43430 \
	linux-firmware-rpidistro-bcm43430 \
	linux-firmware-rpidistro-bcm43455 \
	kernel-modules \
	tzdata \
	bash \
	bash-completion \
	hostrename \
	discover \
"


IMAGE_FEATURES += " ssh-server-dropbear "

