DESCRIPTION = "A Python Flask Web app application for playing local TV episodes on a Chromecast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/Chromecast-Episode-Player.git;branch=main \
    file://no_venv.patch \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

INST_DIR = "/opt/Chromecast-Episode-Player"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${INST_DIR}
	# Using the Static binary for maximum compatibility
	cp -R --no-dereference --preserve=mode,links -v ${S}/episodes ${D}${INST_DIR}
	cp -R --no-dereference --preserve=mode,links -v ${S}/scripts ${D}${INST_DIR}
	cp -R --no-dereference --preserve=mode,links -v ${S}/library ${D}${INST_DIR}
	install -m 0755 ${S}/run.sh ${D}${INST_DIR}

}

FILES_${PN} += " \
	${systemd_unitdir}/* \
	${INST_DIR}/* \
"

RDEPENDS_${PN} += " \
	python3-flask \
	python3-pychromecast \
"

SYSTEMD_SERVICE_${PN} = " chromecastEpisodes_webserver.service "


