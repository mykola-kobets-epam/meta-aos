DESCRIPTION = "AOS Service Manager"

LICENSE = "CLOSED"

GO_IMPORT = "aos_servicemanager"

BRANCH = "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitpct.epam.com/epmd-aepr/${GO_IMPORT}.git;branch=${BRANCH};protocol=ssh"

inherit go

AOS_SM_IDENTIFIERS ??= "visidentifier"
AOS_SM_CUSTOM_IDENTIFIERS ??= ""

# SM crashes if dynamic link selected, disable dynamic link till the problem is solved
GO_LINKSHARED = ""

# embed version
GO_LDFLAGS += '-ldflags="-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always` ${GO_RPATH} ${GO_LINKMODE} -extldflags '${GO_EXTLDFLAGS}'"'

# this flag is requied when GO_LINKSHARED is enabled
# LDFLAGS += "-lpthread"

DEPENDS = "systemd"

do_prepare_modules() {
    file="${S}/src/${GO_IMPORT}/identification/identification.go"

    echo 'package identification' > ${file}
    echo 'import (' >> ${file}

    for identifier in ${AOS_SM_IDENTIFIERS}; do
        echo "\t_ \"aos_servicemanager/identification/${identifier}\"" >> ${file}
    done

    for custom_identifier in ${AOS_SM_CUSTOM_IDENTIFIERS}; do
        echo "\t_ \"aos_servicemanager/${custom_identifier}\"" >> ${file}
    done

    echo ')' >> ${file}
}


RDEPENDS_${PN} += "\
    ca-certificates \
    iptables \
    openssl \
    quota \
    virtual/runc \
    wondershaper \
"

RDEPENDS_${PN}-dev += " bash make"
RDEPENDS_${PN}-staticdev += " bash make"
