#!/usr/bin/env bash

cd "$(dirname "$0")"
./configure.sh

cec-ctl -s --to 0 --standby

