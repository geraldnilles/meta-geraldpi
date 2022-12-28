#!/usr/bin/env bash

# Thermostat Run Script. 
# This script will check the temperature and turn on the heat as needed.
# This shoudl be called periodically by a systemd routing


cd "$(dirname "$0")"

TARGET_MIN=$( ./temp_lookup.sh | awk '{ print $1 }' )
TARGET_MAX=$( ./temp_lookup.sh | awk '{ print $2 }' )
TARGET_DELTA=4

# Offset the schedule.txt file by providing a integer
TARGET_OFFSET=$( cat ./target_offset.txt )
TARGET_MIN=$(( $TARGET_MIN + $TARGET_OFFSET ))
TARGET_MAX=$(( $TARGET_MAX + $TARGET_OFFSET ))

# TODO Print out the temp from each room for journalctl log purposes

# Find the room with the minimum and maximum temperature
TEMP_MIN=$( ./min_temp.sh )
TEMP_MAX=$( ./max_temp.sh )
TEMP_DELTA=$(( $TEMP_MAX - $TEMP_MIN ))

# Shut off the system if no temp sensors found
if [ -z "$TEMP_MIN" ]
then
	echo "Not Temp Sensors Found"
	./off.sh
	exit
fi

# Determine the current state based on the GPIO state
STATE=$( ./get_state.sh )

echo $TARGET_MIN $TARGET_MAX $TARGET_DELTA $TEMP_MIN $TEMP_MAX $TEMP_DELTA $STATE

case $STATE in
	off)
		if [ $TEMP_DELTA -gt $TARGET_DELTA ] || [ $TEMP_MIN -lt $TARGET_MIN ] || [ $TEMP_MAX -gt $TARGET_MAX ]
		then
			./fan.sh
			exit
		fi

	;;
	fan)
		if [ $TEMP_DELTA -lt $TARGET_DELTA ] && [ $TEMP_MIN -gt $TARGET_MIN ] && [ $TEMP_MAX -lt $TARGET_MAX ]
		then
			./off.sh
			exit
		fi

		# Allow fan mode to shut off early if the Target temp is met,
		# but not exceeded as long as the delta between rooms is within
		# 1 degree
		if [ $TEMP_DELTA -lt 2 ] && [ $TEMP_MIN -ge $TARGET_MIN ] && [ $TEMP_MAX -le $TARGET_MAX ]
		then
			./off.sh
			exit
		fi

		if [ $TEMP_MIN -lt $TARGET_MIN ]
		then
			./heat.sh
			exit

		fi

		if [ $TEMP_MAX -gt $TARGET_MAX ]
		then
			./cool.sh
			exit

		fi

	;;
	cool)
		if [ $TEMP_MAX -lt $TARGET_MAX ]
		then
			./fan.sh
			exit

		fi

	;;
	heat)
		if [ $TEMP_MIN -gt $TARGET_MIN ]
		then
			./fan.sh
			exit

		fi

	;;
	*)
		echo "Invalid State"
	;;
esac

