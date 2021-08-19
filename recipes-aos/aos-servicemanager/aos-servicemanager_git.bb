DESCRIPTION = "AOS Service Manager"

LICENSE = "CLOSED"

GO_IMPORT = "aos_servicemanager"

BRANCH = "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitpct.epam.com/epmd-aepr/${GO_IMPORT}.git;branch=${BRANCH};protocol=ssh"

inherit go

# SM crashes if dynamic link selected, disable dynamic link till the problem is solved
GO_LINKSHARED = ""

# embed version
GO_LDFLAGS += '-ldflags="-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always` ${GO_RPATH} ${GO_LINKMODE} -extldflags '${GO_EXTLDFLAGS}'"'

# this flag is requied when GO_LINKSHARED is enabled
# LDFLAGS += "-lpthread"

DEPENDS = "systemd"

RDEPENDS_${PN} += "\
    ca-certificates \
    iptables \
    openssl \
    quota \
    cni \
    aos-firewall \
    aos-dnsname \
"

AOS_RUNNER ?= "runc"

RDEPENDS_${PN} += " ${@bb.utils.contains("AOS_RUNNER", "runc", " virtual/runc", "${AOS_RUNNER}", d)}"

RDEPENDS_${PN}-dev += " bash make"
RDEPENDS_${PN}-staticdev += " bash make"
