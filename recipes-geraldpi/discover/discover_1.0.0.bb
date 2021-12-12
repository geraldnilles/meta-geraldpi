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
        install -d ${D}/${systemd_unitdir}/system
}


RDEPENDS_${PN} = "\
    avahi-daemon \
    avahi-utils \
"


