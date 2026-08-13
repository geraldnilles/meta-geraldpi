DESCRIPTION = "A Matter-compatible HVAC thermostat for Raspberry Pi with multi-zone BLE sensing, MQTT/Home Assistant bridge, and a Flask web UI"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/Matter-HVAC-Thermostat.git;branch=main;protocol=https \
"

# Use this if you want to use a specific commit
# SRCREV = "[githash]"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${prefix} SYSCONFDIR=${sysconfdir} UNITDIR=${systemd_system_unitdir}
}

FILES:${PN} += " \
    ${prefix}/share/thermostat/* \
    ${sysconfdir}/thermostat/* \
    ${systemd_unitdir}/* \
"

RDEPENDS:${PN} += " \
    python3 \
    python3-flask \
    python3-paho-mqtt \
    python3-bleak \
    bluez5 \
    libgpiod-tools \
"

# The schedule .service units are oneshot and are pulled in by their timers
# (Unit=...), so they are intentionally NOT enabled directly. Only the timers
# are listed here, matching upstream's `systemctl enable --now ...timer` flow.
SYSTEMD_SERVICE:${PN} = " \
    thermostat-setup.service \
    thermostat-sensor.service \
    thermostat-control.service \
    thermostat-gpio.service \
    thermostat-mqtt.service \
    thermostat-web.service \
    thermostat-schedule-morning.timer \
    thermostat-schedule-night.timer \
"
