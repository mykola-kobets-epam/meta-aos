FILESEXTRAPATHS:prepend:aos-main-node := "${THISDIR}/files/main:"
FILESEXTRAPATHS:prepend:aos-secondary-node := "${THISDIR}/files/secondary:"

DESCRIPTION = "AOS Identity and Access Manager CPP"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

BRANCH = "feature_sm_cpp"
SRCREV = "${AUTOREV}"

SRC_URI = "gitsm://github.com/aosedge/aos_core_iam_cpp.git;protocol=https;branch=${BRANCH}"

SRC_URI += " \
    file://aos_iamanager.cfg \
    file://aos-iamanager.service \
    file://aos-iamanager-provisioning.service \
    file://aos-target.conf \
    file://aos-dirs-service.conf \
"

DEPENDS += "poco systemd grpc grpc-native protobuf-native protobuf openssl curl"

OECMAKE_GENERATOR = "Unix Makefiles"
EXTRA_OECMAKE += "-DFETCHCONTENT_FULLY_DISCONNECTED=OFF"

inherit autotools pkgconfig cmake systemd

SYSTEMD_SERVICE:${PN} = "aos-iamanager.service aos-iamanager-provisioning.service"

MIGRATION_SCRIPTS_PATH = "${base_prefix}/usr/share/aos/iam/migration"

FILES:${PN} += " \
    ${sysconfdir} \
    ${MIGRATION_SCRIPTS_PATH} \
"

RDEPENDS:${PN} += " \
    aos-rootca \
    aos-provfinish \
"

S = "${WORKDIR}/git"

do_compile[network] = "1"
do_configure[network] =  "1"

do_fetch[vardeps] += "AOS_MAIN_NODE AOS_MAIN_NODE_HOSTNAME AOS_NODE_HOSTNAME AOS_NODE_TYPE AOS_RUNNER"

python do_update_config() {
    import json

    file_name = oe.path.join(d.getVar("D"), d.getVar("sysconfdir"), "aos", "aos_iamanager.cfg")

    with open(file_name) as f:
        data = json.load(f)

    node_info = data.get("NodeInfo", {})
    node_info["NodeType"] = d.getVar("AOS_NODE_TYPE")

    # Set Node Attributes
    aos_runner = d.getVar("AOS_RUNNER")
    node_attributes = node_info.get("Attrs", {})
    node_attributes["NodeRunners"] = aos_runner

    node_info["Attrs"] = node_attributes

    data["NodeInfo"] = node_info

    main_node_host_name = d.getVar("AOS_MAIN_NODE_HOSTNAME")

    # Set main IAM server URLs for secondary IAM nodes
    if not d.getVar("AOS_MAIN_NODE") or d.getVar("AOS_MAIN_NODE") == "0":
        data["MainIAMPublicServerURL"] = main_node_host_name+":8090"
        data["MainIAMProtectedServerURL"] = main_node_host_name+":8089"

    # Set alternative names for server certificates

    for cert_module in data["CertModules"]:
        if "ExtendedKeyUsage" in cert_module and "serverAuth" in cert_module["ExtendedKeyUsage"]:
            cert_module["AlternativeNames"] = [d.getVar("AOS_NODE_HOSTNAME")]

    with open(file_name, "w") as f:
        json.dump(data, f, indent=4)
}

do_install:append() {
    install -d ${D}${sysconfdir}/aos
    install -m 0644 ${WORKDIR}/aos_iamanager.cfg ${D}${sysconfdir}/aos

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/aos-iamanager.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/aos-iamanager-provisioning.service ${D}${systemd_system_unitdir}

    install -d ${D}${sysconfdir}/systemd/system/aos-iamanager-provisioning.service.d
    install -m 0644 ${WORKDIR}/aos-dirs-service.conf ${D}${sysconfdir}/systemd/system/aos-iamanager-provisioning.service.d/20-aos-dirs-service.conf

    install -d ${D}${sysconfdir}/systemd/system/aos.target.d
    install -m 0644 ${WORKDIR}/aos-target.conf ${D}${sysconfdir}/systemd/system/aos.target.d/${PN}.conf

    install -d ${D}${MIGRATION_SCRIPTS_PATH}
    source_migration_path="/src/database/migration"
    if [ -d ${S}${source_migration_path} ]; then
        install -m 0644 ${S}${source_migration_path}/* ${D}${MIGRATION_SCRIPTS_PATH}
    fi
}

addtask update_config after do_install before do_package
