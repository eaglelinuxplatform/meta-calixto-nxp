DESCRIPTION = "u-boot bootloader for imx6ull processors"
UBOOT_SUFFIX ?= "img"

require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot-common.inc

inherit uboot-config

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCBRANCH = "2022.04"
SRC_URI = "git://github.com/eaglelinuxplatform/calixto-nxp-uboot.git;protocol=https;branch=${SRCBRANCH}"
SRCREV =  "a0c3bd360bc5ef8f0e97604e054f8a9bb2a2d66d"

DEPENDS += " \
	bc-native \
	bison-native \
	dtc-native \
	flex-native \
	lzop-native \	
	python3-setuptools-native \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
