DESCRIPTION = "A download manager package for Go"

GO_IMPORT = "github.com/cavaliercoder/grab"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=73a9fd187cdcda4e3009f73e69b5b348"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"
