# Webapps
################
# This server will run a handful of Python Flask Webapps


# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb


# Include these packages in rootfs
IMAGE_INSTALL:append = " \
	wpa-supplicant \
	linux-firmware-bcm43430 \
	kernel-modules \
	tzdata \
	cec-control \
	sethostname \
"

IMAGE_FSTYPES = " wic ext4.gz cpio.gz " 

IMAGE_FEATURES += " ssh-server-dropbear "

do_build[depends] += " sd-flash:do_deploy "

# Set a default password to 'geraldpi'
# Used this command to generate the hash: openssl passwd geraldpi
# Escape the $ symbols and use single quotes
inherit extrausers
EXTRA_USERS_PARAMS = " usermod -p '\$1\$LRQCqbZE\$TshPObg6s.jwB6J9T2xJJ.' root; "

# Update the hostname for this image
overwrite_hostname() { 
	echo "gpi-cec" > ${IMAGE_ROOTFS}/etc/hostname
}

# Add all the rootfs modifications to the list
ROOTFS_POSTINSTALL_COMMAND += " overwrite_hostname; "

