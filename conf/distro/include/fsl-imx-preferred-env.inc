# --Calixto Source-- 
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-calixto-nxp"
PREFERRED_PROVIDER_virtual/kernel = "linux-calixto-nxp"

PREFERRED_PROVIDER_linux-imx-mfgtool = "linux-imx-mfgtool"

PREFERRED_VERSION_gstreamer1.0-plugins-base = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-bad  = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-good = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-good-qt = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0              = "1.20.3.imx"

PREFERRED_VERSION_gstreamer1.0-plugins-base:mx8-nxp-bsp = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-bad:mx8-nxp-bsp  = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-good:mx8-nxp-bsp = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-good-qt:mx8-nxp-bsp = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-plugins-ugly:mx8-nxp-bsp = "1.20.%"
PREFERRED_VERSION_gstreamer1.0:mx8-nxp-bsp              = "1.20.3.imx"
PREFERRED_VERSION_gstreamer1.0-libav:mx8-nxp-bsp        = "1.20.%"
PREFERRED_VERSION_gstreamer1.0-rtsp-server:mx8-nxp-bsp  = "1.20.%"

PREFERRED_VERSION_opengl-es-cts:mx6-nxp-bsp = "3.2.4.0"
PREFERRED_VERSION_opengl-es-cts:mx7-nxp-bsp = "3.2.4.0"
PREFERRED_VERSION_opengl-es-cts:aarch64 = "3.2.8.0"

# Use systemd as default init manager
VIRTUAL-RUNTIME_init_manager = "systemd"
PREFERRED_PROVIDER_udev = "systemd"
PREFERRED_PROVIDER_udev-utils = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
POKY_DEFAULT_DISTRO_FEATURES += " systemd"
