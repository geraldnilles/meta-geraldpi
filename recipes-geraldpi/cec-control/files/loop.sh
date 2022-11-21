#!/usr/bin/env bash

cd "$(dirname "$0")"

while true
do
	sleep 30
	IS_TV_ON=$( ./tv_status.sh )
	# If TV is already Off, do nothing
	if [ $IS_TV_ON -eq "0" ]
	then
		echo "TV is off.  Do Nothing"
		continue
	fi

	CHROMECAST_ACTIVE=$( ./chromecast_status.py )
	# If Chromecast is active, do nothing
	if [ $CHROMECAST_ACTIVE -eq "1" ]
	then
		echo "Chomecast is in use. Do Nothing"
		continue
	fi

	echo "Turning Off TV"
	./tv_off.sh
done

