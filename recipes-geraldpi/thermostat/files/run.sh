#!/usr/bin/env bash

# Thermostat Run Script. 
# This script will check the temperature and turn on the heat as needed.
# This shoudl be called periodically by a systemd routing
#
# TODO Conver this into python so its eaiser to compare floats.  RIght now, we
# are converting everything to whole number which forces us to have a lot of
# hysteresis

cd "$(dirname "$0")"

MIN_TEMP=$( ./temp_lookup.sh )

temp=$( ./min_temp.sh )

# TODO If temp is blank, then no sensors were found. Heating and cooling shoudl
# be shut off

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

# TODO Add logic fro switching between heating and cooling mode
# Write the current mode into a /tmp file.  If the entire house temp exceeds
# the MIN threshold, then switch to heat.  If the entire house temp exceeds
# MAX, then switch to Cool.  
#
# My reasoning is becasue a particular temp sensor might be placed close to a
# vent and have a large swing.  I dont want to wildly toggle between heating
# and cooling

# TODO Add logic for turning on the Fan if the temp differential between rooms is > 5 degrees
# 	If Temp Diff > 5 dgrees and Fan is off,
# 		Turn on fan exit
# 	... Rest of logic
# THis will provide 1 cycle to try to even out temp before appling heat or cool
