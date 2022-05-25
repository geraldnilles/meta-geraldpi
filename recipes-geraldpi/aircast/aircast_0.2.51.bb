DESCRIPTION = "Airplay to Google Cast Audio Bridge Service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    file://aircast.service \
    git://github.com/philippe44/AirConnect.git;rev=14ecfa56f0e20b4a9e2615761ef34f7e92bc2128;branch=master;protocol=https \
"

S = "${WORKDIR}/git"


do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system

        install -d ${D}/${bindir}
	# Using the Static binary for maximum compatibility
	install -m 0755 ${S}/bin/aircast-aarch64-static ${D}/${bindir}/aircast
}


SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = " aircast.service "

# Using the pre-compiled binary in the git repo so this QA step needs to be skipped
INSANE_SKIP:${PN}:append = " already-stripped "

