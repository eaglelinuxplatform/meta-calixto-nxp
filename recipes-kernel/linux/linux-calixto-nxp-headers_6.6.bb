# Copyright 2017-2024 CALIXTO SYSTEMS PVT LTD

SUMMARY = "Installs i.MX-specific kernel headers"
DESCRIPTION = "Installs i.MX-specific kernel headers to userspace. \
New headers are installed in ${includedir}/imx."
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${THISDIR}../../LICENSE.txt;md5=6e28714b0b646413afae7df29356aa93"

SRC_URI = "${LINUX_IMX_SRC}"
LINUX_IMX_SRC ?= "git://github.com/eaglelinuxplatform/calixto-nxp-linux.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "6.6.y"
LOCALVERSION = "-lts-${SRCBRANCH}"
SRCREV = "c50b70616480f6e8a6cf7b48d5ffd535bd230083"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile[noexec] = "1"

IMX_UAPI_HEADERS = " \
    dma-buf.h \
    hantrodec.h \
    hx280enc.h \
    ipu.h \
    imx_vpu.h \
    mxc_asrc.h \
    mxc_dcic.h \
    mxc_mlb.h \
    mxc_sim_interface.h \
    mxc_v4l2.h \
    mxcfb.h \
    pxp_device.h \
    pxp_dma.h \
    version.h \
    videodev2.h \
"

do_install() {
    # We install all headers inside of B so we can copy only the
    # i.MX-specific ones, and there is no risk of a new header to be
    # installed by mistake.
    oe_runmake headers_install INSTALL_HDR_PATH=${B}${exec_prefix}

    ################################################
    # BEGIN Copy of exceptional logic from linux-libc-headers
    # Kernel should not be exporting this header
    rm -f ${B}${exec_prefix}/include/scsi/scsi.h

    # The ..install.cmd conflicts between various configure runs
    find ${B}${includedir} -name ..install.cmd | xargs rm -f
    # END Copy from linux-libc-headers
    ################################################

    # Install i.MX-specific headers only
    for h in ${IMX_UAPI_HEADERS}; do
        install -D -m 0644 ${B}${includedir}/linux/$h \
                       ${D}${includedir}/imx/linux/$h
    done
}

# Allow to build empty main package, this is required in order for -dev package
# to be propagated into the SDK
#
# Without this setting the RDEPENDS in other recipes fails to find this
# package, therefore causing the -dev package also to be skipped effectively not
# populating it into SDK
ALLOW_EMPTY:${PN} = "1"

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS += "unifdef-native bison-native rsync-native"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"

# Restrict this recipe to NXP BSP only, this recipe is not compatible
# with mainline BSP
COMPATIBLE_HOST = '(null)'
COMPATIBLE_HOST:use-nxp-bsp = '.*'
