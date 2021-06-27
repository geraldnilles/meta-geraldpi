
# Base this image on core-image-minimal
include recipes-geraldpi/images/geraldpi-image.bb


# Include modules in rootfs
IMAGE_INSTALL_append += " \
        git \
        hostname-documents \
"

# python3-flask \
# python3-setuptools \
