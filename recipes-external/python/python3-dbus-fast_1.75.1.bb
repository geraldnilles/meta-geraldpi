SUMMARY = "A faster version of dbus-next"
HOMEPAGE = "https://github.com/bluetooth-devices/dbus-fast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=729e372b5ea0168438e4fd4a00a04947"

SRC_URI[sha256sum] = "3ac2e626296fd5aa4fba3a0f42b3e6ffc670f8e761ff82f251a43dfa0e9e4643"

PYPI_PACKAGE = "dbus-fast"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-async-timeout \
"
