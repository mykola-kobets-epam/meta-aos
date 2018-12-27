DESCRIPTION = "A WebSocket implementation for Go."

GO_IMPORT = "github.com/gorilla/websocket"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=c007b54a1743d596f46b2748d9f8c044"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""