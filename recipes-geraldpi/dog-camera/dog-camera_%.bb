DESCRIPTION = "An ExpressJS application for remotely accessing my house cameras"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = " \
    git://github.com/geraldnilles/DogCamera.git;branch=main;protocol=https \
    npm://registry.npmjs.org/;package=express;version=4.18.1 \
"

# Use this if you want to use a specific commit
# SRCREV = "12963c790fcbfd46b86b423bd16d16fa69827f76"

# Use this if you want to automatically pull the latest commit
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

webapp_dir = "/opt/DogCamera"


do_fetch() {
    # changing the home directory to the working directory, the .npmrc will be created in this directory
    export HOME=${WORKDIR}

    cd ${WORKDIR}
    mkdir -p node_dir
    cd node_dir

    # configure cache to be in working directory
    npm set cache ${WORKDIR}/npm_cache

    # clear local cache prior to each compile
    npm cache clear --force

    # compile and install node modules in source directory
    npm --arch=${TARGET_ARCH} --verbose install express pug
}

#do_configure() {
#	curl https://google.com
#}

do_install() {
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${S}/systemd/* ${D}/${systemd_unitdir}/system

        install -d ${D}${webapp_dir}
	# Using the Static binary for maximum compatibility
	cp -R --no-dereference --preserve=mode,links -v ${S}/bin ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/public ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/routes ${D}${webapp_dir}
	cp -R --no-dereference --preserve=mode,links -v ${S}/views ${D}${webapp_dir}
	install -m 0755 ${S}/app.js ${D}${webapp_dir}


	cp -R --no-dereference --preserve=mode,links -v ${WORKDIR}/node_dir/* ${D}${webapp_dir}

}


FILES:${PN} += " \
	${systemd_unitdir}/* \
	${webapp_dir}/* \
"

DEPENDS += " \
	nodejs \
	nodejs-native \
"

RDEPENDS:${PN} += " \
	ffmpeg \
	nodejs \
"

SYSTEMD_SERVICE:${PN} = " scam_ui.service "


