DESCRIPTION = "OCI Runtime Specification http://www.opencontainers.org"

GO_IMPORT = "github.com/opencontainers/runtime-spec"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=b355a61a394a504dacde901c958f662c"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"
