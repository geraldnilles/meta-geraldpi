DESCRIPTION = "A Python Flask Web app application for recording and playing back local news on a Chromecast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/LocalNewsDVR.git;branch=main;protocol=https \
"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

webapp_dir = "/opt/LocalNewsDVR"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/localnews ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/scripts ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/flask_cors ${D}${webapp_dir}
	install -m 0755 ${S}/run.sh ${D}${webapp_dir}
}

FILES:${PN} += " \
	${webapp_dir}/* \
	${systemd_unitdir}/* \
"

RDEPENDS:${PN} += " \
	python3 \
	python3-flask \
	python3-pychromecast \
	ffmpeg \
	cputweaks \
"

SYSTEMD_SERVICE:${PN} = " localnews_webserver.service "

INSANE_SKIP:${PN}:append = " already-stripped "

