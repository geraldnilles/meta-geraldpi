# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A clean, whitespace-sensitive template language for writing HTML"
HOMEPAGE = "https://pugjs.org"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   node_modules/jstransformer/LICENSE.md
#   node_modules/to-fast-properties/license
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "MIT & Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e264d99b2c2307c893730835de298429 \
                    file://node_modules/@babel/helper-validator-identifier/LICENSE;md5=b1d0cd283a346e919abb3beeb018279d \
                    file://node_modules/@babel/parser/LICENSE;md5=3b324af8e79986f4a5621efa85dd1292 \
                    file://node_modules/@babel/types/LICENSE;md5=b1d0cd283a346e919abb3beeb018279d \
                    file://node_modules/acorn/LICENSE;md5=f6e380cb54b7b0b1b2f8428b190f4f73 \
                    file://node_modules/asap/LICENSE.md;md5=6ef000dc4ca2360ae9216d401862c653 \
                    file://node_modules/babel-walk/LICENSE.md;md5=d16d4813d6a527afcc748f7b848a56f9 \
                    file://node_modules/call-bind/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
                    file://node_modules/character-parser/LICENSE;md5=298aca9f9e56a2ab1900d8733f8dbeda \
                    file://node_modules/constantinople/LICENSE;md5=9a1f717a6fc39face4c2fb590535b5be \
                    file://node_modules/doctypes/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/function-bind/LICENSE;md5=e7417c1a8ad83f88bcac21ad440d48b2 \
                    file://node_modules/get-intrinsic/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
                    file://node_modules/has-symbols/LICENSE;md5=afee57a289508ed4df3456667778aaf6 \
                    file://node_modules/has-tostringtag/LICENSE;md5=a026b78b6909aa5e21d77709fb6b5156 \
                    file://node_modules/has/LICENSE-MIT;md5=d000afc3c9ff3501a5610197db76a246 \
                    file://node_modules/is-core-module/LICENSE;md5=02b0fb5ff4014a08fd4193bc3e2349e2 \
                    file://node_modules/is-expression/LICENSE.md;md5=52ed80a2250256503a93672c6b8a0365 \
                    file://node_modules/is-promise/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/is-regex/LICENSE;md5=d22b3eb619d81197fd4f3ca47c2c1ea5 \
                    file://node_modules/js-stringify/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/jstransformer/LICENSE.md;md5=05a9d9f434d10446b6b1da1068d1caac \
                    file://node_modules/object-assign/license;md5=a12ebca0510a773644101a99a867d210 \
                    file://node_modules/path-parse/LICENSE;md5=4b940f9668dfcb796d2cb98ad94692df \
                    file://node_modules/promise/LICENSE;md5=f908224ab18e60b4c2428c7ef33361e7 \
                    file://node_modules/pug-attrs/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-code-gen/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-error/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-filters/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-filters/node_modules/resolve/LICENSE;md5=baa47288b5bd3e657a01886ce3dd0cb6 \
                    file://node_modules/pug-lexer/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-linker/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-load/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-parser/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-runtime/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-strip-comments/LICENSE.md;md5=be8462a3830aa4b5fabb849b47b6c7ed \
                    file://node_modules/pug-walk/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/supports-preserve-symlinks-flag/LICENSE;md5=d237eac07663bde2409de740ba75ec97 \
                    file://node_modules/to-fast-properties/license;md5=a3b54a26fb11cf7129550e6ffc7807ea \
                    file://node_modules/token-stream/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/void-elements/LICENSE;md5=59fd674729486594752aefa0ff5a385d \
                    file://node_modules/with/LICENSE;md5=10d1d4024d61361102888126d4347f7b \
                    file://node_modules/assert-never/README.md;md5=b2b231044301c7119389b985fcf0f903"

SRC_URI = " \
    npm://registry.npmjs.org/;package=pug;version=${PV} \
    npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json \
    "

S = "${WORKDIR}/npm"

inherit npm

LICENSE:${PN} = "MIT"
LICENSE:${PN}-babel-helper-validator-identifier = "MIT"
LICENSE:${PN}-babel-parser = "MIT"
LICENSE:${PN}-babel-types = "MIT"
LICENSE:${PN}-acorn = "MIT"
LICENSE:${PN}-asap = "MIT"
LICENSE:${PN}-assert-never = "Unknown"
LICENSE:${PN}-babel-walk = "MIT"
LICENSE:${PN}-call-bind = "MIT"
LICENSE:${PN}-character-parser = "MIT"
LICENSE:${PN}-constantinople = "MIT"
LICENSE:${PN}-doctypes = "MIT"
LICENSE:${PN}-function-bind = "MIT"
LICENSE:${PN}-get-intrinsic = "MIT"
LICENSE:${PN}-has = "MIT"
LICENSE:${PN}-has-symbols = "MIT"
LICENSE:${PN}-has-tostringtag = "MIT"
LICENSE:${PN}-is-core-module = "MIT"
LICENSE:${PN}-is-expression = "MIT"
LICENSE:${PN}-is-promise = "MIT"
LICENSE:${PN}-is-regex = "MIT"
LICENSE:${PN}-js-stringify = "MIT"
LICENSE:${PN}-jstransformer = "Unknown"
LICENSE:${PN}-object-assign = "MIT"
LICENSE:${PN}-path-parse = "MIT"
LICENSE:${PN}-promise = "MIT"
LICENSE:${PN}-pug-attrs = "MIT"
LICENSE:${PN}-pug-code-gen = "MIT"
LICENSE:${PN}-pug-error = "MIT"
LICENSE:${PN}-pug-filters-resolve = "MIT"
LICENSE:${PN}-pug-filters = "MIT"
LICENSE:${PN}-pug-lexer = "MIT"
LICENSE:${PN}-pug-linker = "MIT"
LICENSE:${PN}-pug-load = "MIT"
LICENSE:${PN}-pug-parser = "MIT"
LICENSE:${PN}-pug-runtime = "MIT"
LICENSE:${PN}-pug-strip-comments = "MIT"
LICENSE:${PN}-pug-walk = "MIT"
LICENSE:${PN}-supports-preserve-symlinks-flag = "MIT"
LICENSE:${PN}-to-fast-properties = "Unknown"
LICENSE:${PN}-token-stream = "MIT"
LICENSE:${PN}-void-elements = "MIT"
LICENSE:${PN}-with = "MIT"
