SUMMARY = "Write interactive web app in script way."
HOMEPAGE = "https://github.com/wang0618/PyWebIO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI[sha256sum] = "94589da5528958ed3a568d22fcb6892a372d234e41bc6a8169419dfff2d7b084"

PYPI_PACKAGE = "pywebio"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-tornado \
    ${PYTHON_PN}-user-agents \
"

