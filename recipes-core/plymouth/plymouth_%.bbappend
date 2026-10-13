FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://calixto.plymouth \
    file://calixto.script \
    file://Calixto_splash.png \
"
do_install:append() {

    install -d ${D}${datadir}/plymouth/themes/calixto

    install -m 0644 ${UNPACKDIR}/calixto.plymouth \
        ${D}${datadir}/plymouth/themes/calixto/

    install -m 0644 ${UNPACKDIR}/calixto.script \
        ${D}${datadir}/plymouth/themes/calixto/

    install -m 0644 ${UNPACKDIR}/Calixto_splash.png \
        ${D}${datadir}/plymouth/themes/calixto/

    # Make Calixto the default theme
    install -d ${D}${datadir}/plymouth

    cat > ${D}${datadir}/plymouth/plymouthd.defaults <<EOF
[Daemon]
Theme=calixto
EOF
}
