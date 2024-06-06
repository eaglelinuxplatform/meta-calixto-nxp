CALIXTO_IMG_PATH_COMMON = '${DEPOLY_DIR}/home/calixto-systems/yocto/calixto-images/${IMAGE_BASENAME}/${MACHINE}'
CALIXTO_IMG_PATH_SDCARD = '${DEPOLY_DIR}/home/calixto-systems/yocto/calixto-images/${IMAGE_BASENAME}/${MACHINE}/sdcard'

create_calixto_versa_image () {

mkdir -p ${CALIXTO_IMG_PATH_COMMON}
rm -rf ${CALIXTO_IMG_PATH_COMMON}/*

mkdir -p ${CALIXTO_IMG_PATH_SDCARD}
rm -rf ${CALIXTO_IMG_PATH_SDCARD}/*

cp -v ${DEPLOY_DIR_IMAGE}/"${PKGE}--${PKGV}-${PKGR}-${MACHINE}${IMAGE_VERSION_SUFFIX}" ${CALIXTO_IMG_PATH_SDCARD}/imx6ull-versa256.dtb

}


