DESCRIPTION = "Pass: The Standard Unix Password Manager"
HOMEPAGE = "https://www.passwordstore.org/"
LICENSE = "GPLv2"
SECTION = "console/utils"
LIC_FILES_CHKSUM = "file://COPYING;md5=573db2df927d27d30e3f59d328774d72"

SRC_URI = "https://git.zx2c4.com/password-store/snapshot/password-store-1.7.4.tar.xz"

SRC_URI[sha256sum] = "cfa9faf659f2ed6b38e7a7c3fb43e177d00edbacc6265e6e32215ff40e3793c0"


do_install () {
    # This is a guess; additional arguments may be required
    oe_runmake install 'DESTDIR=${D}'

    rm -r ${D}/usr/share/zsh
}

FILES_${PN} += " \
	/usr/share/bash-completion \
	/usr/share/bash-completion/completions \
	/usr/share/bash-completion/completions/pass \
"

RDEPENDS_${PN} += " \
	gnupg \
	tree \
"

