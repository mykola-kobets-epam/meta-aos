DESCRIPTION = "Native Go library and CLI for managing filesystem quotas"

GO_IMPORT = "github.com/anexia-it/fsquota"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=8b5fdfc4b7f131cb516b11ac975a19c5"
S = "${WORKDIR}/git"

RDEPENDS_${PN}-dev += "bash"

do_compile[noexec] = "1"
