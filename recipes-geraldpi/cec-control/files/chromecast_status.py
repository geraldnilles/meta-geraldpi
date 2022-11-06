#!/usr/bin/env python3

import time
import pychromecast

# When these Apps are active, it is save to shutdown the TV
BLACKLIST = [
        "E8C28D3C", #Backdrop Screensaver
        ]

def get_status():
    chromecasts, browser = pychromecast.get_listed_chromecasts(
        friendly_names=["Bedroom TV"]
    )
    if not chromecasts:
        return 1

    cast = chromecasts[0]
    # Start socket client's worker thread and wait for initial status update
    cast.wait()

    app_id = cast.status.app_id
        
    browser.stop_discovery()

    if cast.status.app_id in BLACKLIST:
        return 0

    return 1

# Double check a Blacklist app is running before deciding to shut down
if get_status() == 0:
    time.sleep(10)
    if get_status() == 0:
        print(0)
    else:
        print(1)
else:
    print(1)

