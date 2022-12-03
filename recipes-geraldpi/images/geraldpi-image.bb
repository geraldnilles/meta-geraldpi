
# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb


# Include these packages in rootfs
IMAGE_INSTALL:append = " \
	wpa-supplicant \
	linux-firmware-bcm43430 \
	linux-firmware-rpidistro-bcm43430 \
	linux-firmware-rpidistro-bcm43455 \
	kernel-modules \
	tzdata \
	bash \
	bash-completion \
	discover \
"

IMAGE_FSTYPES = " wic squashfs " 

IMAGE_FEATURES += " ssh-server-dropbear "

do_build[depends] += " sd-flash:do_deploy "

# Set a default password to 'geraldpi'
# Used this command to generate the hash: openssl passwd geraldpi
inherit extrausers
EXTRA_USERS_PARAMS = " usermod -p '$1$LRQCqbZE$TshPObg6s.jwB6J9T2xJJ.' root; "

