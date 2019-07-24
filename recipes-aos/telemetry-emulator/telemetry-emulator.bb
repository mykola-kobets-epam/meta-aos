DESCRIPTION = "AOS Telemetry Emulator"

DIR_TELEMETRY_EMULATOR = "${datadir}/telemetry_emulator"

SRCREV = "${AUTOREV}"
SRC_URI = " \
    git://git@gitpct.epam.com/epmd-aepr/demo_insurance;protocol=ssh;branch=develop \
    file://telemetry-emulator.service \
"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
    python3 \
    python3-core \
    python3-compression \
    python3-datetime \
    python3-json \
    python3-misc \
    python3-netserver \
    python3-shell \
    python3-threading \
"

FILES_${PN} = " \
    ${DIR_TELEMETRY_EMULATOR}/*.py \
    ${DIR_TELEMETRY_EMULATOR}/*.json \
"

do_install() {
    install -d ${D}${DIR_TELEMETRY_EMULATOR}
    install -m 0644 ${S}/backend/project/apps/telemetry_emulator/*.py ${D}${DIR_TELEMETRY_EMULATOR}
    install -m 0644 ${S}/backend/project/apps/telemetry_emulator/*.json ${D}${DIR_TELEMETRY_EMULATOR}
}

inherit systemd

SYSTEMD_SERVICE_${PN} = "telemetry-emulator.service"

FILES_${PN} += "${systemd_system_unitdir}/telemetry-emulator.service"

do_install_append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/*.service ${D}${systemd_system_unitdir}
}
