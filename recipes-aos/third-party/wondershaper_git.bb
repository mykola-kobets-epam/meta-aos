DESCRIPTION = "Command-line utility for limiting an adapter's bandwidth"

SRCREV = "${AUTOREV}"
SRC_URI = "git://git@github.com/magnific0/wondershaper;protocol=https"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
S = "${WORKDIR}/git"

RDEPENDS_${PN} += "bash"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/wondershaper ${D}${bindir}
}
