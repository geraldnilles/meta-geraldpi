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
                    file://node_modules/function-bind/LICENSE;md5=e7417c1a8ad83f88bcac21ad440d48b2 \
                    file://node_modules/asap/LICENSE.md;md5=6ef000dc4ca2360ae9216d401862c653 \
                    file://node_modules/path-parse/LICENSE;md5=4b940f9668dfcb796d2cb98ad94692df \
                    file://node_modules/constantinople/LICENSE;md5=9a1f717a6fc39face4c2fb590535b5be \
                    file://node_modules/pug-runtime/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-parser/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/get-intrinsic/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
                    file://node_modules/@babel/types/LICENSE;md5=b1d0cd283a346e919abb3beeb018279d \
                    file://node_modules/@babel/helper-validator-identifier/LICENSE;md5=b1d0cd283a346e919abb3beeb018279d \
                    file://node_modules/@babel/parser/LICENSE;md5=3b324af8e79986f4a5621efa85dd1292 \
                    file://node_modules/babel-walk/LICENSE.md;md5=d16d4813d6a527afcc748f7b848a56f9 \
                    file://node_modules/doctypes/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/has-tostringtag/LICENSE;md5=a026b78b6909aa5e21d77709fb6b5156 \
                    file://node_modules/promise/LICENSE;md5=f908224ab18e60b4c2428c7ef33361e7 \
                    file://node_modules/acorn/LICENSE;md5=f6e380cb54b7b0b1b2f8428b190f4f73 \
                    file://node_modules/pug-filters/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/js-stringify/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/supports-preserve-symlinks-flag/LICENSE;md5=d237eac07663bde2409de740ba75ec97 \
                    file://node_modules/void-elements/LICENSE;md5=59fd674729486594752aefa0ff5a385d \
                    file://node_modules/jstransformer/LICENSE.md;md5=05a9d9f434d10446b6b1da1068d1caac \
                    file://node_modules/to-fast-properties/license;md5=a3b54a26fb11cf7129550e6ffc7807ea \
                    file://node_modules/pug-code-gen/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/pug-walk/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/call-bind/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
                    file://node_modules/is-regex/LICENSE;md5=d22b3eb619d81197fd4f3ca47c2c1ea5 \
                    file://node_modules/pug-attrs/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/object-assign/license;md5=a12ebca0510a773644101a99a867d210 \
                    file://node_modules/pug-error/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/has-symbols/LICENSE;md5=afee57a289508ed4df3456667778aaf6 \
                    file://node_modules/pug-strip-comments/LICENSE.md;md5=be8462a3830aa4b5fabb849b47b6c7ed \
                    file://node_modules/resolve/LICENSE;md5=baa47288b5bd3e657a01886ce3dd0cb6 \
                    file://node_modules/is-promise/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-lexer/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-linker/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/with/LICENSE;md5=10d1d4024d61361102888126d4347f7b \
                    file://node_modules/is-core-module/LICENSE;md5=02b0fb5ff4014a08fd4193bc3e2349e2 \
                    file://node_modules/token-stream/LICENSE;md5=b689321798b9c3969e0467719ddacf2e \
                    file://node_modules/pug-load/LICENSE;md5=8f0d967b27a23156bd4eda03ef97a278 \
                    file://node_modules/has/LICENSE-MIT;md5=d000afc3c9ff3501a5610197db76a246 \
                    file://node_modules/is-expression/LICENSE.md;md5=52ed80a2250256503a93672c6b8a0365 \
                    file://node_modules/character-parser/LICENSE;md5=298aca9f9e56a2ab1900d8733f8dbeda \
                    file://package.json;md5=44bb80f2109c38819495ef968ee40b84 \
                    file://node_modules/@babel/helper-validator-identifier/package.json;md5=5ba52580a442f0c3c011f949c4cabe3a \
                    file://node_modules/@babel/parser/package.json;md5=814a990080330a3e972ef7d027236866 \
                    file://node_modules/@babel/types/package.json;md5=69a9982749b5e1a593ad53f1aca09224 \
                    file://node_modules/acorn/package.json;md5=f4df9c4532d1d8b98d2403f383028cf2 \
                    file://node_modules/asap/package.json;md5=4c974dfa11d66358b3f8610ffcad5478 \
                    file://node_modules/assert-never/package.json;md5=789b7a06207407637a2d209b84de2577 \
                    file://node_modules/babel-walk/package.json;md5=20f9c1538138e33d4640489462f706c5 \
                    file://node_modules/call-bind/package.json;md5=9a9c527cb02ee791af16291838ddc217 \
                    file://node_modules/character-parser/package.json;md5=579aa334c102851e7d784d4b83c3e57b \
                    file://node_modules/constantinople/package.json;md5=550e52f8309bc04de631e92036178207 \
                    file://node_modules/doctypes/package.json;md5=b4cc7aed08e039f1769e0c240c4bf544 \
                    file://node_modules/function-bind/package.json;md5=f453e26c8d3482b4c3736f53303b4ec5 \
                    file://node_modules/get-intrinsic/package.json;md5=5445ffe22cf286a1bf0d086678090e95 \
                    file://node_modules/has/package.json;md5=2fee243336ba5aeebed1e0145472cd49 \
                    file://node_modules/has-symbols/package.json;md5=854e83356c304640e79edea88870cb14 \
                    file://node_modules/has-tostringtag/package.json;md5=0b95b6fe25951eb1cdcf57e0e56351ea \
                    file://node_modules/is-core-module/package.json;md5=ddc7455f799a9fd77a93c417371ff0ad \
                    file://node_modules/is-expression/package.json;md5=3c80a6d5fc6ecdf0459b9299b54999d0 \
                    file://node_modules/is-promise/package.json;md5=f3803c28a2708c0c03cd1ddd47166fd2 \
                    file://node_modules/is-regex/package.json;md5=308a8841e219cc70ec6aa142ceb4226f \
                    file://node_modules/js-stringify/package.json;md5=2f76f23b984048e5867ef697fd325d7d \
                    file://node_modules/jstransformer/package.json;md5=0505f527be6774003ed66910a6a68f5c \
                    file://node_modules/object-assign/package.json;md5=2854c33ba575a9ebc613d1a617ece277 \
                    file://node_modules/path-parse/package.json;md5=e225588668693d527d2a82f0db68088c \
                    file://node_modules/promise/package.json;md5=7975da9da536961f7510cdae6f0e97b8 \
                    file://node_modules/pug-attrs/package.json;md5=42d17c7d965497c078acdb9c13877f73 \
                    file://node_modules/pug-code-gen/package.json;md5=306bdf699237295e066b94f3291233ac \
                    file://node_modules/pug-error/package.json;md5=f710e104b7baaba4d03277b0457f6afe \
                    file://node_modules/pug-filters/package.json;md5=82dbb765e8837c0a4e7f090316a95f4c \
                    file://node_modules/pug-lexer/package.json;md5=e8f951bdedb680a1b7871316e021a029 \
                    file://node_modules/pug-linker/package.json;md5=6419ffaaa9e3e80dcfa9cbf81ec2b063 \
                    file://node_modules/pug-load/package.json;md5=cf21b355c0fb9062da99e15192e3be3a \
                    file://node_modules/pug-parser/package.json;md5=e74f4e2f0194db835cbdb5e707aa3ca0 \
                    file://node_modules/pug-runtime/package.json;md5=d3f70ab6548bfae84a3c54847a136e73 \
                    file://node_modules/pug-strip-comments/package.json;md5=588895ccc9d72b7bacbc99054fa52bd2 \
                    file://node_modules/pug-walk/package.json;md5=8035832e7ba5c779774a39deaaaa48b5 \
                    file://node_modules/resolve/package.json;md5=1d1ae6a12878c27497b6277f51669808 \
                    file://node_modules/supports-preserve-symlinks-flag/package.json;md5=bfaaddac07876305313de6edc3d38b5d \
                    file://node_modules/to-fast-properties/package.json;md5=d80e52bab996281e6eb8189e537d7dbf \
                    file://node_modules/token-stream/package.json;md5=a3de0f7be5a2bf0ae2ad2b8aef291fd6 \
                    file://node_modules/void-elements/package.json;md5=9a5d1e76e84764f00cd8ca9d8d361bf0 \
                    file://node_modules/with/package.json;md5=0cac5475358e68c22e6fcd3f2fed5ef3"

