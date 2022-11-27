SUMMARY = "Bluetooth Low Energy platform Agnostic Klient"
HOMEPAGE = "https://github.com/hbldh/bleak"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bcbc2069a86cba1b5e47253679f66ed7"

SRC_URI[sha256sum] = "87845a96453c58c19031c735444a7b3156800534bcd3f23ba74e119e9ae3cd88"

PYPI_PACKAGE = "bleak"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-async-timeout \
    ${PYTHON_PN}-dbus-fast \
"
