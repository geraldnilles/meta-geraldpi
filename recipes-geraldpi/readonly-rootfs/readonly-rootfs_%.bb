DESCRIPTION = "This init script will mount the rootfs as Read-only with a tmpfs overlay"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = " \
    file://myinit.sh \
"

S = "${WORKDIR}"


do_install() {
        install -d ${D}/${base_sbindir}
	install -m 0755 ${S}/myinit.sh ${D}/${base_sbindir}
}


# Add "init=/sbin/myinit.sh" to the end of the /boot/cmdline.txt line to enable readonly


