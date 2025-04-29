FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://openssl-engine.conf"

HSM_MODULE_PATH ?= "${libdir}/softhsm/libsofthsm2.so"

do_install:append:class-target() {
    if ! grep -q "^\[engine_section\]" ${D}${sysconfdir}/ssl/openssl.cnf; then
        cat ${WORKDIR}/openssl-engine.conf >> ${D}${sysconfdir}/ssl/openssl.cnf
    fi
}

PREFERRED_VERSION_pkcs11-provider = "1.0"

RDEPENDS:${PN}:class-target += " \
    pkcs11-provider \
"
