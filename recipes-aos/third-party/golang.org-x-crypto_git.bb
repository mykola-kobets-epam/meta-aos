DESCRIPTION = "Go supplementary cryptography libraries."

GO_IMPORT = "golang.org/x/crypto"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/golang/crypto;protocol=https"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
S = "${WORKDIR}/git"

DEPENDS += "golang.org-x-sys"

do_compile[noexec] = "1"

PTEST_ENABLED = ""