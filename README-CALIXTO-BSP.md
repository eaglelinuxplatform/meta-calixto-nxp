CALIXTO SYSTEMS PVT LTD Yocto Project BSP cs-6.1.1-0.0.1 Release 
====================================================================
		https://calixtosystems.com/
--------------------------------------------------------------------

Quick Start Guide
----------------- 

The following SOM's were tested in this release:

   * Calixto IMX6ULL-VERSA256 
   * Calixto IMX6ULL-VERSA512  
   * Calixto IMX6ULL-VERSA1024  
   
Quick Start Guide
-----------------
See the Calixto Yocto Project User's Guide for instructions on installing repo.

First install the Calixto Linux BSP repo
	$ repo init -u https://github.com/eaglelinuxplatform/calixto-manifest.git -b kirkstone -m cs-6.1.1-0.0.1.xml

Download the Yocto Project Layers:
	$ repo sync

Run Calixto Linux Yocto Project Setup:
	$ DISTRO=poky MACHINE=imx6ull-versa256 source calixto-setup-release.sh -b build_cs

After this your system will be configured to start a Yocto Project build.


Contributing
------------

To contribute to the development of this BSP and/or submit patches for
new boards please send the patches against the respective project as
informated bellow:

The following layers are included on this release:

 * poky: base build system and metadata
   Path: sources/poky
   GIT: git://git.yoctoproject.org/poky
   Mailing list: https://lists.yoctoproject.org/listinfo/yocto

 * meta-openembedded: extra packages and features
   Path: sources/meta-openembedded
   GIT: git://git.openembedded.org/meta-openembedded
   Mailing list: http://lists.linuxtogo.org/cgi-bin/mailman/listinfo/openembedded-devel
   Note: Use [meta-oe] in subject to easy the processing

 * meta-freescale: support for Freescale's processors and board
   Path: sources/meta-freescale
   Project: https://github.com/Freescale/meta-freescale
   GIT: git://github.com/Freescale/meta-freescale.git
   Mailing list: https://lists.yoctoproject.org/listinfo/meta-freescale

 * meta-freescale-3rdparty: support for boards using Freescale's processors
   Path: sources/meta-freescale-3rdparty
   Project: https://github.com/Freescale/meta-freescale-3rdparty
   GIT: git://github.com/Freescale/meta-freescale-3rdparty.git
   Mailing list: https://lists.yoctoproject.org/listinfo/meta-freescale
   Note: Use [3rdparty] in subject to easy the processing

 * meta-freescale-distro: distribution images and recipes
   Path: sources/meta-freescale-distro
   Project: https://github.com/Freescale/meta-freescale-distro
   GIT: git://github.com/Freescale/meta-freescale-distro.git
   Mailing list: https://lists.yoctoproject.org/listinfo/meta-freescale
   Note: Use [distro] in subject to easy the processing
