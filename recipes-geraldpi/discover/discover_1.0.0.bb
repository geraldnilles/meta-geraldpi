DESCRIPTION = "Scripts for various GeraldPi isntances to find eachother"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://ssh.service \
    file://find_peers \
"

S = "${WORKDIR}"

do_install() {
        install -d ${D}/${bindir}
        install -m 0755 ${WORKDIR}/find_peers ${D}${bindir}

        install -d ${D}${sysconfdir}/avahi/services
	install -m 0644 ${WORKDIR}/ssh.service ${D}${sysconfdir}/avahi/services
}


RDEPENDS_${PN} = "\
    avahi-daemon \
    avahi-utils \
    hostrename \
"


