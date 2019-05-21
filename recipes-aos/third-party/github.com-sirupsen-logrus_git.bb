DESCRIPTION = "Structured, pluggable logging for Go."

GO_IMPORT = "github.com/sirupsen/logrus"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=8dadfef729c08ec4e631c4f6fc5d43a0"
S = "${WORKDIR}/git"

DEPENDS += "golang.org-x-crypto"
RDEPENDS_${PN}-dev += "bash"

do_compile[noexec] = "1"

PTEST_ENABLED = ""
