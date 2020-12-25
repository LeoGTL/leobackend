#!/usr/bin bash

echo "##开启重启程序..."
leobackendpid=$(ps -aux | grep leobackend | grep -v grep | awk '{print $2}')
if [ -n "$leobackendpid" ];
then
    kill -9 "$leobackendpid"
fi

echo "3..."
sleep 1
echo "2..."
sleep 1
echo "1..."
sleep 1

nohup $JAVA_HOME/bin/java -jar target/leobackend-1.0-SNAPSHOT.jar >> logs/leobackend.log &
echo "##程序重启完毕."
