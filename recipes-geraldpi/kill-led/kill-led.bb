DESCRIPTION = "Kill the LED after boot"
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://kill-led.sh \
           file://kill-led.service"

S = "${WORKDIR}"

inherit allarch systemd

do_install() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system

    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/kill-led.sh ${D}${bindir}/
}


# Enable the systemd service
SYSTEMD_SERVICE:${PN} = "kill-led.service"
SYSTEMD_AUTO_ENABLE = "enable"

