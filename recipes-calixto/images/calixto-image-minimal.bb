SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "kernel-modules packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image
inherit extrausers

EXTRA_USERS_PARAMS = " usermod -p $(openssl passwd -6 root) root; \   
		       useradd -p $(openssl passwd -6 calixto123) calixto_admin; \
		       usermod  -a -G sudo calixto_admin;"
                                              
IMAGE_FSTYPES:append = " tar"

IMAGE_INSTALL += "packagegroup-core-boot python3 python3-dbus sudo libubootenv-bin"
CORE_IMAGE_EXTRA_INSTALL += "inetutils apmd"
IMAGE_ROOTFS_SIZE ?= "8192"
