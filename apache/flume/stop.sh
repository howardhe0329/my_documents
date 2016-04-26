#!/bin/bash
PID=`cat app.pid`
if [ $PID != "" ]; then
    kill -3 $PID
fi
echo "flume agent shutdown"