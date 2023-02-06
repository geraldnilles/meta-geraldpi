DESCRIPTION = "An application for regulating temperature"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/Thermostat.git;branch=main;protocol=https \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

do_install() {
	DESTDIR=${D} make install
}

FILES:${PN} += " \
	/opt/thermostat/* \
	${sysconfdir}/* \
	${systemd_unitdir}/* \
	${datadir}/* \
"

RDEPENDS:${PN} += " \
	python3 \
	python3-pandas \
	python3-bleak \
	python3-flask \
	bluez5 \
"

SYSTEMD_SERVICE:${PN} = " thermostat.timer temp-scanner.service "

