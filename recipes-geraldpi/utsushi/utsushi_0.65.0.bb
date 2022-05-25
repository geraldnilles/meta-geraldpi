DESCRIPTION = "Utsushi: Image Scan Utility for Epson Scanners"
HOMEPAGE = "https://gitlab.com/utsushi"
LICENSE = "GPLv3"
SECTION = "libs/multimedia"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://support.epson.net/linux/src/scanner/imagescanv3/common/imagescan_3.65.0.orig.tar.gz \
	file://boost-1.74.patch \
	file://autoreconf_parenthesis.patch \
"

SRC_URI[sha256sum] = "e83704398c51a3166fd62c25b89e95cf6262e52f3dc6e627db3e7556e2220d64"

FILES:${PN} += " \
"

RDEPENDS:${PN} += " \
	boost \
	libusb1 \
"

DEPENDS += " \
	boost \
	libtool \
	libusb1 \
	autoconf-archive-native \
	graphicsmagick \
	libjpeg-turbo \
	tiff \
"

inherit autotools
inherit gettext

# Configure script needs help finding the boot library location
EXTRA_OECONF:append = " \
    --with-boost-libdir=${STAGING_DIR_TARGET}/usr/lib \
    --with-magick \
    --with-jpeg --with-tiff \
"

# TODO Add Version number to libraries to avoid this QA Error

# Generated library files do nto contains a version number so Yocto gets mad.
# This tells the packacing script to skip that check
INSANE_SKIP:${PN} = "dev-so"

