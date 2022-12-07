#!/usr/bin/env bash

# This will use the current time to determine the set temperature from the
# schedule.txt file

HOUR=$( date +%H )

cat schedule.txt | awk -v HOUR=$HOUR '$1==HOUR { print $2 }'

