DESCRIPTION = "Sync Data between instances on the network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    file://sync_all \
    file://sync_docs \
    file://sync-scripts.service \
    file://sync-scripts.timer \
    file://sync_passwords \
"

S = "${WORKDIR}"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/sync-scripts.service ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/sync-scripts.timer ${D}${systemd_unitdir}/system

        install -d ${D}/${bindir}
        install -m 0755 ${WORKDIR}/sync_* ${D}${bindir}
}

SYSTEMD_SERVICE:${PN} = " sync-scripts.timer "


FILES:${PN} += " \
	${systemd_unitdir}/* \
"

