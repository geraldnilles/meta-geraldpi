
# Add the folder in this directory to the files path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://wpa_supplicant-wlan0.conf \
"

# Add thee config file directory file
FILES:${PN} += " \
	${sysconfdir}/wpa_supplicant/*.conf \
"

# Install these files into the /etc folder
do_install:append() {
    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant
    /usr/bin/wpa_passphrase ${WIFI_SSID} ${WIFI_PASSWORD} >> ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf
}

# Enable the wpa_supplicant at boot
# SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " wpa_supplicant@wlan0.service"


