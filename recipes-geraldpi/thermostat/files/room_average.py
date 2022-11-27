#!/usr/bin/env python3

import asyncio

import scan
# import matplotlib.pyplot as plt

async def main():
    db = await scan.client()
    df = db[db["Temp"] > 50]
    rooms = db.Room.unique()
    for r in rooms:
	# Grap the last 10 reading and average them
        print(r,df[df["Room"] == r].tail(5)["Temp"].mean(),sep=";")

asyncio.run(main())

