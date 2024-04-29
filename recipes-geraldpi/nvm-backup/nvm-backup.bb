DESCRIPTION = "Periodicly backup system files into Boot partition for use with read-only Rootfs"
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Adjust as needed if using a different compression method
RDEPENDS:${PN} = "tar"

inherit systemd

SYSTEMD_SERVICE:${PN} = "nvm_backup.service nvm_backup.timer nvm_restore.service"
SYSTEMD_AUTO_ENABLE = "enable"

SRC_URI = "\
    file://nvm_backup.service  \
    file://nvm_backup.timer	 \
    file://nvm_restore.service \
    file://nvm_backup.sh \
    file://nvm_restore.sh \
    file://nvm_backup_files.txt \
"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/nvm_backup_files.txt ${D}${sysconfdir}


    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/*.timer ${D}${systemd_unitdir}/system

    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/nvm_*.sh ${D}${bindir}/
}


