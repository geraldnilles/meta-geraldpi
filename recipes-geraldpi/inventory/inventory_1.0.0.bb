SUMMARY = "Garage Inventory Management System"
DESCRIPTION = "A web-based inventory management system for garages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} += " python3-pywebio "

SRC_URI = "file://inventory.py \
           file://inventory.service \
          "

S = "${WORKDIR}"

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "inventory.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/inventory.py ${D}${bindir}/inventory.py

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/inventory.service ${D}${systemd_unitdir}/system
}

