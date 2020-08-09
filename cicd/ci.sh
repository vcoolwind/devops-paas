#!/bin/bash
pre_version="1.0.0"
img_name="devops"

#-----do not change below content-----------------------
origin_path=`pwd`
project_path=$(cd `dirname $0`; pwd)

if [[ "$1" != "" ]]; then
  version=$1
  hub=etc-member
else
  version="${pre_version}-"`date +%Y%m%d%H`
  hub=etc-member-dev
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
/usr/bin/expect ./autologin
docker push ${target_img}

#keep origin dir
cd ${origin_path}