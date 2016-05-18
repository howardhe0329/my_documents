# iostat命令
> 是I/O statistics（输入/输出统计）的缩写，iostat工具将对系统的磁盘操作活动进行监视。

##命令格式
iostat [参数][时间][次数]
##命令功能
查看CPU、网卡、tty设备、磁盘、CD-ROM 等等设备的活动情况, 负载信息。
##命令参数
* -C 显示CPU使用情况
* -d 显示磁盘使用情况
* -k 以 KB 为单位显示
* -m 以 M 为单位显示
* -N 显示磁盘阵列(LVM) 信息
* -n 显示NFS 使用情况
* -p [磁盘] 显示磁盘和分区的情况
* -t 显示终端和CPU的信息
* -x 显示详细信息
* -V 显示版本信息

##命令结果
* iostat

```
[suoper@prod-java-bigdata-26 ~]$ iostat
Linux 3.10.0-327.4.5.el7.x86_64 (prod-java-bigdata-26) 	2016年05月16日 	_x86_64_	(8 CPU)

avg-cpu:  %user   %nice %system %iowait  %steal   %idle
          36.30    0.00    0.42    0.04    0.12   63.12

Device:            tps    kB_read/s    kB_wrtn/s    kB_read    kB_wrtn
sda               6.47         1.20        42.08    4749293  166208384
sdb               0.18         2.57         6.97   10132260   27534248
```

###结果说明
* avg-cpu:
	* %user: CPU处在用户模式下的时间百分比
	* %nice: CPU处在带NICE值的用户模式下的时间百分比
	* %system: CPU处在系统模式下的时间百分比
	* %iowait: CPU等待IO输入输出完成时间的百分比
	* %steal: 管理程序维护另一个虚拟处理器时，虚拟CPU的无意识等待时间百分比
	* %idle: CPU空闲时间百分比
* Device:
	* rrqm/s: 每秒进行 merge 的读操作数目。即 rmerge/s
	* wrqm/s: 每秒进行 merge 的写操作数目。即 wmerge/s
	* r/s: 每秒完成的读 I/O 设备次数。即 rio/s
	* w/s: 每秒完成的写 I/O 设备次数。即 wio/s
	* rsec/s: 每秒读扇区数。即 rsect/s
	* wsec/s: 每秒写扇区数。即 wsect/s
	* rkB/s: 每秒读K字节数。是 rsect/s 的一半，因为每扇区大小为512字节
	* wkB/s: 每秒写K字节数。是 wsect/s 的一半
	* avgrq-sz: 平均每次设备I/O操作的数据大小 (扇区)
	* avgqu-sz: 平均I/O队列长度
	* await: 平均每次设备I/O操作的等待时间 (毫秒)
	* svctm: 平均每次设备I/O操作的服务时间 (毫秒)
	* %util:  一秒中有百分之多少的时间用于 I/O 操作，即被io消耗的cpu百分比
	
###结果分析
1. 如果**%util**接近**100%**，说明产生的**I/O请求太多**，I/O系统已经满负荷，该**磁盘**可能存在瓶颈。
2. 如果**svctm**比较接近**await**，说明**I/O**几乎没有等待时间。
3. 如果**await**远大于**svctm**，说明**I/O队列太长**，**IO响应太慢**，则需要进行必要优化。
4. 如果**avgqu-sz**比较大，也表示有大量**IO在等待**。

##示例
* 定时显示所有信息 `iostat 2 3`
* 显示指定磁盘信息 `iostat -d sda1`

```
[suoper@prod-java-bigdata-26 ~]$ iostat -d sda
Linux 3.10.0-327.4.5.el7.x86_64 (prod-java-bigdata-26) 	2016年05月16日 	_x86_64_	(8 CPU)

Device:            tps    kB_read/s    kB_wrtn/s    kB_read    kB_wrtn
sda               6.47         1.20        42.07    4749293  166258316
```

* 显示tty和Cpu信息 `iostat -t`

```
[suoper@prod-java-bigdata-26 ~]$ iostat -t
Linux 3.10.0-327.4.5.el7.x86_64 (prod-java-bigdata-26) 	2016年05月16日 	_x86_64_	(8 CPU)

2016年05月16日 18时43分10秒
avg-cpu:  %user   %nice %system %iowait  %steal   %idle
          36.30    0.00    0.42    0.04    0.12   63.12

Device:            tps    kB_read/s    kB_wrtn/s    kB_read    kB_wrtn
sda               6.47         1.20        42.07    4749293  166262216
sdb               0.18         2.56         6.97   10132260   27534248
```

* 以M为单位显示所有信息 `iostat -m`
* 查看TPS和吞吐量信息 `iostat -d -k 1 1`

```
[suoper@prod-java-bigdata-26 ~]$ iostat -d -k 1 1
Linux 3.10.0-327.4.5.el7.x86_64 (prod-java-bigdata-26) 	2016年05月16日 	_x86_64_	(8 CPU)

Device:            tps    kB_read/s    kB_wrtn/s    kB_read    kB_wrtn
sda               6.47         1.20        42.07    4749293  166268492
sdb               0.18         2.56         6.97   10132260   27534248
```
**结果说明**:
tps: 该 设备每秒的传输次数 （Indicate the number of transfers per second that were issued to the device.）。 “一次传输”意思是“一次I/O请求”。多个逻辑请求可能会被合并为“一次I/O请求”。“一次传输”请求的大小是未知的。
kB_read/s：每秒从设备（drive expressed）读取的数据量
kB_wrtn/s：每秒向设备（drive expressed）写入的数据量
kB_read：读取的总数据量；kB_wrtn：写入的总数量数据量

