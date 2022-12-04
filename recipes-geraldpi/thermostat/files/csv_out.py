#!/usr/bin/env python3

##############################################################################
# CSV Out Script #
##################
# This reads all of the temperature from the last 24 hours and dumps it to a
# CSV file for offline review
##############################################################################

import asyncio

import scan

async def main():
    db = await scan.client()
    df = db[db["Temp"] > 50]
    df.to_csv("/tmp/log.csv")

asyncio.run(main())
