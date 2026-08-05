# matter-hvac-thermostat

Yocto recipe for `matter-hvac-thermostat`, pulled from
`git://github.com/geraldnilles/Matter-HVAC-Thermostat.git` (branch `main`, `SRCREV = ${AUTOREV}`).

## Key details

- Build/install: the upstream `Makefile` supports `DESTDIR`/`PREFIX`/`SYSCONFDIR`/`UNITDIR`.
  The recipe uses `oe_runmake install` with `UNITDIR=${systemd_unitdir}` so unit files land in
  `${D}/lib/systemd/system` (where the `systemd` class expects them).
- Services: 6 systemd units — `thermostat-setup`, `thermostat-sensor`, `thermostat-control`,
  `thermostat-gpio`, `thermostat-mqtt`, `thermostat-web`. All are listed in `SYSTEMD_SERVICE`.
- Runtime deps: `python3`, `python3-flask`, `python3-paho-mqtt`, `python3-bleak`, `bluez5`,
  `libgpiod-tools` (provides `gpioset`), `mosquitto` (MQTT broker for the HA bridge).
- Config: `/etc/thermostat/defaults.json` must be edited per-device (sensor MACs, MQTT broker).

## Related recipes

- `recipes-geraldpi/thermostat/thermostat.bb` is the *older* (pre-Matter) thermostat project.
  `recipes-geraldpi/images/geraldpi-thermostat.bb` still installs the old `thermostat` recipe.
