DESCRIPTION = "AOS Update Manager"

GO_IMPORT = "gitpct.epam.com/epmd-aepr/aos_updatemanager"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://git@${GO_IMPORT}.git;protocol=ssh"
LICENSE = "CLOSED"
S = "${WORKDIR}/git"

RDEPENDS_${PN}-dev += "bash"

do_compile[noexec] = "1"

PTEST_ENABLED = ""
