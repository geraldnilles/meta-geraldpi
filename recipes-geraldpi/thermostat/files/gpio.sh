#!/usr/bin/env bash


set -e 

if [ -z $1 ]
then

	echo "Please specify a GPIO number"
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

# If the value is not specified, print the gpios's current value
if [ -z $2 ]
then
	cat value
else
	echo $2 > value
fi

