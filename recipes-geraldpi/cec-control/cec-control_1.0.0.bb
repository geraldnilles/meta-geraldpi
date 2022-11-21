DESCRIPTION = "Scripts for automatically shuting off the TV"
SECTION = "libs/multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = " \
    file://chromecast_status.py \
    file://configure.sh
    file://loop.sh
    file://tv_off.sh
    file://tv_on.sh
    file://tv_status.sh
"

S = "${WORKDIR}"

app_dir = "/opt/cec-control"

do_install() {

        install -d ${D}/${app_dir}
	
        install -m 0755 ${WORKDIR}/chromecast_status.py ${D}/${app_dir}
        install -m 0755 ${WORKDIR}/configure.sh ${D}/${app_dir}
        install -m 0755 ${WORKDIR}/loop.sh ${D}/${app_dir}
        install -m 0755 ${WORKDIR}/tv_off.sh ${D}/${app_dir}
        install -m 0755 ${WORKDIR}/tv_on.sh ${D}/${app_dir}
        install -m 0755 ${WORKDIR}/tv_status.sh ${D}/${app_dir}

}

FILES:${PN} += " \
	${systemd_unitdir}/* \
	${app_dir}/* \
"


RDEPENDS:${PN} = " \
	v4l-utils \
	python3-pychromecast \
"


