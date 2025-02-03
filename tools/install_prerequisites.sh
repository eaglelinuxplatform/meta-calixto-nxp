#!/bin/bash

set -eu -o pipefail # fail on error and report it, debug all lines

sudo -n true
test $? -eq 0 || exit 1 "you should have sudo privilege to run this script"

echo installing the must-have pre-requisites
while read -r p ; do sudo apt-get install -y $p ; done < <(cat << "EOF"
    gawk
    wget
    git
    diffstat
    unzip
    texinfo
    gcc
    build-essential
    chrpath
    socat
    cpio
    python3
    python3-pip
    python3-pexpect
    xz-utils
    debianutils
    iputils-ping
    python3-git
    python3-jinja2
    python3-subunit
    zstd
    liblz4-tool
    file
    locales
    libacl1
EOF
)

echo installing the  pre-requisites
echo you have 5 seconds to proceed ...
echo or
echo hit Ctrl+C to quit
echo -e "\n"
sleep 6
