#!/bin/bash
# rpmをビルドする。要: rpm-build, maven, java-devel
# 使い方: ./build-rpm.sh   (成果物は ./rpmbuild/RPMS/noarch/ に出力される)
set -e
cd "$(dirname "$0")"
NAME=slf4jloggerconfigurator
VERSION=$(sed -n 's/^Version:[[:space:]]*//p' rpm/${NAME}.spec)
TOP="$(pwd)/rpmbuild"
mkdir -p "${TOP}"/{SOURCES,SPECS,BUILD,RPMS,SRPMS}
git archive --format=tar.gz --prefix=${NAME}-${VERSION}/ -o "${TOP}/SOURCES/${NAME}-${VERSION}.tar.gz" HEAD
rpmbuild -ba --define "_topdir ${TOP}" rpm/${NAME}.spec
