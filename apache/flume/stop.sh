#!/bin/bash
PID=`jps -v|grep Application|awk '{print $1}'`
if [ $PID != "" ]; then
    kill -9 $PID
fi
> nohup.out
echo "flume agent shutdown"