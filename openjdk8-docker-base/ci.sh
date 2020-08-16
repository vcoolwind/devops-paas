#!/bin/bash
pre_version="1.0.0"
img_name="openjdk8-centos7"

#-----do not change below content-----------------------
origin_path=`pwd`
project_path=$(cd `dirname $0`; pwd)

if [[ "$1" != "" ]]; then
  version=$1
  hub=etc-group
else
  version="${pre_version}-"`date +%Y%m%d%H`
  hub=etc-group-dev
fi
target_img=ccr.ccs.tencentyun.com/${hub}/${img_name}:${version}

#prepare
cd ${project_path}
cp -f Dockerfile ../Dockerfile
cp -f docker-startup.sh ../docker-startup.sh

#jar package
cd ..
git pull
mvn clean package -Dmaven.test.skip
#docker builid
docker build . -t ${target_img}
rm Dockerfile docker-startup.sh
#docker push
cd $project_path
docker login --username=100015048926 ccr.ccs.tencentyun.com -p Hub@etc2020
docker push ${target_img}

#keep origin dir
cd ${origin_path}