SRC_URI = " \
    npm://registry.npmjs.org/;package=pug;version=${PV} \
    npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json \
    "

S = "${WORKDIR}/npm"

inherit npm

LICENSE_${PN} = "MIT"
LICENSE_${PN}-babel-helper-validator-identifier = "MIT"
LICENSE_${PN}-babel-parser = "MIT"
LICENSE_${PN}-babel-types = "MIT"
LICENSE_${PN}-acorn = "MIT"
LICENSE_${PN}-asap = "MIT"
LICENSE_${PN}-assert-never = "Unknown"
LICENSE_${PN}-babel-walk = "MIT"
LICENSE_${PN}-call-bind = "MIT"
LICENSE_${PN}-character-parser = "MIT"
LICENSE_${PN}-constantinople = "MIT"
LICENSE_${PN}-doctypes = "MIT"
LICENSE_${PN}-function-bind = "MIT"
LICENSE_${PN}-get-intrinsic = "MIT"
LICENSE_${PN}-has = "MIT"
LICENSE_${PN}-has-symbols = "MIT"
LICENSE_${PN}-has-tostringtag = "MIT"
LICENSE_${PN}-is-core-module = "MIT"
LICENSE_${PN}-is-expression = "MIT"
LICENSE_${PN}-is-promise = "MIT"
LICENSE_${PN}-is-regex = "MIT"
LICENSE_${PN}-js-stringify = "MIT"
LICENSE_${PN}-jstransformer = "Unknown"
LICENSE_${PN}-object-assign = "MIT"
LICENSE_${PN}-path-parse = "MIT"
LICENSE_${PN}-promise = "MIT"
LICENSE_${PN}-pug-attrs = "MIT"
LICENSE_${PN}-pug-code-gen = "MIT"
LICENSE_${PN}-pug-error = "MIT"
LICENSE_${PN}-pug-filters = "MIT"
LICENSE_${PN}-pug-lexer = "MIT"
LICENSE_${PN}-pug-linker = "MIT"
LICENSE_${PN}-pug-load = "MIT"
LICENSE_${PN}-pug-parser = "MIT"
LICENSE_${PN}-pug-runtime = "MIT"
LICENSE_${PN}-pug-strip-comments = "MIT"
LICENSE_${PN}-pug-walk = "MIT"
LICENSE_${PN}-resolve = "MIT"
LICENSE_${PN}-supports-preserve-symlinks-flag = "MIT"
LICENSE_${PN}-to-fast-properties = "Unknown"
LICENSE_${PN}-token-stream = "MIT"
LICENSE_${PN}-void-elements = "MIT"
LICENSE_${PN}-with = "MIT"
