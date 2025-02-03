DESCRIPTION = "i.MX U-Boot suppporting i.MX reference boards."

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${THISDIR}../../LICENSE.txt;md5=6e28714b0b646413afae7df29356aa93"

SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
UBOOT_SRC ?= "git://github.com/Anzal-calixto/uboot-imx-calixto.git;protocol=https"
SRCBRANCH = "lf_v2024.04"
LOCALVERSION ?= "-${SRCBRANCH}"
SRCREV = "1b18a0c9814b9c97701e8463cc87b9a0c27d8a61"

DEPENDS += " \
    bc-native \
    bison-native \
    dtc-native \
    flex-native \
    gnutls-native \
    xxd-native \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit fsl-u-boot-localversion

BOOT_TOOLS = "imx-boot-tools"
