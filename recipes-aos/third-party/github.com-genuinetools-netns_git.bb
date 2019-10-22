DESCRIPTION = "Runc hook (OCI compatible) for setting up default bridge networking for containers."

GO_IMPORT = "github.com/genuinetools/netns"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=c8db5597ee6d9159c3df50ebf2b3c15e"
S = "${WORKDIR}/git"

PTEST_ENABLED = ""