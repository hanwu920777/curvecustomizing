#!/bin/bash

THIS_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}")" && pwd )"

SERVICE_NAME=$1

echo "extracting lava ...$SERVICE_NAME"
cd $THIS_DIR/$SERVICE_NAME/lib
echo "extracting...3rdparty...tar.gz"
tar -zxvf lava-3rdparty-centos7-2.0.1.tar.gz
rm -rvf lava-3rdparty-centos7-2.0.1.tar.gz
echo "extracting...lava...tar.gz"
tar -zxvf lava-centos7-2.0.1.tar.gz
rm -rvf lava-centos7-2.0.1.tar.gz
mv  -vf  bin/*  $THIS_DIR/$SERVICE_NAME/lib
echo "extracting lava...done"
