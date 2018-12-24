DESCRIPTION = "gopsutil: psutil for golang"

GO_IMPORT = "github.com/shirou/gopsutil"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=ed7522382cec5b7a6d6ebb8e30eed40e"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"
