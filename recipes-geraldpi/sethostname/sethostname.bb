DESCRIPTION = "Set hostname based on the Raspberry Pi's serial number"
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://set-hostname-from-serial.sh \
           file://set-hostname-from-serial.service"

S = "${WORKDIR}"

inherit allarch systemd

do_install() {
    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/set-hostname-from-serial.service ${D}${sysconfdir}/systemd/system/

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/set-hostname-from-serial.sh ${D}${bindir}/
}

SYSTEMD_SERVICE_${PN} = "set-hostname-from-serial.service"
FILES_${PN} += "${systemd_system_unitdir}/set-hostname-from-serial.service \
                ${bindir}/set-hostname-from-serial.sh"

# Enable the systemd service
SYSTEMD_AUTO_ENABLE = "enable"

