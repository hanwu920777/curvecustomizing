#!/bin/bash

prjName={{start_project_name}}
serviceName={{start_service_name}}
appClassName={{start_app_class_name}}
JVM_OPTS_MS_SIZE={{start_jvm_ms}}
JVM_OPTS_MX_SIZE={{start_jvm_mx}}
extraArgs=$1

# 以下为通用脚本

ENV=dev
if [ -f /opt/sumscope/${prjName}/.ssinfo ]; then
    source /opt/sumscope/${prjName}/.ssinfo
    ENV=$env_info
fi

originalDir=`pwd`
thisFileDir="$( cd "$( dirname "${BASH_SOURCE[0]}")" && pwd )"
serviceDir="$( cd "${thisFileDir}/.." && pwd )"
projectDir="$( cd "${serviceDir}/../.." && pwd )"
logDir=${projectDir}/log/${serviceName}
dataDir=${projectDir}/data/${serviceName}
mkdir -p ${logDir}
mkdir -p ${dataDir}

LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${serviceDir}/lib
export LD_LIBRARY_PATH

ulimit -c unlimited

echo "serviceDir: $serviceDir"
echo "projectDir: $projectDir"
echo "LD_LIBRARY_PATH: $LD_LIBRARY_PATH"

sh  ${serviceDir}/bin/stop.sh
cd  ${serviceDir}

JAVA_OPT="-server -Xms${JVM_OPTS_MS_SIZE} -Xmx${JVM_OPTS_MX_SIZE} -XX:+PrintGCDetails -Xloggc:${logDir}/gc.log"
JAVA_ARGS=" -Dspring.profiles.active=$ENV  -Dservice_log_dir=${logDir}  -Dfile.encoding=utf-8 -Duser.timezone=GMT+08 ${extraArgs}  "


nohup  java  $JAVA_OPT  $JAVA_ARGS  -cp  ${serviceDir}/control:${serviceDir}/javalib/*  ${appClassName} >/dev/null 2> ${logDir}/nohup.out &

curr_dir=`pwd`
fails=0
while [ $fails -le 3 ]; do
    for pid in `pgrep java`; do
        one_dir=`readlink -e /proc/$pid/cwd`
        if [ "$one_dir" != "" ] && [ "$one_dir" == "$curr_dir" ]; then
            echo $pid':' $one_dir
            cd  ${originalDir}
            exit 0
        fi
    done
    sleep 1
    fails=$(($fails + 1))
done
echo 'start error...'

tail  -n  50  ${logDir}/nohup.out

cd  ${originalDir}
