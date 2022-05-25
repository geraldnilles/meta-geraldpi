# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Fast, unopinionated, minimalist web framework"
HOMEPAGE = "http://expressjs.com/"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "BSD-3-Clause & ISC & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5513c00a5c36cd361da863dd9aa8875d \
                    file://node_modules/accepts/LICENSE;md5=bf1f9ad1e2e1d507aef4883fff7103de \
                    file://node_modules/array-flatten/LICENSE;md5=44088ba57cb871a58add36ce51b8de08 \
                    file://node_modules/body-parser/LICENSE;md5=0afd201e48c7d095454eed4ac1184e40 \
                    file://node_modules/bytes/LICENSE;md5=013e95467eddb048f19a6f5b42820f86 \
                    file://node_modules/call-bind/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
                    file://node_modules/content-disposition/LICENSE;md5=13babc4f212ce635d68da544339c962b \
                    file://node_modules/content-type/LICENSE;md5=f4b767f006864f81a4901347fe4efdab \
                    file://node_modules/cookie/LICENSE;md5=bc85b43b6f963e8ab3f88e63628448ca \
                    file://node_modules/debug/LICENSE;md5=ddd815a475e7338b0be7a14d8ee35a99 \
                    file://node_modules/depd/LICENSE;md5=ebc30494fd072dc98368da73e1821715 \
                    file://node_modules/destroy/LICENSE;md5=d5eb22cf6cc99e645b98b28ee3503ddf \
                    file://node_modules/ee-first/LICENSE;md5=c8d3a30332ecb31cfaf4c0a06da18f5c \
                    file://node_modules/encodeurl/LICENSE;md5=272621efa0ff4f18a73221e49ab60654 \
                    file://node_modules/escape-html/LICENSE;md5=f8746101546eeb9e4f6de64bb8bdf595 \
                    file://node_modules/etag/LICENSE;md5=6e8686b7b13dd7ac8733645a81842c4a \
                    file://node_modules/finalhandler/LICENSE;md5=462b10b32bb9175b97944aabef4aa171 \
                    file://node_modules/forwarded/LICENSE;md5=13babc4f212ce635d68da544339c962b \
                    file://node_modules/fresh/LICENSE;md5=373c2cf0978b37e434394a43b4cbbdb4 \
                    file://node_modules/function-bind/LICENSE;md5=e7417c1a8ad83f88bcac21ad440d48b2 \
                    file://node_modules/get-intrinsic/LICENSE;md5=0eb2c73daa0ecf037cbdf3d0bb0c98d5 \
                    file://node_modules/has-symbols/LICENSE;md5=afee57a289508ed4df3456667778aaf6 \
                    file://node_modules/has/LICENSE-MIT;md5=d000afc3c9ff3501a5610197db76a246 \
                    file://node_modules/http-errors/LICENSE;md5=607209623abfcc77b9098f71a0ef52f9 \
                    file://node_modules/iconv-lite/LICENSE;md5=f942263d98f0d75e0e0101884e86261d \
                    file://node_modules/inherits/LICENSE;md5=5b2ef2247af6d355ae9d9f988092d470 \
                    file://node_modules/ipaddr.js/LICENSE;md5=88f60a4b6e44cb849b5d907a7664c0ef \
                    file://node_modules/media-typer/LICENSE;md5=c6e0ce1e688c5ff16db06b7259e9cd20 \
                    file://node_modules/merge-descriptors/LICENSE;md5=aaf57ba8c5c9bf256fea7e943991a81a \
                    file://node_modules/methods/LICENSE;md5=c16a7dd9f946172f07086576d135d9d3 \
                    file://node_modules/mime-db/LICENSE;md5=175b28b58359f8b4a969c9ab7c828445 \
                    file://node_modules/mime-types/LICENSE;md5=bf1f9ad1e2e1d507aef4883fff7103de \
                    file://node_modules/mime/LICENSE;md5=8e8ea2ad138ce468f8570a0edbadea65 \
                    file://node_modules/ms/license.md;md5=fd56fd5f1860961dfa92d313167c37a6 \
                    file://node_modules/negotiator/LICENSE;md5=6417a862a5e35c17c904d9dda2cbd499 \
                    file://node_modules/object-inspect/LICENSE;md5=288162f1d1bfa064f127f2b42d2a656f \
                    file://node_modules/on-finished/LICENSE;md5=1b1f7f9cec194121fdf616b971df7a7b \
                    file://node_modules/parseurl/LICENSE;md5=e7842ed4f188e53e53c3e8d9c4807e89 \
                    file://node_modules/path-to-regexp/LICENSE;md5=44088ba57cb871a58add36ce51b8de08 \
                    file://node_modules/proxy-addr/LICENSE;md5=6e8686b7b13dd7ac8733645a81842c4a \
                    file://node_modules/qs/LICENSE.md;md5=b289135779dd930509ae81e6041690c0 \
                    file://node_modules/range-parser/LICENSE;md5=d4246fb961a4f121eef5ffca47f0b010 \
                    file://node_modules/raw-body/LICENSE;md5=f22163d3bc6b4bc1bbbdf654fe30af5b \
                    file://node_modules/safe-buffer/LICENSE;md5=badd5e91c737e7ffdf10b40c1f907761 \
                    file://node_modules/safer-buffer/LICENSE;md5=3baebc2a17b8f5bff04882cd0dc0f76e \
                    file://node_modules/send/LICENSE;md5=5f1a8369a899b128aaa8a59d60d00b40 \
                    file://node_modules/send/node_modules/ms/license.md;md5=2b8bc52ae6b7ba58e1629deabd53986f \
                    file://node_modules/serve-static/LICENSE;md5=27b1707520b14d0bc890f4e75cd387b0 \
                    file://node_modules/setprototypeof/LICENSE;md5=4846f1626304c2c0f806a539bbc7d54a \
                    file://node_modules/side-channel/LICENSE;md5=375dc7ca936a14e9c29418d5263bd066 \
                    file://node_modules/statuses/LICENSE;md5=36e2bc837ce69a98cc33a9e140d457e5 \
                    file://node_modules/toidentifier/LICENSE;md5=1a261071a044d02eb6f2bb47f51a3502 \
                    file://node_modules/type-is/LICENSE;md5=0afd201e48c7d095454eed4ac1184e40 \
                    file://node_modules/unpipe/LICENSE;md5=934ab86a8ab081ea0326add08d550739 \
                    file://node_modules/utils-merge/LICENSE;md5=1cf0906082187f374cb9a63c54eb782c \
                    file://node_modules/vary/LICENSE;md5=13babc4f212ce635d68da544339c962b \
                    file://node_modules/cookie-signature/Readme.md;md5=57ae8b42de3dd0c1f22d5f4cf191e15a"

