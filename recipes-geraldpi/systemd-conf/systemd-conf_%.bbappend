

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://eth.network \
    file://wifi.network \
"

# Add these 2 .network files into the final package
FILES_${PN} += " \
	${sysconfdir}/systemd/network/*.network \
"

# Install these files into the /etc folder
do_install_append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wifi.network ${D}${sysconfdir}/systemd/network
}


