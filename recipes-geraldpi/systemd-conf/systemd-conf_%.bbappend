

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://eth.network \
    file://wifi.network \
    file://journald.conf \
"

# Add these 2 .network files into the final package
FILES:${PN} += " \
	${sysconfdir}/systemd/network/*.network \
	${systemd_unitdir}/journald.conf.d/00-${PN}.conf \
"

# Install these files into the /etc folder
do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wifi.network ${D}${sysconfdir}/systemd/network
    install -D -m0644 ${WORKDIR}/journald.conf ${D}${systemd_unitdir}/journald.conf.d/00-${PN}.conf
}


