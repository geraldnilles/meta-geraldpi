# matter-hvac-thermostat

Yocto recipe for `matter-hvac-thermostat`, pulled from
`git://github.com/geraldnilles/Matter-HVAC-Thermostat.git` (branch `main`, `SRCREV = ${AUTOREV}`).

## Key details

- Build/install: the upstream `Makefile` supports `DESTDIR`/`PREFIX`/`SYSCONFDIR`/`UNITDIR`.
  The recipe uses `oe_runmake install` with `UNITDIR=${systemd_system_unitdir}` so unit files land in
  `${D}/lib/systemd/system` (where the `systemd` class expects them).
- Services: 6 daemon service units — `thermostat-setup`, `thermostat-sensor`,
  `thermostat-control`, `thermostat-gpio`, `thermostat-mqtt`, `thermostat-web` — plus
  2 schedule timer units (`thermostat-schedule-morning.timer`, `thermostat-schedule-night.timer`)
  and their 2 oneshot service units. `SYSTEMD_SERVICE` lists the 6 daemon services and the
  2 timers; the schedule `.service` units are `Unit=`-triggered by their timers and are
  deliberately NOT enabled directly.
- Scheduler: `src/schedule.py` (stdlib-only) is installed automatically by the upstream
  `Makefile` and needs no new runtime dependencies; the unit/timer files also land via
  `make install`, so `do_install` and `FILES:${PN}` require no changes.
- Runtime deps: `python3`, `python3-flask`, `python3-paho-mqtt`, `python3-bleak`, `bluez5`,
  `libgpiod-tools` (provides `gpioset`). No local MQTT broker is installed — the MQTT daemon
  connects to the external broker on the Home Assistant device (configured in `defaults.json`).
- Config: `/etc/thermostat/defaults.json` must be edited per-device (sensor MACs, MQTT broker
  host/credentials for the external Home Assistant broker).

## Related recipes

- `recipes-geraldpi/thermostat/thermostat.bb` is the *older* (pre-Matter) thermostat project.
  `recipes-geraldpi/images/geraldpi-thermostat.bb` still installs the old `thermostat` recipe.

## Systemd unit path gotcha (IMPORTANT)

The upstream `Makefile` treats `UNITDIR` as the **complete** destination
directory for unit files (it does NOT append `/system` itself). The recipe
therefore MUST pass `UNITDIR=${systemd_system_unitdir}` (i.e.
`/lib/systemd/system`), NOT `UNITDIR=${systemd_unitdir}` (`/lib/systemd`).

If `UNITDIR=${systemd_unitdir}` is used, the `.service` files land in
`${D}/lib/systemd/*.service` and the `systemd` bbclass's
`systemd_check_services()` (which only searches `${sysconfdir}/systemd/system`,
`${systemd_system_unitdir}`, and `${systemd_user_unitdir}`) will fail with:

```
ERROR: matter-hvac-thermostat-1.0+git-r0 do_package: Didn't find service unit 'thermostat-setup.service', specified in SYSTEMD_SERVICE:matter-hvac-thermostat.
```

Verified by local simulation: `make install DESTDIR=... UNITDIR=/lib/systemd/system`
puts all 10 units (6 services + 2 oneshot schedule services + 2 timers) in `lib/systemd/system/`.
