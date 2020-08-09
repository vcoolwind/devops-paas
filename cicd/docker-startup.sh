#!/bin/bash
# 通用的执行脚本，默认启动/app.jar，环境变量可以调整核心的内存设置。
#set -x
JAVA_OPTS="${JAVA_OPTS} -server -Xms${JVM_XMS} -Xmx${JVM_XMX} -Xmn${JVM_XMN} -XX:MetaspaceSize=${JVM_MS} -XX:MaxMetaspaceSize=${JVM_MMS}"
JAVA_OPTS="${JAVA_OPTS} -XX:-OmitStackTraceInFastThrow -XX:-UseLargePages"
JAVA_OPTS="${JAVA_OPTS} -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
JAVA_OPTS="${JAVA_OPTS} ${JAVA_OPT_EXT}"

echo "-----application will be starting with paras-----"
echo ${JAVA_OPTS}
echo "--------------------------------------------"

$JAVA ${JAVA_OPTS} -jar /app.jar
