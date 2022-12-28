#!/usr/bin/env bash


set -e 

cd "$(dirname "$0")"

echo "Entering Heat Mode"

./gpio.sh 20 0 
./gpio.sh 26 1
./gpio.sh 21 0

