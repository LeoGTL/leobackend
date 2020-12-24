#!/usr/bin bash

echo "##开启重启程序..."
leobackendpid=$(ps -aux | grep leobackend | grep -v grep | awk '{print $2}')
if [ -n "$leobackendpid" ];
then
    kill -9 "$leobackendpid"
fi

sleep 3

nohup $JAVA_HOME/bin/java -jar target/leobackend-1.0-SNAPSHOT.jar >> logs/leobackend.log &
