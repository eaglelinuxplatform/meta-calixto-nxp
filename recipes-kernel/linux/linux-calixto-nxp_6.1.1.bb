DESCRIPTION = "Linux Kernel for nxp processors"
LICENSE = "GPL-2.0-only"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit kernel

#FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/eaglelinuxplatform/calixto-nxp-linux.git;branch=6.1.1;protocol=https;"

#SRC_URI += "file://defconfig"

KBUILD_DEFCONFIG_imx6ull-versa256 = "imx6ull_versa_default_defconfig"

S = "${WORKDIR}/git"

SRCREV = "a7ea257716dde10fc501c7c32e3432f92ba13def"
