#!/usr/bin/env bash


set -e 

if [ -z $2 ]
then

	echo "Please specify both the gpio and the state desired"
	echo "To set GPIO 69 to high:"
	echo " > $0 69 1"
	exit -1
fi


cd /sys/class/gpio
# Export the GPIO if it has not been exported already
if [ ! -d gpio$1 ]
then
	echo $1 > export
fi

cd gpio$1
echo out > direction
echo $2 > value

