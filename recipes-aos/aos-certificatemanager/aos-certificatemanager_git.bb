DESCRIPTION = "AOS Certificate Manager"

LICENSE = "CLOSED"

GO_IMPORT = "aos_certificatemanager"

SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitpct.epam.com/epmd-aepr/${GO_IMPORT}.git;protocol=ssh"

inherit go

# SM crashes if dynamic link selected, disable dynamic link till the problem is solved
GO_LINKSHARED = ""

# embed version
GO_LDFLAGS += '-ldflags="-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always` ${GO_RPATH} ${GO_LINKMODE} -extldflags '${GO_EXTLDFLAGS}'"'

# this flag is requied when GO_LINKSHARED is enabled
# LDFLAGS += "-lpthread"

RDEPENDS_${PN}-dev += " bash make"
RDEPENDS_${PN}-staticdev += " bash make"
