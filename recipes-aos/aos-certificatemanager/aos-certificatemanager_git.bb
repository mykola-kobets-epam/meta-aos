DESCRIPTION = "AOS Certificate Manager"

LICENSE = "CLOSED"

GO_IMPORT = "aos_certificatemanager"

BRANCH = "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitpct.epam.com/epmd-aepr/${GO_IMPORT}.git;branch=${BRANCH};protocol=ssh"

inherit go

AOS_CM_CERT_MODULES ??= "swmodule"
AOS_CM_CUSTOM_CERT_MODULES ??= ""

# SM crashes if dynamic link selected, disable dynamic link till the problem is solved
GO_LINKSHARED = ""

# embed version
GO_LDFLAGS += '-ldflags="-X main.GitSummary=`git --git-dir=${S}/src/${GO_IMPORT}/.git describe --tags --always` ${GO_RPATH} ${GO_LINKMODE} -extldflags '${GO_EXTLDFLAGS}'"'

# this flag is requied when GO_LINKSHARED is enabled
# LDFLAGS += "-lpthread"

do_prepare_modules() {
    file="${S}/src/${GO_IMPORT}/certmodules/modules.go"

    echo 'package certmodules' > ${file}
    echo 'import (' >> ${file}

    for module in ${AOS_CM_CERT_MODULES}; do
        echo "\t_ \"aos_certificatemanager/certmodules/${module}\"" >> ${file}
    done

    for custom_module in ${AOS_CM_CUSTOM_CERT_MODULES}; do
        echo "\t_ \"aos_certificatemanager/${custom_module}\"" >> ${file}
    done

    echo ')' >> ${file}
}

RDEPENDS_${PN} += "\
    ca-certificates \
    openssl \
"

RDEPENDS_${PN}-dev += " bash make"
RDEPENDS_${PN}-staticdev += " bash make"

addtask prepare_modules after do_unpack before do_compile