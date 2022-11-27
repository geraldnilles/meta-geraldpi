#!/usr/bin/env python3

import asyncio

import scan

async def main():
    db = await scan.client()
    df = db[db["Temp"] > 50]
    #ax = df[df["Room"] == "Bedroom Temperature"].plot(x="Time",y="Temp", ylabel="Bedroom")
    #df[df["Room"] == "Master Temperature"].plot(x="Time",y="Temp", ylabel="Master",ax=ax)
    #df.plot(x="Time",y="Temp")
    #plt.show()
    print(df.tail(50))

asyncio.run(main())

