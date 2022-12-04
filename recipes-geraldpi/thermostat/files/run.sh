#!/usr/bin/env bash

# Thermostat Run Script. 
# This script will check the temperature and turn on the heat as needed.
# This shoudl be called periodically by a systemd routing
#
# TODO Conver this into python so its eaiser to compare floats.  RIght now, we
# are converting everything to whole number which forces us to have a lot of
# hysteresis

cd "$(dirname "$0")"

MIN_TEMP=69

temp=$( ./min_temp.sh )

echo Temp: $temp Set: $MIN_TEMP

# Rooms are too cold
if [ $temp -lt $MIN_TEMP ]
then
	# If heat is off, Turn it on
	if [ $( ./heat.sh ) -eq 0 ]
	then
		echo "Turning Heat On"
		./heat.sh 1
	fi
fi 

# Rooms are sufficiently warm
if [ $temp -gt $(( $MIN_TEMP + 0 )) ]
then
	# If heat is still on, shut if off
	if [ $( ./heat.sh ) -eq 1 ]
	then
		echo "Turning Heat Off"
		./heat.sh 0
	fi
fi

# TODO Add range for enabling cooling

