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
    ca-certificates \
    github.com-genuinetools-netns \
    github.com-opencontainers-runc \
    iptables \
    openssl \
    python3 \
    python3-compression \
    python3-crypt \
    python3-enum \
    python3-json \
    python3-misc \
    python3-selectors \
    python3-shell \
    python3-six \
    python3-threading \
    python3-websocket-client \
    quota \
    wondershaper \
"

RDEPENDS_${PN}-dev += "bash"
