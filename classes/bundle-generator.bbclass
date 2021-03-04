# This class containes functions to generate update bundle

BUNDLE_DIR ?= "${DEPLOY_DIR_IMAGE}/update"
BUNDLE_FILE ?= "${IMAGE_BASENAME}-${MACHINE}-${PV}.bundle.tar"
BUNDLE_WORK_DIR ?= "${WORKDIR}/update"

do_pack_bundle() {
    tar cf ${BUNDLE_DIR}/${BUNDLE_FILE} -C ${BUNDLE_WORK_DIR}/ .
}
