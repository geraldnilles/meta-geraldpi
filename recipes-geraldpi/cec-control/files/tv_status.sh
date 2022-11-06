#!/usr/bin/env bash


cd "$(dirname "$0")"
./configure.sh

cec-ctl -s --to 0 --give-device-power-status | grep "pwr-state: on" | wc -l

