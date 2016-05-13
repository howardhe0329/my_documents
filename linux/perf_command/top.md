#top命令
> 常用的性能分析工具，能够实时显示系统中各个进程的资源占用状况。

##命令格式
top [参数]
##命令功能
显示当前系统正在执行的进程的相关信息，包括进程ID、内存占用率、CPU占用率等
##命令参数
* -b: 批处理
* -c: 显示完整的治命令
* -l: 忽略失效过程
* -s: 保密模式
* -S: 累积模式
* -i <时间> 设置间隔时间
* -u <用户名> 指定用户名
* -p <进程号> 指定进程
* -n <次数> 循环显示的次数
##命令结果

```
[suoper@prod-bigdata-hadoop-19 ~]$ top
top - 14:39:56 up 64 days, 9 min,  1 user,  load average: 0.05, 0.18, 0.22
Tasks: 120 total,   2 running, 118 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.1 us,  0.1 sy,  0.0 ni, 86.3 id, 13.5 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  8010832 total,   559160 free,  4412412 used,  3039260 buff/cache
KiB Swap:  8388604 total,  7971580 free,   417024 used.  3172120 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
 4376 suoper    20   0 8620288 4.078g      0 S   1.0 53.4   1388:22 java
  509 root      20   0   80200    144     80 S   0.3  0.0 144:41.35 gapd
    1 root      20   0   46612   7460   1056 S   0.0  0.1   4:37.01 systemd
    2 root      20   0       0      0      0 S   0.0  0.0   0:56.68 kthreadd
    3 root      20   0       0      0      0 S   0.0  0.0   7:06.78 ksoftirqd/0
    5 root       0 -20       0      0      0 S   0.0  0.0   0:00.00 kworker/0:0H
    7 root      rt   0       0      0      0 S   0.0  0.0   2:18.28 migration/0
    8 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcu_bh
    9 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/0
   10 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/1
   11 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/2
   12 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/3
   13 root      20   0       0      0      0 S   0.0  0.0  86:16.42 rcu_sched
   14 root      20   0       0      0      0 S   0.0  0.0  45:26.15 rcuos/0
   15 root      20   0       0      0      0 S   0.0  0.0  42:35.10 rcuos/1
   16 root      20   0       0      0      0 S   0.0  0.0  37:53.92 rcuos/2
   17 root      20   0       0      0      0 S   0.0  0.0  44:04.62 rcuos/3
   18 root      rt   0       0      0      0 S   0.0  0.0   0:51.07 watchdog/0
   19 root      rt   0       0      0      0 S   0.0  0.0   0:47.78 watchdog/1
   20 root      rt   0       0      0      0 S   0.0  0.0   1:41.40 migration/1
   21 root      20   0       0      0      0 S   0.0  0.0   6:31.28 ksoftirqd/1
   23 root       0 -20       0      0      0 S   0.0  0.0   0:00.00 kworker/1:0H
   24 root      rt   0       0      0      0 S   0.0  0.0   0:47.72 watchdog/2
   25 root      rt   0       0      0      0 S   0.0  0.0   1:51.54 migration/2
   26 root      20   0       0      0      0 S   0.0  0.0   8:28.44 ksoftirqd/2
   28 root       0 -20       0      0      0 S   0.0  0.0   0:00.00 kworker/2:0H
   29 root      rt   0       0      0      0 S   0.0  0.0   0:52.19 watchdog/3
   30 root      rt   0       0      0      0 S   0.0  0.0   1:49.64 migration/3
   31 root      20   0       0      0      0 S   0.0  0.0   5:37.26 ksoftirqd/3
```
###结果说明
* 统计信息区: （前五行是当前系统情况整体的统计信息区）
	1. 任务队列信息（第一行）:
		* 当前时间: (14:39:56)
		* 运行多长时间: up 64 days, 9 min (64天9分钟)
		* 当前有多少个用户登录系统: 1 user (当前有1个用户)
		* load average: 系统负载，后面三个数分别代表：1分钟、5分钟、15分钟。用`uptime`命令也可以查看负载
	2. Tasks （第二行）表示进程状态信息
		* total: 系统现在总共进程数
		* running: 正在运行进程数
		* sleeping: 处于休眠进程数
		* stopped: 停止进程数
		* zombie: 僵尸进程数
	3. Cpu(s) (第三行) 表示CPU状态信息
		* us: 用户空间占用CPU百分比
		* sy: 系统空间占用CPU百分比
		* ni: 改变过优先级的进程占用CPU的百分比
		* id: 空闲CPU百分比
		* wa: IO等待占用CPU的百分比
		* hi: 硬中断（Hardware IRQ）占用CPU的百分比
		* si: 软中断（Software Interrupts）占用CPU的百分比
	4. Mem: （第四行） 表示内存状态信息 单位：K
		* total: 物理内存总量
		* used: 已使用内存总量
		* free: 空闲内存总量
		* buff/cache: 缓存的内存总量
	5. Swap: （第五行） 表示交换区状态信息 单位：K
		* total: 交换区总量
		* free: 空闲交换区总量
		* used: 已使用的交换区总量
		* avail Mem: 缓冲的交换区总量
	6. 列表：
		* PID: 进程标识
		* USER: 进程所有者
		* PR: 进程优先级
		* NI: nice值。负值表示高优先级，正值表示低优先级
		* VIRT: 进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES
		* RES: 进程使用的、未被换出的物理内存大小，单位kb。RES=CODE+DATA
		* SHR: 共享内存大小，单位kb
		* S: 进程状态。D=不可中断的睡眠状态 R=运行 S=睡眠 T=跟踪/停止 Z=僵尸进程
		* %CPU: 上次更新到现在的CPU时间占用百分比
		* %MEM: 进程使用的物理内存百分比
		* TIME+: 进程使用的CPU时间总计，单位1/100秒
		* COMMAND: 进程名称（命令名/命令行）
