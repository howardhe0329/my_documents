#!/bin/bash
PID=`cat app.pid`
if [ $PID != "" ]; then
    kill -9 $PID
fi
echo "flume agent shutdown"