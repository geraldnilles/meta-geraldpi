#!/usr/bin/env bash

cd "$(dirname "$0")"

bluetoothctl power on

./scan.py
