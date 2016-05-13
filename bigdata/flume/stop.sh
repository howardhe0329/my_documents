#!/bin/bash
PID=`cat app.pid`
if [ $PID != "" ]; then
    kill -15 $PID
fi
echo "flume agent shutdown"