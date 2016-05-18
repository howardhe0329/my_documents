# du命令
> 查看文件和目录磁盘使用空间情况查看。

##命令格式
df [选项][文件]
##命令参数
* -a或-all 显示目录中个别文件的大小
* -b或-bytes 显示目录或文件大小时，以byte为单位
* -c或--total 除了显示个别目录或文件的大小外，同时也显示所有目录或文件的总和
* -k或--kilobytes 以KB(1024bytes)为单位输出
* -m或--megabytes 以MB为单位输出
* -s或--summarize 仅显示总计，只列出最后加总的值
* -h或--human-readable 以K，M，G为单位，提高信息的可读性
* -x或--one-file-xystem 以一开始处理时的文件系统为准，若遇上其它不同的文件系统目录则略过
* -L<符号链接>或--dereference<符号链接> 显示选项中所指定符号链接的源文件大小
* -S或--separate-dirs 显示个别目录的大小时，并不含其子目录的大小
* -X<文件>或--exclude-from=<文件> 在<文件>指定目录或文件
* -exclude=<目录或文件> 略过指定的目录或文件
* -D或--dereference-args 显示指定符号链接的源文件大小
* -H或--si 与-h参数相同，但是K，M，G是以1000为换算单位
* -l或--count-links 重复计算硬件链接的文件

##示例
* 查看指定目录的所占空间 `du [目录]`

```
[suoper@prod-java-bigdata-26 ~]$ du script
12	script/userbehavior
44	script/services_data
52	script/test
1332	script/logs
1452	script
```

* 只显示总和的大小 `du -s [目录]`

```
[suoper@prod-java-bigdata-26 ~]$ du -s script
1452	script
```

* 方便阅读的格式显示 `du -h [目录]`

```
[suoper@prod-java-bigdata-26 ~]$ du -h script
12K	script/userbehavior
44K	script/services_data
52K	script/test
1.4M	script/logs
1.5M	script
```

* 统计总和 `du -c [目录]`

```
[suoper@prod-java-bigdata-26 ~]$ du -c script
12	script/userbehavior
44	script/services_data
52	script/test
1332	script/logs
1452	script
1452	总用量
```

*  文件和目录都显示 `du -ah [目录]`

```
[suoper@prod-java-bigdata-26 ~]$ du -ah script
4.0K	script/userbehavior/userbehavior_etl.sh
4.0K	script/userbehavior/userbehavior_export_mysql.sh
12K	script/userbehavior
4.0K	script/services_data/services_data_repair.sh
4.0K	script/services_data/services_business_data_export_mysql.sh
4.0K	script/services_data/epocket_export_mysql.sh
4.0K	script/services_data/epocket_repair.sh
4.0K	script/services_data/epocket_export_mysql_report.sh
4.0K	script/services_data/kill_driver.sh
4.0K	script/services_data/services_data_etl.sh
4.0K	script/services_data/epocket_report_export.sh
4.0K	script/services_data/epocket_etl.sh
4.0K	script/services_data/services_business_data_etl.sh
44K	script/services_data
4.0K	script/show-kafka-consumer.sh
4.0K	script/sql.sh
4.0K	script/test/hadoop_put.sh
4.0K	script/test/leijia.sh
4.0K	script/test/param.sh
4.0K	script/test/for.sh
4.0K	script/test/mod.sh
4.0K	script/test/param1.sh
4.0K	script/test/abc.txt
4.0K	script/test/test.sh
4.0K	script/test/test1.sh
4.0K	script/test/dir.sh
4.0K	script/test/no_login_ssh.sh
4.0K	script/test/delSparkWorks.sh
52K	script/test
1.3M	script/logs/spark.log
1.4M	script/logs
1.5M	script
```

* 按照空间大小排序 `du|sort -nr|more`

```
[suoper@prod-java-bigdata-26 ~]$ du|sort -nr|more
1170192	.
1452	./script
1332	./script/logs
52	./script/test
44	./script/services_data
20	./.ssh
12	./script/userbehavior
8	./.oracle_jre_usage
```

* 显示当前目录下各个子目录所使用的空间 `du -h --max-depth=1`

```
[suoper@prod-java-bigdata-26 script]$ du -h --max-depth=1
12K	./userbehavior
44K	./services_data
52K	./test
1.4M	./logs
1.5M	.
```

