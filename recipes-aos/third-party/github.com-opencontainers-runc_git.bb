DESCRIPTION = "CLI tool for spawning and running containers according to the OCI specification https://www.opencontainers.org/"

GO_IMPORT = "github.com/opencontainers/runc"

inherit go

SRCREV = "${AUTOREV}"
SRC_URI = "git://${GO_IMPORT};protocol=https"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=435b266b3899aa8a959f17d41c56def8"
S = "${WORKDIR}/git"

RDEPENDS_${PN}-dev += "bash"