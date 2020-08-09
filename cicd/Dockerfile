FROM ccr.ccs.tencentyun.com/etc-group/openjdk:8-centos7-20200801           

#需要指定正确的jar包
ADD target/devops.jar /app.jar

#------------------------------------
ADD docker-startup.sh /docker-startup.sh
#如下环境变量的对应关系，默认openjdk:8-centos7-etc-20200801有默认设置
#Xms${JVM_XMS}   
#Xmx${JVM_XMX} 
#Xmn${JVM_XMN} 
#XX:MetaspaceSize=${JVM_MS} 
#XX:MaxMetaspaceSize=${JVM_MMS}"
#
#JVM_XMS="1g" 
#JVM_XMX="1g" 
#JVM_XMN="512m" 
#JVM_MS="128m" 
#JVM_MMS="320m" 

ENV JVM_XMS="256M" \
    JVM_XMX="512M" \
    JVM_XMN="168M"

ENV SERVER_PORT=8080

RUN chmod +x /docker-startup.sh

EXPOSE 8080
ENTRYPOINT  ["/bin/bash", "-C", "/docker-startup.sh"]



