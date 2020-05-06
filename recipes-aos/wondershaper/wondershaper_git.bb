DESCRIPTION = "Command-line utility for limiting an adapter's bandwidth"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/magnific0/wondershaper;protocol=https"

S = "${WORKDIR}/git"

FILES_${PN} += "${bindir}/wondershaper"

RDEPENDS_${PN} += "bash"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/wondershaper ${D}${bindir}
}
