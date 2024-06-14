create_imx6ull_versa_image () {

CALIXTO_IMAGE_PATH_SDCARD="${DEPLOY_DIR}/../../../calixto-images/${MACHINE}/sdcard"

mkdir -p ${CALIXTO_IMAGE_PATH_SDCARD}
rm -rf ${CALIXTO_IMAGE_PATH_SDCARD}/*

cp -v ${DEPLOY_DIR_IMAGE}/u-boot.imx ${CALIXTO_IMAGE_PATH_SDCARD}/u-boot-dtb.imx

cp ${DEPLOY_DIR}/../../../sources/meta-calixto-nxp/recipes-bsp/imx6ull-versa-flasher/imx6ull-versa-flasher-0.1/create-sdcard-boot.sh ${CALIXTO_IMAGE_PATH_SDCARD}

}

