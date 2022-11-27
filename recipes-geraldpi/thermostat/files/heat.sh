#!/usr/bin/env bash


set -e 

cd "$(dirname "$0")"

if [ -z $1 ]
then

	echo "Please specify if you want he heat on or off"
	echo "to enable the heat:"
	echo " > $0 1"
	exit -1
fi

./gpio.sh 26 $1

