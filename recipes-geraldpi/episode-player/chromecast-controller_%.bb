DESCRIPTION = "A Python wrapper for PyChromecast that uses Systemd Socket Activation to quickly communicate with my Chromecasts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/Chromecast-Controller.git;branch=main;protocol=https \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

webapp_dir = "/opt/Chromecast-Controller"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${libdir}/python3.10/site-packages
	install -m 0755 ${S}/castcontroller.py ${D}${libdir}/python3.10/site-packages/


        install -d ${D}${webapp_dir}
	install -m 0755 ${S}/test.py ${D}${webapp_dir}

        install -d ${D}${bindir}
	install -m 0755 ${S}/find_chromecasts ${D}${bindir}

}

FILES:${PN} += " \
	${systemd_unitdir}/* \
	${webapp_dir}/* \
	${bindir}/* \
	${libdir}/* \
"

RDEPENDS:${PN} += " \
	python3 \
	python3-pychromecast \
	python3-systemd \
"

SYSTEMD_SERVICE:${PN} = " chromecast_controller.socket find_chromecasts.timer "