###结果分析
1. 对于内存监控，如果发现swap的used的数值在不断的变化，说明内核在不断的进行内存和swap的数据交换，表明内存不够用了。

##命令使用技巧
1. 按`1`可显示多个逻辑CPU状态
2. 按`b`打开/关闭加亮效果
3. 按`y`打开/关闭运行状态进程的加亮效果
4. 按`x`打开/关闭排序列的加亮效果
5. 通过`shift + >`或`shift + <`可以向左向右改变排序列

##示例
* 显示完整命令 `top -c`

```
[suoper@prod-java-bigdata-26 ~]$ top -c
top - 15:32:29 up 42 days, 14:30,  1 user,  load average: 2.46, 2.65, 2.73
Tasks: 150 total,   1 running, 149 sleeping,   0 stopped,   0 zombie
%Cpu(s): 38.1 us,  0.3 sy,  0.0 ni, 61.5 id,  0.0 wa,  0.0 hi,  0.0 si,  0.2 st
KiB Mem :  8010336 total,  1765524 free,  4437740 used,  1807072 buff/cache
KiB Swap:  8388604 total,  7853256 free,   535348 used.  3233948 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
20687 suoper    20   0 4867376 647768   7656 S 103.3  8.1  25653:16 /usr/local/jdk/bin/java -Xms512m -Xmx512m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume+
14947 suoper    20   0 4728104 432596   3428 S 102.3  5.4  37894:14 /usr/local/jdk/bin/java -Xms512m -Xmx512m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume+
26274 suoper    20   0 4800812 642612   5936 S 102.0  8.0  27866:20 /usr/local/jdk/bin/java -Xms512m -Xmx512m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume+
11412 suoper    20   0 7195244 887576  24048 S   2.0 11.1  67:26.31 /usr/local/jdk/bin/java -Xms1024m -Xmx1024m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflu+
12824 suoper    20   0 7164556 804640  24048 S   1.0 10.0  47:24.89 /usr/local/jdk/bin/java -Xms1024m -Xmx1024m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflu+
12115 suoper    20   0 7173380 919564  24008 S   0.7 11.5  70:24.50 /usr/local/jdk/bin/java -Xms1024m -Xmx1024m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflu+
   21 root      20   0       0      0      0 S   0.3  0.0   1:28.59 [rcuos/3]
19799 suoper    20   0  146292   2208   1452 R   0.3  0.0   0:00.27 top -c
    1 root      20   0   44928   6132   1428 S   0.0  0.1   1:25.30 /usr/lib/systemd/systemd --switched-root --system --deserialize 21
    2 root      20   0       0      0      0 S   0.0  0.0   0:00.22 [kthreadd]
    3 root      20   0       0      0      0 S   0.0  0.0   0:01.96 [ksoftirqd/0]
    5 root       0 -20       0      0      0 S   0.0  0.0   0:00.00 [kworker/0:0H]
    7 root      rt   0       0      0      0 S   0.0  0.0   0:04.19 [migration/0]
    8 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcu_bh]
    9 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/0]
   10 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/1]
   11 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/2]
   12 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/3]
   13 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/4]
   14 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/5]
   15 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/6]
   16 root      20   0       0      0      0 S   0.0  0.0   0:00.00 [rcuob/7]
   17 root      20   0       0      0      0 S   0.0  0.0  13:20.64 [rcu_sched]
   18 root      20   0       0      0      0 S   0.0  0.0   1:37.55 [rcuos/0]
```

