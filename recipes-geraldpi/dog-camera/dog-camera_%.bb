DESCRIPTION = "An ExpressJS application for remotely accessing my house cameras"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/DogCamera.git;branch=main;protocol=https \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

webapp_dir = "/opt/DogCamera"


do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	# Using the Static binary for maximum compatibility
	cp -R --no-dereference --preserve=mode,links -v ${S}/bin ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/public ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/routes ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/views ${D}${webapp_dir}
	install -m 0755 ${S}/app.js ${D}${webapp_dir}

}

FILES:${PN} += " \
	${systemd_unitdir}/* \
	${webapp_dir}/* \
"

RDEPENDS:${PN} += " \
	ffmpeg \
	expressjs \
	pugjs \
"

SYSTEMD_SERVICE:${PN} = " scam_ui.service "


