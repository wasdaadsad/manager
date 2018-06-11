#!/bin/bash
 
PHOME=$(dirname `readlink -f "$0"`)
PHOME=$(dirname $PHOME)

JAVA_OPTS="-server -Xms1g -Xmx1g -Xmn256m -XX:MaxPermSize=256m -Xss256k \
-XX:+UseParNewGC -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection \
-XX:+CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=80 -XX:+UseCMSInitiatingOccupancyOnly -XX:+UseCompressedOops \
-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${log4j2.log.home}/gc.log \
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${log4j2.log.home}/dump \
-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector \
-XX:MaxDirectMemorySize=256m \
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=${jmxremote.port} \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false "

#-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+UseAdaptiveSizePolicy -XX:MaxGCPauseMillis=100 -XX:ParallelGCThreads=8 \
#-agentpath:/home/gchen/jprofiler9/bin/linux-x64/libjprofilerti.so=port=8849


pid=`ps -eo pid,args | grep NatServerBootstrap | grep $1 | grep java | grep -v grep | awk '{print $1}'`

if [ -n "$pid" ]
then
    kill -3 ${pid}
    kill ${pid} && sleep 3
    if [  -n "`ps -eo pid | grep $pid`" ]
    then
        kill -9 ${pid}
    fi
    echo "kill pid: ${pid}"
    sleep 2
fi

java -Dmodule=$1 $JAVA_OPTS -cp $PHOME/conf:$PHOME/lib/* com.vivo.nat.server.NatServerBootstrap $1 > /dev/null 2>&1 &
