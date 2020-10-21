DESCRIPTION = "AOS VIS"

LICENSE = "CLOSED"

GO_IMPORT = "aos_vis"

BRANCH = "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitpct.epam.com/epmd-aepr/${GO_IMPORT}.git;branch=${BRANCH};protocol=ssh"

inherit go

PLUGINS ?= "storageadapter telemetryemulatoradapter renesassimulatoradapter"

# SM crashes if dynamic link selected, disable dynamic link till the problem is solved
GO_LINKSHARED = ""

# embed version
GO_LDFLAGS += '-ldflags="-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always` ${GO_RPATH} ${GO_LINKMODE} -extldflags '${GO_EXTLDFLAGS}'"'

RDEPENDS_${PN} += "\
    ca-certificates \
    openssl \
"

RDEPENDS_${PN}-dev += " bash make"
RDEPENDS_${PN}-staticdev += " bash make"
