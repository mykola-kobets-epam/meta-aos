DESCRIPTION = "A collection of go utility packages"

GO_IMPORT = "github.com/coreos/pkg"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=d2794c0df5b907fdace235a619d80314"
S = "${WORKDIR}/git"

DEPENDS += "systemd"

RDEPENDS_${PN}-dev += "bash"

do_compile[noexec] = "1"

PTEST_ENABLED = ""
