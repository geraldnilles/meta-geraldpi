#!/usr/bin/env bash

cd "$(dirname "$0")"

ROOT_ONLY=1 ./full_ota_update.sh "$@"

