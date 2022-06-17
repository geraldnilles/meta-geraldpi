DESCRIPTION = "Custom Scripts for scanning and merging PDFs"
SECTION = "libs/multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = " \
    file://scan \
    file://merge \
    file://completion.bash \
"

S = "${WORKDIR}"

do_install() {

        install -d ${D}/${bindir}
        install -m 0755 ${WORKDIR}/scan ${D}${bindir}
        install -m 0755 ${WORKDIR}/merge ${D}${bindir}

        install -d ${D}/${datadir}/bash-completion/completions
        install -m 0755 ${WORKDIR}/completion.bash ${D}/${datadir}/bash-completion/completions/scan

}

FILES:${PN} += " \
       ${datadir}/* \
"

RDEPENDS:${PN} = " \
	utsushi-git \
	tesseract \
	ghostscript \
	bash-completion \
	git \
"


