DESCRIPTION = "Go utilities for performing shell-like word splitting/joining"

GO_IMPORT = "github.com/kballard/go-shellquote"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=9d95d1ad917c814c23909addb8692eeb"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""