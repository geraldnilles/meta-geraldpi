
# Add the folder in this directory to the files path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://wpa_supplicant.conf.in \
    file://wifi.network \
"

# Add thee config file directory file
FILES:${PN} += " \
	${sysconfdir}/wpa_supplicant/*.conf \
	${sysconfdir}/systemd/network/* \
"

# Embedd the Wifi Password and SSID into the config file
do_compile:append() {
    sed -e 's,@WIFI_SSID@,${WIFI_SSID},g' \
        -e 's,@WIFI_PASSWORD@,${WIFI_PASSWORD},g' \
        ${WORKDIR}/wpa_supplicant.conf.in > ${WORKDIR}/wpa_supplicant.conf
}

# Install these files into the /etc folder
do_install:append() {
    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

    # Add Wifi config to the SystemD NetworkD
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wifi.network ${D}${sysconfdir}/systemd/network/
}

# Enable the wpa_supplicant at boot
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " wpa_supplicant@wlan0.service"


