#!/bin/bash
pre_version="1.1"
img_name="openjdk8-centos7-skywalking8"

#-----do not change below content-----------------------
origin_path=`pwd`
project_path=$(cd `dirname $0`; pwd)

version="${pre_version}-"`date +%Y%m%d%H`
hub=etc-group

target_img=ccr.ccs.tencentyun.com/${hub}/${img_name}:${version}

echo ${target_img}

cd ${project_path}
#docker builid
docker build . -t ${target_img}
docker login --username=100015048926 ccr.ccs.tencentyun.com -p Hub@etc2020
docker push ${target_img}

#keep origin dir
cd ${origin_path}
