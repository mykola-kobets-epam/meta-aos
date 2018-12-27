DESCRIPTION = "Cross-platform file system notifications for Go. https://fsnotify.org"

GO_IMPORT = "github.com/fsnotify/fsnotify"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=c38914c9a7ab03bb2b96d4baaee10769"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""