DESCRIPTION = "Produces a unique, yet persistant hostname for each device"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    file://hostrename.service \
    file://hostrename \
    file://hostbasename \
"

S = "${WORKDIR}"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system

        install -d ${D}/${bindir}
        install -m 0755 ${WORKDIR}/hostrename ${D}${bindir}
        install -m 0755 ${WORKDIR}/hostbasename ${D}${bindir}
}

SYSTEMD_SERVICE:${PN} = " hostrename.service"



