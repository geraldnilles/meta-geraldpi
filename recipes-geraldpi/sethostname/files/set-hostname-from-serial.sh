#!/usr/bin/env bash

HOSTNAME=$( cat /etc/hostname )

# Get the serial number of the SoC
SERIAL=$( cat /sys/firmware/devicetree/base/serial-number )

# Grab the last 8 digits of the serial and add a dash to be beginning
SUFFIX="-${SERIAL: -8}"

# Set the new hostname
hostnamectl hostname "$HOSTNAME$SUFFIX"

