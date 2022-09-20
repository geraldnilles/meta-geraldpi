DESCRIPTION = "Pass: The Standard Unix Password Manager"
HOMEPAGE = "https://www.passwordstore.org/"
LICENSE = "GPL-2.0-only"
SECTION = "console/utils"
LIC_FILES_CHKSUM = "file://COPYING;md5=573db2df927d27d30e3f59d328774d72"

SRC_URI = "https://git.zx2c4.com/password-store/snapshot/password-store-1.7.4.tar.xz \
	file://pass-term-hack.sh \
"

SRC_URI[sha256sum] = "cfa9faf659f2ed6b38e7a7c3fb43e177d00edbacc6265e6e32215ff40e3793c0"


do_install () {
    # This is a guess; additional arguments may be required
    oe_runmake install 'DESTDIR=${D}'

    # I dont need th zfs stuff
    #
    #rm -r ${D}/usr/share/zsh
    #rm -r ${D}/usr/share/fish

    install -d ${D}${sysconfdir}/profile.d
    install -d ${D}/home/root
    install -m 0644 ${WORKDIR}/pass-term-hack.sh ${D}${sysconfdir}/profile.d
    ln -s /media/passwords ${D}/home/root/.password-store
    ln -s /media/gnupg ${D}/home/root/.gnupg
}

FILES:${PN} += " \
	/usr/share/bash-completion \
	/usr \
	/usr/share/bash-completion/completions \
	/usr/share/bash-completion/completions/pass \
	${sysconfdir}/profile.d/*.sh \
	/home \
	/home/root \
"

RDEPENDS:${PN} += " \
	gnupg \
	tree \
	git \
	coreutils \
"

