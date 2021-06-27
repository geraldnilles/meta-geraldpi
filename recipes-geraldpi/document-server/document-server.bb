DESCRIPTION = "Simple HTTP Document Server"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    file://document-server.service \
"

S = "${WORKDIR}"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
}

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = " document-server.service"

RDEPENDS_${PN} = "\
    python3 \
"