SRC_URI = " \
    npm://registry.npmjs.org/;package=express;version=${PV} \
    npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json \
    "

inherit npm

S = "${WORKDIR}/npm"


LICENSE:${PN} = "MIT"
LICENSE:${PN}-accepts = "MIT"
LICENSE:${PN}-array-flatten = "MIT"
LICENSE:${PN}-body-parser = "MIT"
LICENSE:${PN}-bytes = "MIT"
LICENSE:${PN}-call-bind = "MIT"
LICENSE:${PN}-content-disposition = "MIT"
LICENSE:${PN}-content-type = "MIT"
LICENSE:${PN}-cookie = "MIT"
LICENSE:${PN}-cookie-signature = "Unknown"
LICENSE:${PN}-debug = "MIT"
LICENSE:${PN}-depd = "MIT"
LICENSE:${PN}-destroy = "MIT"
LICENSE:${PN}-ee-first = "MIT"
LICENSE:${PN}-encodeurl = "MIT"
LICENSE:${PN}-escape-html = "MIT"
LICENSE:${PN}-etag = "MIT"
LICENSE:${PN}-finalhandler = "MIT"
LICENSE:${PN}-forwarded = "MIT"
LICENSE:${PN}-fresh = "MIT"
LICENSE:${PN}-function-bind = "MIT"
LICENSE:${PN}-get-intrinsic = "MIT"
LICENSE:${PN}-has = "MIT"
LICENSE:${PN}-has-symbols = "MIT"
LICENSE:${PN}-http-errors = "MIT"
LICENSE:${PN}-iconv-lite = "MIT"
LICENSE:${PN}-inherits = "ISC"
LICENSE:${PN}-ipaddrjs = "MIT"
LICENSE:${PN}-media-typer = "MIT"
LICENSE:${PN}-merge-descriptors = "MIT"
LICENSE:${PN}-methods = "MIT"
LICENSE:${PN}-mime = "MIT"
LICENSE:${PN}-mime-db = "MIT"
LICENSE:${PN}-mime-types = "MIT"
LICENSE:${PN}-ms = "MIT"
LICENSE:${PN}-negotiator = "MIT"
LICENSE:${PN}-object-inspect = "MIT"
LICENSE:${PN}-on-finished = "MIT"
LICENSE:${PN}-parseurl = "MIT"
LICENSE:${PN}-path-to-regexp = "MIT"
LICENSE:${PN}-proxy-addr = "MIT"
LICENSE:${PN}-qs = "BSD-3-Clause"
LICENSE:${PN}-range-parser = "MIT"
LICENSE:${PN}-raw-body = "MIT"
LICENSE:${PN}-safe-buffer = "MIT"
LICENSE:${PN}-safer-buffer = "MIT"
LICENSE:${PN}-send-ms = "MIT"
LICENSE:${PN}-send = "MIT"
LICENSE:${PN}-serve-static = "MIT"
LICENSE:${PN}-setprototypeof = "ISC"
LICENSE:${PN}-side-channel = "MIT"
LICENSE:${PN}-statuses = "MIT"
LICENSE:${PN}-toidentifier = "MIT"
LICENSE:${PN}-type-is = "MIT"
LICENSE:${PN}-unpipe = "MIT"
LICENSE:${PN}-utils-merge = "MIT"
LICENSE:${PN}-vary = "MIT"
