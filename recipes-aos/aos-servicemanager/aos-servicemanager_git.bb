DESCRIPTION = "AOS Service Manager"

LICENSE = "CLOSED"

GO_IMPORT = "gitpct.epam.com/epmd-aepr/aos_servicemanager"

SRCREV = "${AUTOREV}"
SRC_URI = "git://git@${GO_IMPORT}.git;protocol=ssh"

inherit go

S = "${WORKDIR}/git"

GO_INSTALL = "${GO_IMPORT}"

# embed version
GO_LDFLAGS += '-ldflags "-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always`"'

DEPENDS += "\
    github.com-anexia-it-fsquota \
    github.com-cavaliercoder-grab \
    github.com-coreos-go-iptables \
    github.com-coreos-go-systemd \
    github.com-fsnotify-fsnotify \
    github.com-godbus-dbus \
    github.com-google-uuid \
    github.com-gorilla-websocket \
    github.com-mattn-go-sqlite3 \
    github.com-opencontainers-runtime-spec \
    github.com-shirou-gopsutil \
    github.com-sirupsen-logrus \
    github.com-streadway-amqp \
"

RDEPENDS_${PN} += "\
    github.com-genuinetools-netns \
    github.com-opencontainers-runc \
    wondershaper \
"

RDEPENDS_${PN}-dev += "bash"
