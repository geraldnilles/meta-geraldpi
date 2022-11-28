DESCRIPTION = "An application for regulating temperature"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    file://run.sh \
    file://gpio.sh \
    file://heat.sh \
    file://scan.py \
    file://test_client.py \
    file://max_temp.sh \
    file://min_temp.sh \
    file://room_average.py \
"


# Use this if you want to automatically pull the latest commit
# SRCREV = "${AUTOREV}"
# PV = "1.0+git${SRCPV}"

S = "${WORKDIR}"

webapp_dir = "/opt/thermostat"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/*.service ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/*.timer ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	install -m 0755 ${S}/*.sh ${D}${webapp_dir}
	install -m 0755 ${S}/*.py ${D}${webapp_dir}

}

FILES:${PN} += " \
	${webapp_dir}/* \
	${systemd_unitdir}/* \
"

RDEPENDS:${PN} += " \
	python3 \
	python3-pandas \
	python3-bleak \
	bluez5 \
"

SYSTEMD_SERVICE:${PN} = " thermostat.timer temp-scanner.service "


