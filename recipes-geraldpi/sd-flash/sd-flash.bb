DESCRIPTION = "Scripts to package with the deployed images for easier flashing of SD card"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy nopackages

SRC_URI = " \
    file://sd_update.sh \
    file://sd_create.sh \
    file://root_ota_update.sh \
    file://full_ota_update.sh \
"

S = "${WORKDIR}"

do_deploy() {
    install -d ${DEPLOYDIR}

    for i in ${S}/*.sh ; do
	cp $i ${DEPLOYDIR}/
    done
}

addtask deploy after do_compile