* 查看设备使用率（%util）、响应时间（await）`iostat -d -x 1 1`

```
[suoper@prod-bigdata-hadoop-19 ~]$ iostat -d -x 1 1
Linux 3.10.0-327.4.5.el7.x86_64 (prod-bigdata-hadoop-19) 	2016年05月17日 	_x86_64_	(4 CPU)

Device:         rrqm/s   wrqm/s     r/s     w/s    rkB/s    wkB/s avgrq-sz avgqu-sz   await r_await w_await  svctm  %util
sdb              21.42    25.56    5.02    0.72   109.67   105.18    74.82     0.61  106.95   21.17  706.28   9.68   5.56
sda               0.09     0.34   32.57    1.51   948.77     8.80    56.20     0.01    0.18    0.15    0.73   0.12   0.40
sdc               1.64     4.44  824.96    6.27  3492.85  1169.69    11.22     0.54    0.65    0.23   56.13   0.04   3.64
dm-0              0.00     0.00  826.62   10.56  3492.85  1169.69    11.14     0.57    0.68    0.23   35.70   0.04   3.66
```
**说明**:
rrqm/s: 每秒进行merge的读操作数目.即delta(rmerge)/s
wrqm/s: 每秒进行merge的写操作数目.即delta(wmerge)/s
r/s: 每秒完成的读I/O设备次数.即delta(rio)/s
w/s: 每秒完成的写 I/O 设备次数.即 delta(wio)/s
rsec/s: 每秒读扇区数.即 delta(rsect)/s
wsec/s: 每秒写扇区数.即 delta(wsect)/s
rkB/s: 每秒读K字节数.是 rsect/s 的一半,因为每扇区大小为512字节.(需要计算)
wkB/s: 每秒写K字节数.是 wsect/s 的一半.(需要计算)
avgrq-sz: 平均每次设备I/O操作的数据大小(扇区).delta(rsect+wsect)/delta(rio+wio)
avgqu-sz: 平均I/O队列长度.即 delta(aveq)/s/1000 (因为aveq的单位为毫秒).
await: 平均每次设备I/O操作的等待时间 (毫秒).即 delta(ruse+wuse)/delta(rio+wio)
svctm: 平均每次设备I/O操作的服务时间 (毫秒)即 delta(use)/delta(rio+wio)
%util: 一秒中有百分之多少的时间用于 I/O 操作,或者说一秒中有多少时间 I/O 队列是非空的，即 delta(use)/s/1000 (因为use的单位为毫秒)
**分析**
如果 %util 接近 100%，说明产生的I/O请求太多，I/O系统已经满负荷，该磁盘可能存在瓶颈。
idle小于70% IO压力就较大了，一般读取速度有较多的wait。
同时可以结合vmstat 查看查看b参数(等待资源的进程数)和wa参数(IO等待所占用的CPU时间的百分比，高过30%时IO压力高)。
另外 await 的参数也要多和 svctm 来参考。差的过高就一定有 IO 的问题。
avgqu-sz 也是个做 IO 调优时需要注意的地方，这个就是直接每次操作的数据的大小，如果次数多，但数据拿的小的话，其实 IO 也会很小。如果数据拿的大，才IO 的数据会高。也可以通过 avgqu-sz × ( r/s or w/s ) = rsec/s or wsec/s。也就是讲，读定速度是这个来决定的。
svctm 一般要小于 await (因为同时等待的请求的等待时间被重复计算了)，svctm 的大小一般和磁盘性能有关，CPU/内存的负荷也会对其有影响，请求过多也会间接导致 svctm 的增加。await 的大小一般取决于服务时间(svctm) 以及 I/O 队列的长度和 I/O 请求的发出模式。如果 svctm 比较接近 await，说明 I/O 几乎没有等待时间；如果 await 远大于 svctm，说明 I/O 队列太长，应用得到的响应时间变慢，如果响应时间超过了用户可以容许的范围，这时可以考虑更换更快的磁盘，调整内核 elevator 算法，优化应用，或者升级 CPU。
队列长度(avgqu-sz)也可作为衡量系统 I/O 负荷的指标，但由于 avgqu-sz 是按照单位时间的平均值，所以不能反映瞬间的 I/O 洪水。
**形象的比喻**
r/s+w/s 类似于交款人的总数
平均队列长度(avgqu-sz)类似于单位时间里平均排队人的个数
平均服务时间(svctm)类似于收银员的收款速度
平均等待时间(await)类似于平均每人的等待时间
平均I/O数据(avgrq-sz)类似于平均每人所买的东西多少
I/O 操作率 (%util)类似于收款台前有人排队的时间比例
设备IO操作:总IO(io)/s = r/s(读) +w/s(写) =1.46 + 25.28=26.74
平均每次设备I/O操作只需要0.36毫秒完成,现在却需要10.57毫秒完成，因为发出的 请求太多(每秒26.74个)，假如请求时同时发出的，可以这样计算平均等待时间:
平均等待时间=单个I/O服务器时间*(1+2+...+请求总数-1)/请求总数 
每秒发出的I/0请求很多,但是平均队列就4,表示这些请求比较均匀,大部分处理还是比较及时。

* 查看cpu状态 `iostat -c 1 3`

```
[suoper@prod-bigdata-hadoop-19 ~]$ iostat -c 1 3
Linux 3.10.0-327.4.5.el7.x86_64 (prod-bigdata-hadoop-19) 	2016年05月17日 	_x86_64_	(4 CPU)

avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           6.59    0.00    4.13    4.31    0.06   84.91

avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           1.54    0.00   22.62    4.63    0.26   70.95

avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           1.03    0.00   26.29    3.35    0.00   69.33
```
