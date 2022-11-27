#!/usr/bin/env python3

import asyncio

import scan
# import matplotlib.pyplot as plt

async def main():
    db = await scan.client()
    df = db[db["Temp"] > 50]
    rooms = db.Room.unique()
    for r in rooms:
	# Grap the last 5 reading and average them
        print(r,int(df[df["Room"] == r].tail(5)["Temp"].mean()),sep=";")

asyncio.run(main())

