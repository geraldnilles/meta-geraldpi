SUMMARY = "Simple FAT32 Boot Partition Checker"
DESCRIPTION = "Systemd service to check /dev/mmcblk0p1 after a delay."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://boot-check.service"

inherit systemd

# Runtime dependency for fsck.vfat
RDEPENDS:${PN} += "dosfstools"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/boot-check.service ${D}${systemd_system_unitdir}/boot-check.service
}

SYSTEMD_SERVICE:${PN} = "boot-check.service"
SYSTEMD_AUTO_ENABLE = "enable"

