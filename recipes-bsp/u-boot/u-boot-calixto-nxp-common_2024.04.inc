DESCRIPTION = "i.MX U-Boot suppporting i.MX reference boards."

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${THISDIR}../../LICENSE.txt;md5=6e28714b0b646413afae7df29356aa93"

SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
UBOOT_SRC ?= "git://github.com/eaglelinuxplatform/calixto-nxp-uboot.git;protocol=https"
SRCBRANCH = "2024.04"
LOCALVERSION ?= "-${SRCBRANCH}"
SRCREV = "aea33596bd043be70c36261c9e1425716dfe0349"

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
