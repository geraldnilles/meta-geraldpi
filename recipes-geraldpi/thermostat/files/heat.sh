#!/usr/bin/env bash


set -e 

cd "$(dirname "$0")"

./gpio.sh 26 $1

