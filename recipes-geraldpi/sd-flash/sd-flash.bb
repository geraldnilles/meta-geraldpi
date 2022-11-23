DESCRIPTION = "Scripts to packet with the deployed images for flashing and updaing the SD card"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy nopackages

SRC_URI = " \
    file://sd_create.sh \
"

S = "${WORKDIR}"

do_deploy() {
    install -d ${DEPLOYDIR}

    for i in ${S}/*.sh ; do
	cp $i ${DEPLOYDIR}/
    done
}

