SUMMARY = "Python port of Browserscope's user agent parser"
HOMEPAGE = "https://github.com/ua-parser/uap-python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=729e372b5ea0168438e4fd4a00a04947"

SRC_URI[sha256sum] = "db51f1b59bfaa82ed9e2a1d99a54d3e4153dddf99ac1435d51828165422e624e"

PYPI_PACKAGE = "ua-parser"

inherit pypi python_setuptools_build_meta

