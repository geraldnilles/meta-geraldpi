DESCRIPTION = "A Python Flask Web app application for managing todos and grocery lists"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/todo-list.git;branch=main \
    file://no_venv.patch \
"

# Use this if you want to use a specific commit
# SRCREV = "[githash]"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

webapp_dir = "/opt/todo-list"

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	# Using the Static binary for maximum compatibility
	cp -R --no-dereference --preserve=mode,links -v ${S}/todos ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/scripts ${D}${webapp_dir}
	install -m 0755 ${S}/run.sh ${D}${webapp_dir}

	ln -s /media/todos ${D}${webapp_dir}/instance
}

FILES_${PN} += " \
	${systemd_unitdir}/* \
	${webapp_dir}/* \
"

RDEPENDS_${PN} += " \
	python3 \
	python3-flask \
"

SYSTEMD_SERVICE_${PN} = " todolist_webserver.service "


