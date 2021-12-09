DESCRIPTION = "Utsushi: Image Scan Utility for Epson Scanners"
HOMEPAGE = "https://gitlab.com/utsushi"
LICENSE = "GPLv3"
SECTION = "libs/multimedia"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://support.epson.net/linux/src/scanner/imagescanv3/common/imagescan_3.65.0.orig.tar.gz \
	file://boost-1.74.patch \
"

SRC_URI[sha256sum] = "e83704398c51a3166fd62c25b89e95cf6262e52f3dc6e627db3e7556e2220d64"


# TODO THere is currently a segfault when you attempt to run the scan command.
# The list command does work, however.  need to dig into that
# https://www.doulos.com/knowhow/arm-embedded/embedded-linux-debugging-user-space-seg-faults/ 

FILES_${PN} += " \
"

RDEPENDS_${PN} += " \
	boost \
	libusb1 \
"

DEPENDS += " \
	boost \
	libtool \
	libusb1 \
"

inherit autotools
inherit gettext

# This package comes with a working configuration file, This forces autotools
# to skip the autoconf step
do_configure() {
	oe_runconf

}

# TODO Remove all the executables except the scan-cli and move it to the /usr/bin folder


# Configure script needs help finding the boot library location
EXTRA_OECONF_append += "--with-boost-libdir=${STAGING_DIR_TARGET}/usr/lib"

# TODO Add Version number to libraries to avoid this QA Error

# Generated library files do nto contains a version number so Yocto gets mad.
# This tells the packacing script to skip that check
INSANE_SKIP_${PN} = "dev-so"
