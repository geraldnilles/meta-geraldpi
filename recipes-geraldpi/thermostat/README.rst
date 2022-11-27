##############
 Thermostat
##############


Roll Out Plan
=============

[X] Get BLE scanning working with pip as a proof of concept
[ ] Write python recipes for bleak and its dependencies so that they are built in and not loaded at runtime

    - bleak
    - async-timeout
    - dbus-fast

[ ] Systemd service for starting the script
[ ] Write script for pulling and averaging most recent measurements from each room
[ ] Write a script that toggles the GPIO

    - We can just use the sysfs nodes for driving the GPIOs
    - Relay 1 is GPIO 26. We can use this for heat 
    - Relay 2 is GPIO 20. We can use this for fan
    - Relay 3 is GPIO 21. We can use this for cooling 

[ ] Write a script that find the minimum temp for each room and returns 1 for heat, and 0 for off

[ ] Write a script that toggles the relay GPIO based on minimum temperature

[ ] Write a systemd timer that runs every minute and determines if the heat shoudl be on or off

[ ] Write a http server to graphs the

    - Need to add matplotlib as a dependency

[ ] Write a Flask app for setting the temperature