* 显示指定的进程信息 `top -p 26274`

```
[suoper@prod-java-bigdata-26 ~]$ top -p 26274
top - 15:35:12 up 42 days, 14:32,  1 user,  load average: 3.12, 2.79, 2.76
Tasks:   1 total,   0 running,   1 sleeping,   0 stopped,   0 zombie
%Cpu(s): 38.5 us,  0.3 sy,  0.0 ni, 61.1 id,  0.0 wa,  0.0 hi,  0.0 si,  0.1 st
KiB Mem :  8010336 total,  1757576 free,  4445080 used,  1807680 buff/cache
KiB Swap:  8388604 total,  7853256 free,   535348 used.  3226404 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
26274 suoper    20   0 4800812 642612   5936 S 101.3  8.0  27869:08 java
```

* 监视某进程下的所有线程运行状态 `top -p 26274 -H`

```
[suoper@prod-java-bigdata-26 ~]$ top -p 26274 -H
top - 15:46:41 up 42 days, 14:44,  1 user,  load average: 3.34, 2.96, 2.84
Threads:  41 total,   1 running,  40 sleeping,   0 stopped,   0 zombie
%Cpu(s): 39.1 us,  0.3 sy,  0.0 ni, 60.1 id,  0.0 wa,  0.0 hi,  0.0 si,  0.5 st
KiB Mem :  8010336 total,  1754840 free,  4445176 used,  1810320 buff/cache
KiB Swap:  8388604 total,  7853256 free,   535348 used.  3226280 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
26649 suoper    20   0 4800812 642612   5936 R 95.0  8.0  25845:39 java
26614 suoper    20   0 4800812 642612   5936 S  1.7  8.0 230:48.84 java
26617 suoper    20   0 4800812 642612   5936 S  1.7  8.0 230:54.00 java
26615 suoper    20   0 4800812 642612   5936 S  1.3  8.0 231:14.12 java
26618 suoper    20   0 4800812 642612   5936 S  1.3  8.0 230:59.96 java
26619 suoper    20   0 4800812 642612   5936 S  1.3  8.0 231:03.62 java
26613 suoper    20   0 4800812 642612   5936 S  0.7  8.0 230:48.99 java
26616 suoper    20   0 4800812 642612   5936 S  0.7  8.0 230:38.78 java
26620 suoper    20   0 4800812 642612   5936 S  0.7  8.0 230:45.65 java
26621 suoper    20   0 4800812 642612   5936 S  0.7  8.0 164:18.21 java
26274 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.06 java
26611 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.54 java
26622 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:01.32 java
26623 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.68 java
26624 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.00 java
26625 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:15.58 java
26626 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:12.49 java
26627 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:12.94 java
26628 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:08.36 java
26629 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.00 java
26630 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.00 java
26631 suoper    20   0 4800812 642612   5936 S  0.0  8.0  13:21.10 java
26632 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:08.47 java
26633 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:04.14 java
26634 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:08.22 java
26635 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:07.78 java
26636 suoper    20   0 4800812 642612   5936 S  0.0  8.0   1:04.86 java
26637 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:08.12 java
26638 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:08.13 java
26639 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:07.71 java
26640 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:00.96 java
26641 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:43.46 java
26643 suoper    20   0 4800812 642612   5936 S  0.0  8.0   1:24.18 java
26644 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:02.34 java
26645 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:07.93 java
26646 suoper    20   0 4800812 642612   5936 S  0.0  8.0   1:31.41 java
26648 suoper    20   0 4800812 642612   5936 S  0.0  8.0   1:28.99 java
26650 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:07.72 java
26652 suoper    20   0 4800812 642612   5936 S  0.0  8.0   0:07.83 java
```

##top交互命令
* h: 显示帮助画面，给出一些简短的命令总结说明
* k: 终止一个进程
* i: 忽略闲置和僵死进程。这是一个开关式命令
* q: 退出程序
* r: 重新安排一个进程的优先级别
* S: 切换到累计模式
* s: 改变两次刷新之间的延迟时间（单位为s），如果有小数，就换算成m s。输入0值则系统将不断刷新，默认值是5 s
* f或F: 从当前显示中添加或者删除项目
* o或O: 改变显示项目的顺序
* l: 切换显示平均负载和启动时间信息
* m: 切换显示内存信息
* t: 切换显示进程和CPU状态信息
* c: 切换显示命令名称和完整命令行
* M: 根据驻留内存大小进行排序
* P: 根据CPU使用百分比大小进行排序
* T: 根据时间/累计时间进行排序
* w: 将当前设置写入~/.toprc文件中


