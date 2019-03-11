DESCRIPTION = "Go client for AMQP 0.9.1 https://godoc.org/github.com/streadway/amqp"

GO_IMPORT = "github.com/streadway/amqp"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=29358571796140f6172846eac9be80d2"
S = "${WORKDIR}/git"

do_compile[noexec] = "1"

PTEST_ENABLED = ""