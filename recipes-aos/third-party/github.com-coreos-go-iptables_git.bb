DESCRIPTION = "Go wrapper around iptables utility"

GO_IMPORT = "github.com/coreos/go-iptables"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=19cbd64715b51267a47bf3750cc6a8a5"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""