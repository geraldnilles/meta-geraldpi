SUMMARY = "Write interactive web app in script way."
HOMEPAGE = "https://github.com/wang0618/PyWebIO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=729e372b5ea0168438e4fd4a00a04947"

SRC_URI[sha256sum] = "94589da5528958ed3a568d22fcb6892a372d234e41bc6a8169419dfff2d7b084"

PYPI_PACKAGE = "pywebio"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-tornado \
    ${PYTHON_PN}-user-agents \
"

