DESCRIPTION = "An ExpressJS application for remotely accessing my house cameras"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/DogCamera.git;branch=main \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

INST_DIR = "/opt/DogCamera"


do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${INST_DIR}
	# Using the Static binary for maximum compatibility
	cp -R --no-dereference --preserve=mode,links -v ${S}/bin ${D}${INST_DIR}
	cp -R --no-dereference --preserve=mode,links -v ${S}/public ${D}${INST_DIR}
	cp -R --no-dereference --preserve=mode,links -v ${S}/routes ${D}${INST_DIR}
	cp -R --no-dereference --preserve=mode,links -v ${S}/views ${D}${INST_DIR}
	install -m 0755 ${S}/app.js ${D}${INST_DIR}

}

FILES_${PN} += " \
	${systemd_unitdir}/* \
	${INST_DIR}/* \
"

RDEPENDS_${PN} += " \
	ffmpeg \
	expressjs \
	pugjs \
"

# SYSTEMD_SERVICE_${PN} = " scam_ui.service scam_hls@LivingRoomCam.service scam_heartbeat.timer "
SYSTEMD_SERVICE_${PN} = " scam_ui.service "


