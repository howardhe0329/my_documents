[TOC]
#free命令
> 显示系统内存的使用情况

## free 命令参数
* -b	以Byte为单位显示内存使用情况
* -k	以KB为单位显示内存使用情况
* -m	以MB为单位显示内存使用情况
* -g	以GB为单位显示内存使用情况
* -o	不显示缓冲区调节列
* -s	<间隔秒数> 　持续观察内存使用状况
* -t 显示内存总和列
* -V 显示版本信息

## free命令结果说明
示例：

```
[suoper@prod-bigdata-hadoop-19 ~]$ free -m
              total        used        free      shared  buff/cache   available
Mem:           7823        4300         418          36        3103        3112
Swap:          8191         420        7771
```

1. Mem: 内存使用情况
	1. total 内存总大小
	2. used	已使用内存存大
	3. free	空闲内存大小
	4. shard	多个进程共享的内存总额
	5. buff/cache	磁盘缓存的大小
	6. available	
2. Swap: 交换区信息 (通常指虚拟内存）
	1. total 交换总量
	2. used	使用量
	3. free 空闲交换区

## free命令结果分析
对于应用程序来说buff/cache是可用的，因为buff/cache是为了提高文件的读取性能，当应用程序需要用内存的时候, buff/cache会很快被回收。所以从应用程序角度来讲可用内存=free + buff/cache。
对于swap，只要used使用不大，就不用担心内存太小。如果swap的used使用太多，可能就要考虑增加内存了。
关于buff/cache为何占用一部分内存，是因为为了提高磁盘的读写效率。
swap是当内存不够用时，将内存中的暂时不用的数据写入到磁盘里。
## 其它
```
[suoper@prod-bigdata-hadoop-19 ~]$ cat /proc/meminfo
MemTotal:        8010832 kB
MemFree:          488656 kB
MemAvailable:    3244784 kB
Buffers:           17456 kB
Cached:            50660 kB
SwapCached:        36860 kB
Active:          3182984 kB
Inactive:        1148580 kB
Active(anon):    3169428 kB
Inactive(anon):  1131680 kB
Active(file):      13556 kB
Inactive(file):    16900 kB
Unevictable:           0 kB
Mlocked:               0 kB
SwapTotal:       8388604 kB
SwapFree:        7965460 kB
Dirty:               560 kB
```

