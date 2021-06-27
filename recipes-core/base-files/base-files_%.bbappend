

hostname = ""

do_install_append() {

	echo "LABEL=documents	/media	ext4	defaults,nofail	0	0" >> ${D}${sysconfdir}/fstab

}
