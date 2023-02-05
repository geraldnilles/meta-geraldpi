DESCRIPTION = "Airplay to Google Cast Audio Bridge Service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    file://aircast.service \
    git://github.com/philippe44/AirConnect.git;branch=master;protocol=https \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"


do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system

        install -d ${D}/${bindir}
	# Using the Static binary for maximum compatibility
	install -m 0755 ${S}/bin/aircast-linux-aarch64-static ${D}/${bindir}/aircast
}


SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = " aircast.service "

# Using the pre-compiled binary in the git repo so this QA step needs to be skipped
INSANE_SKIP:${PN}:append = " already-stripped "

