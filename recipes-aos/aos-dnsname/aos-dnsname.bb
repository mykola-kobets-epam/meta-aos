DESCRIPTION = "DNS name CNI plugin"

GO_IMPORT = "aos_cni_dns"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

BRANCH = "master"
SRCREV = "74ae385e3bc7d0c8a218990d04f16d399d792f32"
SRC_URI = "git://git@gitpct.epam.com/epmd-aepr/${GO_IMPORT}.git;branch=${BRANCH};protocol=ssh"

inherit go
inherit goarch

# embed version
GO_LDFLAGS += '-ldflags="-X github.com/containernetworking/plugins/pkg/utils/buildversion.BuildVersion=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always` ${GO_RPATH} ${GO_LINKMODE} -extldflags '${GO_EXTLDFLAGS}'"'

do_compile() {
    cd ${S}/src/${GO_IMPORT}/
    ${GO} build -o ${B}/bin/dnsname ./plugins/meta/dnsname/
}

do_install() {
    localbindir="${libexecdir}/cni/"

    install -d ${D}${localbindir}
    install -m 755 ${B}/bin/dnsname ${D}${localbindir}
}

FILES_${PN} = "${libexecdir}/cni/*"

RDEPENDS_${PN} += "\
    dnsmasq \
"
