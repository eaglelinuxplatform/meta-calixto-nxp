# Copyright 2017-2024 CALIXTO SYSTEMS PVT LTD

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${THISDIR}../../LICENSE.txt;md5=6e28714b0b646413afae7df29356aa93"

SRC_URI = "${LINUX_IMX_SRC}"
LINUX_IMX_SRC ?= "git://github.com/eaglelinuxplatform/calixto-nxp-linux.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "6.6.y"
KBRANCH = "${SRCBRANCH}"
LOCALVERSION = "-lts-next"
SRCREV = "ecaa84c029fdb7e6bf9599611403fd2f49ef7234"

# PV is defined in the base in linux-imx.inc file and uses the LINUX_VERSION definition
# required by kernel-yocto.bbclass.
#
# LINUX_VERSION define should match to the kernel version referenced by SRC_URI and
# should be updated once patchlevel is merged.
LINUX_VERSION = "6.6.52"

KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

DEFAULT_PREFERENCE = "1"

DO_CONFIG_V7_COPY = "no"
DO_CONFIG_V7_COPY:mx6-nxp-bsp = "yes"
DO_CONFIG_V7_COPY:mx7-nxp-bsp = "yes"
DO_CONFIG_V7_COPY:mx8-nxp-bsp = "no"
DO_CONFIG_V7_COPY:mx9-nxp-bsp = "no"

# Add setting for LF Mainline build
#IMX_KERNEL_CONFIG_AARCH32 = "imx_v7_defconfig"
#IMX_KERNEL_CONFIG_AARCH64 = "imx_v8_defconfig"
KBUILD_DEFCONFIG ?= "defconfig"
#KBUILD_DEFCONFIG:mx6-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH32}"
#KBUILD_DEFCONFIG:mx7-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH32}"
#KBUILD_DEFCONFIG:mx8-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH64}"
#KBUILD_DEFCONFIG:mx9-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH64}"


do_configure:append(){

    kernel_dts_dir="${S}/arch/arm64/boot/dts/freescale"  # Adjust path as needed
    makefile_path="${kernel_dts_dir}/Makefile"
    # Check if MACHINE is "imx93-calixto-versa_1gb" and rename accordingly
    if [ "${MACHINE}" = "imx93-calixto-versa_1gb" ]; then
        original_dts="${kernel_dts_dir}/imx93-calixto-versa_1GB_NPU.dts"
        new_dts="${kernel_dts_dir}/imx93-calixto-versa.dts"
	dtb_filename="imx93-calixto-versa.dtb"

    fi

    if [ -f "$original_dts" ]; then
            cp "$original_dts" "$new_dts"
            echo "copied DTS file from $original_dts to $new_dts"
        else
            echo "Warning: DTS file $original_dts not found"
    fi


        # Append the DTB filename to the line with dtb-$(CONFIG_ARCH_MXC) +=
    echo "dtb-\$(CONFIG_ARCH_MXC) += $dtb_filename" >> "$makefile_path"

}
# Use a verbatim copy of the defconfig from the linux-imx repo.
# IMPORTANT: This task effectively disables kernel config fragments
# since the config fragments applied in do_kernel_configme are replaced.
addtask copy_defconfig after do_kernel_configme before do_kernel_localversion
do_copy_defconfig () {
    install -d ${B}
    if [ ${DO_CONFIG_V7_COPY} = "yes" ]; then
        # copy latest IMX_KERNEL_CONFIG_AARCH32 to use for mx6, mx6ul and mx7
        mkdir -p ${B}
        cp ${S}/arch/arm/configs/${IMX_KERNEL_CONFIG_AARCH32} ${B}/.config
    else
        # copy latest IMX_KERNEL_CONFIG_AARCH64 to use for mx8
        mkdir -p ${B}
        cp ${S}/arch/arm64/configs/${KBUILD_DEFCONFIG} ${B}/.config
    fi
}

DELTA_KERNEL_DEFCONFIG ?= ""
#DELTA_KERNEL_DEFCONFIG:mx8-nxp-bsp = "imx.config"

do_merge_delta_config[dirs] = "${B}"
do_merge_delta_config[depends] += " \
    flex-native:do_populate_sysroot \
    bison-native:do_populate_sysroot \
"
do_merge_delta_config() {
    for deltacfg in ${DELTA_KERNEL_DEFCONFIG}; do
        if [ -f ${S}/arch/${ARCH}/configs/${deltacfg} ]; then
            ${KERNEL_CONFIG_COMMAND}
            oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} ${deltacfg}
        elif [ -f "${WORKDIR}/${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${WORKDIR}/${deltacfg}
        elif [ -f "${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${deltacfg}
        fi
    done
    cp .config ${WORKDIR}/defconfig
}
addtask merge_delta_config before do_kernel_localversion after do_copy_defconfig

COMPATIBLE_MACHINE = "(imx-nxp-bsp)"
