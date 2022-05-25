DESCRIPTION = "A Python Flask Web app application for playing local TV episodes on a Chromecast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/Chromecast-Episode-Player.git;branch=main;protocol=https \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

webapp_dir = "/opt/Chromecast-Episode-Player"
video_library = "/media/episodes"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/episodes ${D}${webapp_dir}

	cp -R --no-dereference --preserve=mode,links -v ${S}/scripts ${D}${webapp_dir}
	install -m 0755 ${S}/run.sh ${D}${webapp_dir}

	ln -s ${video_library} ${D}${webapp_dir}/library
}

FILES:${PN} += " \
	${systemd_unitdir}/* \
	${webapp_dir}/* \
"

RDEPENDS:${PN} += " \
	python3 \
	python3-flask \
	python3-pychromecast \
"

SYSTEMD_SERVICE:${PN} = " chromecastEpisodes_webserver.service "


