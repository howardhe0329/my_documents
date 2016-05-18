# watch命令
> 监测一个命令的运行结果。watch是周期性的执行下个程序，并全屏显示执行结果。

##命令格式
watch [参数][命令]
##命令参数
* -n或--interval watch缺省每2秒运行一下程序，可以用-n或-interval来指定间隔的时间。
* -d或--differences 用-d或--differences 选项watch 会高亮显示变化的区域。而-d=cumulative选项会把变动过的地方(不管最近的那次有没有变动)都高亮显示出来。
* -t或-no-title 会关闭watch命令在顶部的时间间隔,命令，当前时间的输出。
* -h 查看帮助文档

##示例
* 每隔一秒高亮显示网络链接数的变化情况 `watch -n 1 -d netstat -ant`

```
[suoper@prod-java-bigdata-26 script]$ watch -n 1 -d netstat -ant
Every 1.0s: netstat -ant                                                                                                                                     Mon May 16 16:06:11 2016

Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address           Foreign Address         State
tcp        0	  0 0.0.0.0:22              0.0.0.0:*               LISTEN
tcp        0      0 192.168.99.26:22        192.168.255.254:59150   ESTABLISHED
tcp6	   0	  0 :::17001                :::*                    LISTEN
tcp6	   0	  0 :::45193                :::*                    LISTEN
tcp6	   0	  0 :::17002                :::*                    LISTEN
tcp6	   0	  0 :::41226                :::*                    LISTEN
tcp6	   0	  0 :::17003                :::*                    LISTEN
tcp6	   0	  0 :::17004                :::*                    LISTEN
tcp6	   0	  0 :::17005                :::*                    LISTEN
tcp6	   0	  0 :::17006                :::*                    LISTEN
tcp6	   0	  0 :::38766                :::*                    LISTEN
tcp6	   0	  0 :::18001                :::*                    LISTEN
tcp6	   0	  0 :::18002                :::*                    LISTEN
tcp6	   0	  0 :::18003                :::*                    LISTEN
tcp6	   0	  0 :::51701                :::*                    LISTEN
tcp6	   0	  0 :::22                   :::*                    LISTEN
tcp6	   0	  0 :::45472                :::*                    LISTEN
tcp6	   0	  0 :::56295                :::*                    LISTEN
tcp6	   0	  0 192.168.99.26:52720     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:34530     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:51732     192.168.99.19:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:53926     192.168.99.19:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:18001     192.168.2.91:33192      TIME_WAIT
tcp6	   0	  0 192.168.99.26:51826     192.168.99.11:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:56327     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:38180     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:54692     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:35605     192.168.99.11:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:33108     192.168.99.8:50010      ESTABLISHED
tcp6	   0	  0 192.168.99.26:50151     192.168.99.11:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:57607     192.168.99.5:9092       ESTABLISHED
tcp6	   0	  0 192.168.99.26:38172     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:34676     192.168.99.7:9092       ESTABLISHED
tcp6	   0	  0 192.168.99.26:35325     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:34540     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:34635     192.168.99.9:50010      ESTABLISHED
tcp6	   0	  0 192.168.99.26:53672     192.168.99.11:50010     TIME_WAIT
tcp6	   0	  0 192.168.99.26:50618     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:53650     192.168.99.19:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:56272     192.168.99.10:50010     TIME_WAIT
tcp6	   0	  0 192.168.99.26:54334     192.168.99.10:50010     ESTABLISHED
tcp6	   0	  0 192.168.99.26:60191     192.168.99.6:9092       ESTABLISHED

```

* 每秒一次输出系统的平均负载 `watch -n 1 'cat /proc/loadavg‘`

```
[suoper@prod-java-bigdata-26 script]$ watch -n 1 cat /proc/loadavg

Every 10.0s: cat /proc/loadavg                                                                                                                               Mon May 16 16:10:28 2016

3.35 3.16 3.02 4/1963 10380
```
