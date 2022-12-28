#!/usr/bin/env bash


set -e 

cd "$(dirname "$0")"

echo "Entering Heat Mode"

./gpio.sh 20 1 # Not Required, but i think this makes more sense from a state trasition POV
./gpio.sh 26 1
./gpio.sh 21 0

