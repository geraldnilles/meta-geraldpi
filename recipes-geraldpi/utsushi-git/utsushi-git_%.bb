DESCRIPTION = "Utsushi Git Repo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = " \
    git://gitlab.com/utsushi/utsushi.git;branch=master;protocol=https \
    file://ac_check_progs_fix.patch \
"

# Use this if you want to use a specific commit
SRCREV = "839d06a5a80b353cb604eb9f7d352a1648ab1fdf"

# Use this if you want to automatically pull the latest commit
# SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"


inherit pkgconfig
inherit gettext
inherit autotools

S = "${WORKDIR}/git"

do_configure:prepend() {
	olddir=`pwd`
	cd ${S}
	./bootstrap --force
	cd $olddir
}

RDEPENDS:${PN} += " \
	boost \
	libusb1 \
"

DEPENDS += " \
	boost \
	libusb1 \
	libtool \
	libtool-native \
	libxslt-native \
	autoconf-archive-native \
	graphicsmagick \
	libjpeg-turbo \
	tiff \
"

FILES:${PN} += " \
"

EXTRA_OECONF:append = " \
     --with-boost-libdir=${STAGING_DIR_TARGET}/usr/lib \
    --with-magick \
    --with-jpeg \
    --with-tiff \
"


