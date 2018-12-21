DESCRIPTION = "Runc hook (OCI compatible) for setting up default bridge networking for containers."

GO_IMPORT = "github.com/genuinetools/netns"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=48ef0979a2bcc3fae14ff30b8a7f5dbf"
S = "${WORKDIR}/git"