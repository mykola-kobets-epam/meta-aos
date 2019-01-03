DESCRIPTION = "AOS VIS"

LICENSE = "CLOSED"

GO_IMPORT = "gitpct.epam.com/epmd-aepr/aos_vis"

SRCREV = "${AUTOREV}"
SRC_URI = "\
    git://git@${GO_IMPORT}.git;protocol=ssh \
    file://0001-dataadapter-Make-storageadapter-and-telemetryemulato.patch \
"

inherit go

S = "${WORKDIR}/git"

GO_INSTALL = "${GO_IMPORT}"

# embed version
GO_LDFLAGS += '-ldflags "-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always`"'

DEPENDS += "\
    github.com-godbus-dbus \
    github.com-gorilla-websocket \
    github.com-sirupsen-logrus \
"

RDEPENDS_${PN} += "\
    ca-certificates \
    iptables \
    openssl \
"
