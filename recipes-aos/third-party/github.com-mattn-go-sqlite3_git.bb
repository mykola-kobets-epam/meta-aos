DESCRIPTION = "sqlite3 driver for go using database/sql http://mattn.github.io/go-sqlite3"

GO_IMPORT = "github.com/mattn/go-sqlite3"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=2b7590a6661bc1940f50329c495898c6"
S = "${WORKDIR}/git"

RDEPENDS_${PN}-dev += "bash"

do_compile[noexec] = "1"
