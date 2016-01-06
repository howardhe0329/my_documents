#!/bin/bash
./flume-ng agent -c ../conf/ -f ../conf/flume-tail-kafka.properties -n agent-1 -Dflume.root.logger=INFO,LOGFILE -Xms512m -Xmx512m 2>&1 &