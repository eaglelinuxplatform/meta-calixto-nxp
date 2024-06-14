DESCRIPTION = "Flashing tools"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy

FILESEXTRAPATHS:prepend = "${THISDIR}/imx6ull-versa-flasher-0.1:"

# addtask deploy before do_build after do_compile

SRC_URI = "file://LICENSE \
	   file://flash_emmc.sh \
	   file://regulatory.db \
	   file://regulatory.db.p7s \
	   file://sdma-imx6q.bin \
"

S = "${WORKDIR}"

FILES:${PN} = "${sysconfdir}/flash_emmc.sh \
	       ${base_libdir}/firmware/regulatory.db \
	       ${base_libdir}/firmware/regulatory.db.p7s \
	       ${base_libdir}/firmware/imx/sdma/sdma-imx6q.bin \
"
do_compile () {
	:
}

do_install() {
	install -d ${D}${sysconfdir}/
	install -d ${D}${base_libdir}/firmware/imx/sdma/
	
	install -m 0755 ${WORKDIR}/flash_emmc.sh ${D}${sysconfdir}/ 
	install -m 0755 ${WORKDIR}/regulatory.db ${D}${base_libdir}/firmware/
	install -m 0755 ${WORKDIR}/regulatory.db.p7s ${D}${base_libdir}/firmware/	
	install -m 0755 ${WORKDIR}/sdma-imx6q.bin ${D}${base_libdir}/firmware/imx/sdma/	
}

# do_deploy() {
#	:
# }


