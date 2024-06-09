DESCRIPTION = "Flashing tools"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy

FILESEXTRAPATHS:prepend = "${THISDIR}/imx6ull-versa-flasher-0.1:"

addtask deploy before do_build after do_compile

SRC_URI = "file://LICENSE \
	   file://create-sdcard-boot.sh \
	   file://flash_emmc.sh \
	   file://interfaces \
"

S = "${WORKDIR}"

FILES_${PN} = "${sysconfdir}/flash_emmc.sh \
               ${sysconfdir}/network/interfaces \
"

do_compile () {
	:
}

do_install() {
	install -d ${D}${sysconfdir}/
	install -d ${D}${sysconfdir}/network/
	
	install -m 0755 ${WORKDIR}/flash_emmc.sh ${D}${sysconfdir}/ 
	install -m 0755 ${WORKDIR}/interfaces ${D}${sysconfdir}/network/
	
}

do_deploy() {
	cp -p ${WORKDIR}/create-sdcard-boot.sh ${DEPLOY_DIR_IMAGE}/
}

