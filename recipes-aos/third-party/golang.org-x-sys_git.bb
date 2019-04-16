DESCRIPTION = "Go packages for low-level interaction with the operating system."

GO_IMPORT = "golang.org/x/sys"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/golang/sys;protocol=https"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
S = "${WORKDIR}/git"

RDEPENDS_${PN}-dev += "bash"

do_compile[noexec] = "1"

PTEST_ENABLED = ""
