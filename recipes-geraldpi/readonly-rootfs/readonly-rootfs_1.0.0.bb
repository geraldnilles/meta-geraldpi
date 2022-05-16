DESCRIPTION = "This init script will mount the rootfs as Read-only with a tmpfs overlay"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = " \
    file://myinit.sh \
    file://hostbasename \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/${base_sbindir}
    install -m 0755 ${S}/myinit.sh ${D}/${base_sbindir}

    install -d ${D}/${bindir}
    install -m 0755 ${S}/hostbasename ${D}/${bindir}
}

# THis recipe only moves the myinit file into the sbin directory, it does not
# enable read-only rootfs.  To actually enable it, Add "init=/sbin/myinit.sh"
# to the end of the /boot/cmdline.txt line.  This is done inside the gpoky
# distro config script


