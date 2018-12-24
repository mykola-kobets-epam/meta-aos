DESCRIPTION = "AOS VIS"

LICENSE = "CLOSED"

GO_IMPORT = "gitpct.epam.com/epmd-aepr/aos_vis"

SRCREV = "${AUTOREV}"
SRC_URI = "git://git@${GO_IMPORT}.git;protocol=ssh"

inherit go

PLUGINS = "\
    storageadapter \
    telemetryemulatoradapter \
"

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

FILES_${PN} += "${libdir}/*"

do_compile_append () {
    GOLIB="${B}/lib" 

    rm -rf ${GOLIB} ${B}/pkg
    mkdir ${GOLIB}

    for PLUGIN in ${PLUGINS}
    do
        cd ${B}/src/${GO_IMPORT}/plugins/${PLUGIN}
        ${GO} build ${GO_LINKSHARED} ${GOBUILDFLAGS} -buildmode=plugin -o ${GOLIB}/${PLUGIN}.so
    done
}

do_install_append() {
    install -d ${D}${libdir}/aos/plugins
    for f in ${B}/lib/*
    do
        base=`basename $f`
        install -m644 $f ${D}${libdir}/aos/plugins
    done
}
