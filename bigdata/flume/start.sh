#!/bin/bash
sh /apps/flume/bin/flume-ng agent -c ../conf/ -f ../conf/flume-config.properties -n agent-1 -Dflume.root.logger=info,DAILY -Dflume.monitoring.type=http -Dflume.monitoring.port=17001 2>&1 &
echo $! > app.pid
tail -f /logs/flume.log