DESCRIPTION = "Linux Kernel for nxp processors"
LICENSE = "GPL-2.0-only"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit kernel

SRC_URI = "git://github.com/eaglelinuxplatform/calixto-nxp-linux.git;branch=6.1.1;protocol=https;"

SRC_URI += "file://defconfig \
	   file://imx6ull-calixto-versa256.dts \
	   file://imx6ull-calixto-versa512.dts \
	   file://imx6ull-calixto-versa1024.dts \
"
DEPENDS += "lzop-native"

#KBUILD_DEFCONFIG_imx6ull-versa256 = "imx6ull_versa_default_defconfig"

S = "${WORKDIR}/git"

SRCREV = "a7ea257716dde10fc501c7c32e3432f92ba13def"

FILESEXTRAPATHS:append = "${THISDIR}/files:"

do_configure:append() {  
    if [ "${MACHINE}" = "imx6ull-versa256" ]; then    
        cp ${WORKDIR}/imx6ull-calixto-versa256.dts ${WORKDIR}/git/arch/arm/boot/dts/imx6ull-calixto-versa.dts
        dts_base="imx6ull-calixto-versa" 
               
    elif [ ${MACHINE} = "imx6ull-versa512" ]; then
        cp ${WORKDIR}/imx6ull-calixto-versa512.dts ${WORKDIR}/git/arch/arm/boot/dts/imx6ull-calixto-versa.dts
        dts_base="imx6ull-calixto-versa" 
               
    elif [ ${MACHINE} = "imx6ull-versa1024" ]; then
        cp ${WORKDIR}/imx6ull-calixto-versa1024.dts ${WORKDIR}/git/arch/arm/boot/dts/imx6ull-calixto-versa.dts
        dts_base="imx6ull-calixto-versa"
    fi
    

    if ! grep -q "dtb-$(CONFIG_SOC_IMX6UL) += ${dts_base}.dtb" "${S}/arch/arm/boot/dts/Makefile"; then
        echo "dtb-$(CONFIG_SOC_IMX6UL) += ${dts_base}.dtb" >> "${S}/arch/arm/boot/dts/Makefile"
    fi
}
