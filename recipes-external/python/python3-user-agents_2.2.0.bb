SUMMARY = "A library to identify devices (phones, tablets) and their capabilities by parsing browser user agent strings."
HOMEPAGE = "https://github.com/selwin/python-user-agents"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI[sha256sum] = "d36d25178db65308d1458c5fa4ab39c9b2619377010130329f3955e7626ead26"

PYPI_PACKAGE = "user-agents"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-ua-parser \
"

