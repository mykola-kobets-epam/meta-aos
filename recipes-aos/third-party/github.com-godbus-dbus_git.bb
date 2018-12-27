DESCRIPTION = "Native Go bindings for D-Bus"

GO_IMPORT = "github.com/godbus/dbus"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=09042bd5c6c96a2b9e45ddf1bc517eed"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""