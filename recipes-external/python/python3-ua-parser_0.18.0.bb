SUMMARY = "Python port of Browserscope's user agent parser"
HOMEPAGE = "https://github.com/ua-parser/uap-python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI[sha256sum] = "db51f1b59bfaa82ed9e2a1d99a54d3e4153dddf99ac1435d51828165422e624e"

PYPI_PACKAGE = "ua-parser"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-pyyaml \
"

DEPENDS += " \
    ${PYTHON_PN}-pyyaml \
"
