DESCRIPTION = "A basic implementation of scp for go."

GO_IMPORT = "github.com/tmc/scp"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=6a9aec2aa222ac058a8379bee740b4f9"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""