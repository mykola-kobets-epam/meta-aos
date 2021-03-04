# This class generates partition image from WIC

PART_IMAGE_PARTNO ?= ""
PART_IMAGE_DIR ?= "${WORKDIR}/update"
PART_IMAGE_FILE ?= "${IMAGE_BASENAME}-${MACHINE}-p${PART_IMAGE_PARTNO}.gz"
PART_SOURCE_DIR ?= "${WORKDIR}/build-wic"

# Functions

do_create_part_image() {
    if [ -z ${PART_IMAGE_PARTNO} ] || [ ${PART_IMAGE_PARTNO} = "none" ]; then
        exit 0
    fi

    PART_FILE=$(find -L ${PART_SOURCE_DIR} -name "*direct.p${PART_IMAGE_PARTNO}")

    if [ -z ${PART_FILE} ]; then
        bbfatal "WIC partition not found"
    fi

    gzip -c ${PART_FILE} > ${PART_IMAGE_DIR}/${PART_IMAGE_FILE}
}
