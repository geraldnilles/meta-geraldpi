#!/usr/bin/env bash

# Thermostat Run Script. 
# This script will check the temperature and turn on the heat as needed.
# This shoudl be called periodically by a systemd routing

cd "$(dirname "$0")"

MIN_TEMP=68

temp=$( ./min_temp.sh )

echo Temp: $temp Set: $MIN_TEMP

if [ $temp -lt $MIN_TEMP ]
then
	echo "Turning Heat On"
	./heat.sh 1
fi 

if [ $temp -gt $(( $MIN_TEMP + 1 )) ]
then
	echo "Turning Heat Off"
	./heat.sh 0
fi

