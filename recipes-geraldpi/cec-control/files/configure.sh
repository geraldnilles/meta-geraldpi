#!/usr/bin/env bash

cd "$(dirname "$0")"

IS_CONFIGURED=$( cec-ctl | grep "Primary Device Type" | grep "Processor" | wc -l )

if [ $IS_CONFIGURED -eq "0" ]
then
	cec-ctl -s --processor > /dev/null
fi

