FROM centos:7.5.1804
MAINTAINER wangyanfeng "yanfengking@163.com"

VOLUME /logs

ADD CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo

# set environment
ENV MODE="cluster" \
    JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk" \
    JAVA="/usr/lib/jvm/java-1.8.0-openjdk/bin/java" \
    JVM_XMS="512m" \
    JVM_XMX="512m" \
    JVM_XMN="256m" \
    JVM_MS="128m" \
    JVM_MMS="256m" \
    JVM_LOG_HOME="/logs" \
    TIME_ZONE="Asia/Shanghai"

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=$TIME_ZONE \
        -Djava.awt.headless=true \
        -Djava.net.preferIPv4Stack=true \
        -XX:+PrintGCDetails \
        -XX:+PrintGCApplicationStoppedTime \
        -Xloggc:$JVM_LOG_HOME/gc.log \
        -XX:+HeapDumpOnOutOfMemoryError \
        -XX:HeapDumpPath=$JVM_LOG_HOME/heapdump.hprof "
RUN set -x \
    && yum update -y \
    && yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel wget iputils nc  vi libcurl net-tools telnet

RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

RUN yum clean all
