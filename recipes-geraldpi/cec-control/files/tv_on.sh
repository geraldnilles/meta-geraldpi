#!/usr/bin/env bash


cd "$(dirname "$0")"
./configure.sh

cec-ctl -s --to 0 --image-view-on
sleep 1
cec-ctl -s --to 0 --active-source phys-addr=1.0.0.0

