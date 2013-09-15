#!/bin/sh

BIN_DIR=`cd $(dirname $0); pwd`
export JAVA_HOME=/home/deployer/jdk1.7.0_17

exec $JAVA_HOME/bin/java -Xms256m -Xmx768m -XX:NewRatio=2 -server -showversion \
    -classpath '/usr/lib/wtl/server/libs/*' \
    -Dfile.encoding=UTF-8 \
    -Djava.io.tmpdir=/var/tmp \
    -Dapp.package=wtl-server \
    ru.yandex.hackaton.server.WtlApplication "$@"
