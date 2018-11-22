#!/bin/sh
cd `dirname $0`
BIN_DIR=`pwd`

# 主启动类
cd ..
DEPLOY_DIR=`pwd`
MAIN_CLASS=com.qhbd.sso.UserEsApplication
# 获取 pid
PIDS=`ps -ef | grep java | grep "$DEPLOY_DIR" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The $MAIN_CLASS does not started!"
    exit 1
fi


# kill 线程
echo -e "Stopping the $MAIN_CLASS ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

echo "OK!"
echo "PID: $PIDS"
