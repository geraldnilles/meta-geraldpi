
DESCRIPTION = "Hostname Ovewrite Package for Images"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"




do_install() {
	install -d ${D}${sysconfdir}
	echo "gpi-documents" > ${D}${sysconfdir}/hostname
}


RDEPENDS_${PN} = "\
    base-files \
"


