# Copyright 2017-2026 CALIXTO SYSTEMS PVT LTD

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "coreutils-native"
SRC_URI = "${LINUX_IMX_SRC}"
LINUX_IMX_SRC ?= "git://github.com/eaglelinuxplatform/calixto-nxp-linux.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "6.18.20"
KBRANCH = "${SRCBRANCH}"
SRCREV = "500f9264d517df00512adb5c8fa6e8e952281420"

# PV is defined in the base in linux-imx.inc file and uses the LINUX_VERSION definition
# required by kernel-yocto.bbclass.
#
# LINUX_VERSION define should match to the kernel version referenced by SRC_URI and
# should be updated once patchlevel is merged.
LINUX_VERSION = "6.18.20"
# FIXME: Drop this line once LINUX_VERSION is stable and set correctly
KERNEL_VERSION_SANITY_SKIP = "1"
LOCALVERSION = "-2.0.0"

#KBUILD_DEFCONFIG:mx6-generic-bsp = "imx_v7_defconfig"
#KBUILD_DEFCONFIG:mx7-generic-bsp = "imx_v7_defconfig"
#KBUILD_DEFCONFIG:mx8-generic-bsp = "imx_v8_defconfig"
#KBUILD_DEFCONFIG:mx9-generic-bsp = "imx_v8_defconfig"
KBUILD_DEFCONFIG ?= "imx_v8_defconfig"

#DEFAULT_PREFERENCE = "1"

do_configure:append(){

    kernel_dts_dir="${S}/arch/arm64/boot/dts/freescale"  # Adjust path as needed
    kernel_arm32_dir="${S}/arch/arm/boot/dts/nxp/imx"
    makefile_path="${kernel_dts_dir}/Makefile"
    arm32_makefile_path="${kernel_arm32_dir}/Makefile"
    # Check if MACHINE is "imx93-calixto-versa_1gb" and rename accordingly
    if [ "${MACHINE}" = "imx93-calixto-versa_1gb" ]; then
        original_dts="${kernel_dts_dir}/imx93-calixto-versa_1GB.dts"
        new_dts="${kernel_dts_dir}/imx93-calixto-versa.dts"
        dtb_filename="imx93-calixto-versa.dtb"
    elif [ "${MACHINE}" = "imx93-calixto-versa_2gb" ]; then
        original_dts="${kernel_dts_dir}/imx93-calixto-versa_2GB.dts"
        new_dts="${kernel_dts_dir}/imx93-calixto-versa.dts"
        dtb_filename="imx93-calixto-versa.dtb"
   elif [ "${MACHINE}" = "imx8mp-calixto-optima_2gb" ]; then
        original_dts="${kernel_dts_dir}/imx8mp-calixto-optima_2GB.dts"
        new_dts="${kernel_dts_dir}/imx8mp-calixto-optima.dts"
        dtb_filename="imx8mp-calixto-optima.dtb"
   elif [ "${MACHINE}" = "imx8mp-calixto-optima_4gb" ]; then
        original_dts="${kernel_dts_dir}/imx8mp-calixto-optima_4GB.dts"
        new_dts="${kernel_dts_dir}/imx8mp-calixto-optima.dts"
        dtb_filename="imx8mp-calixto-optima.dtb"
    fi

    if [ -f "$original_dts" ]; then
            cp "$original_dts" "$new_dts"
            echo "copied DTS file from $original_dts to $new_dts"
        else
            echo "Warning: DTS file $original_dts not found"
    fi

    if ["$arch32_flag" = "1"]; then
            echo "dtb-\$(CONFIG_SOC_IMX6UL) += $dtb_filename" >> "$arm32_makefile_path"
        else
            # Append the DTB filename to the line with dtb-$(CONFIG_ARCH_MXC) +=
            echo "dtb-\$(CONFIG_ARCH_MXC) += $dtb_filename" >> "$makefile_path"
    fi
}

python __anonymous () {
    import bb
    # Fail fast if DELTA_KERNEL_DEFCONFIG is present in the datastore (even if empty)
    if "DELTA_KERNEL_DEFCONFIG" in d.keys():
        val = d.getVar("DELTA_KERNEL_DEFCONFIG", expand=False)
        bb.error(f"Detected deprecated/unsupported variable 'DELTA_KERNEL_DEFCONFIG' (value: '{val}').")
        bb.fatal("Please remove 'DELTA_KERNEL_DEFCONFIG' and use supported kernel configuration methods, "
                 "e.g., configuration fragments via kernel-yocto or a maintained defconfig.")
}

do_deploy:append() {
    if [ ${@bb.utils.filter('UBOOT_CONFIG', 'crrm', d)} ]; then
        baseName=${KERNEL_IMAGETYPE}-${KERNEL_IMAGE_NAME}
        gzip -c ${DEPLOYDIR}/$baseName${KERNEL_IMAGE_BIN_EXT} > \
            ${DEPLOYDIR}/$baseName${KERNEL_IMAGE_BIN_EXT}.gz
        ln -sf $baseName${KERNEL_IMAGE_BIN_EXT}.gz $deployDir/${KERNEL_IMAGETYPE}.gz
        # FIXME: For now, the CRRM kernel is just a copy of the regular kernel
        ln -sf $baseName${KERNEL_IMAGE_BIN_EXT}    $deployDir/${KERNEL_IMAGETYPE}_crrm
        ln -sf $baseName${KERNEL_IMAGE_BIN_EXT}.gz $deployDir/${KERNEL_IMAGETYPE}_crrm.gz
    fi
}

COMPATIBLE_MACHINE = "(imx-nxp-bsp)"

CVE_STATUS_GROUPS = "CVE_STATUS_KERNEL"
CVE_STATUS_KERNEL = " \
    CVE-2026-31436 CVE-2026-31444 CVE-2026-31448 CVE-2026-31478 CVE-2026-43067 CVE-2026-31414 CVE-2026-31682 CVE-2026-43011 \
    CVE-2026-43037 CVE-2026-43038 CVE-2026-43341 CVE-2026-31533 CVE-2026-31633 CVE-2026-31636 CVE-2026-31637 CVE-2026-31649 \
    CVE-2026-31657 CVE-2026-31659 CVE-2026-31668 CVE-2026-31669 CVE-2026-31607 CVE-2026-31608 CVE-2026-31609 CVE-2026-31685 \
    CVE-2026-43071 CVE-2026-43083 CVE-2026-43114 CVE-2026-43117 CVE-2026-31705 CVE-2026-31718 CVE-2026-31589 CVE-2026-43493 \
    CVE-2026-43501 CVE-2026-45988 CVE-2026-46039 CVE-2026-46043 CVE-2026-46115 CVE-2026-46119 CVE-2026-46135 CVE-2026-46137 \
    CVE-2026-46155 CVE-2026-46185 CVE-2026-46195 CVE-2026-31431 CVE-2026-31635 CVE-2026-43284 CVE-2026-43500 CVE-2026-46300 \
    CVE-2026-46333 \
"
CVE_STATUS_KERNEL[status] = "cpe-stable-backport: Backported in NXP LTS Kernel 6.18.20"
