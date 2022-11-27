DESCRIPTION = "A Python Flask Web app application for regulating temperature"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# inherit systemd

SRC_URI = " \
    file://run.sh \
"


# Use this if you want to automatically pull the latest commit
# SRCREV = "${AUTOREV}"
# PV = "1.0+git${SRCPV}"

S = "${WORKDIR}"

webapp_dir = "/opt/thermostat"

do_install() {
        # install -d ${D}/${systemd_unitdir}/system
        # install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	install -m 0755 ${S}/run.sh ${D}${webapp_dir}

}

FILES:${PN} += " \
	${webapp_dir}/* \
"

RDEPENDS:${PN} += " \
	python3 \
	python3-pandas \
	bluez5 \
"

# SYSTEMD_SERVICE:${PN} = " todolist_webserver.service "


