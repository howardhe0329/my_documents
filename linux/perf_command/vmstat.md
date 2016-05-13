#vmstat命令
> vmstat是Virtual Meomory Statistics（虚拟内存统计）的缩写，可对操作系统的**虚拟内存**、**进程**、**CPU**活动进行监控。

## 命令格式
vmstat [-a] [-n] [-S unit] [delay [ count]]
vmstat [-s] [-n] [-S unit]
vmstat [-m] [-n] [delay [ count]]
vmstat [-d] [-n] [delay [ count]]
vmstat [-p disk partition] [-n] [delay [ count]]
vmstat [-f]
vmstat [-V]
##命令参数
* -a: 显示活跃和非活跃内存
* -f: 显示从系统启动至今的fork数量
* -m: 显示slabinfo
* -n: 只在开始时显示一次各字段名称
* -s: 示内存相关统计信息及多种系统活动数量
* delay: 刷新时间间隔。如果不指定，只显示一条结果
* count: 刷新次数。如果不指定刷新次数，但指定了刷新时间间隔，这时刷新次数为无穷
* -d: 显示磁盘相关统计信息
* -p: 显示指定磁盘分区统计信息
* -S: 使用指定单位显示。参数有 k 、K 、m 、M ，分别代表1000、1024、1000000、1048576字节（byte）。默认单位为K（1024 bytes）
* -V: 显示vmstat版本信息

## 命令输出结果

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat 1
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0 417376 632040  34508 2867100   29   28  1116   338    1    1  7  4 84  5  0
 0  0 417376 631820  34508 2867324    0    0     0     0  171  294  0  0 100  0  0
 0  0 417376 631756  34508 2867340    0    0     0     0  261  385  0  0 100  0  0
 0  0 417376 631660  34516 2867360    0    0     4     0  250  376  0  0 100  0  0
 0  0 417376 631536  34532 2867356    0    0     0   112  127  230  0  0 100  0  0
 0  0 417376 631692  34532 2867368    0    0     0     0  214  337  0  0 100  0  0
 0  0 417376 631476  34532 2867368    0    0     0   180  313  450  0  0 100  0  0
 0  0 417376 631568  34532 2867376    0    0     0     0  254  385  0  0 100  0  0
 0  0 417376 631412  34532 2867384    0    0     0     0  225  364  0  0 100  0  0
 0  0 417376 631412  34532 2867388    0    0     0     0  278  436  0  0 99  0  0
 0  0 417376 631536  34548 2867372    0    0     0   108  272  451  0  0 100  0  0
 0  0 417376 631508  34548 2867400    0    0     0     0  318  483  0  0 100  0  0
 0  0 417376 631696  34548 2867400    0    0     0     0  192  313  0  0 100  0  0
 0  0 417376 631696  34548 2867400    0    0     0     0  149  257  0  0 100  0  0
 0  0 417376 631664  34548 2867408    0    0     0     0  137  226  0  0 100  0  0
 0  0 417376 631648  34548 2867408    0    0     0     0  156  276  0  0 100  0  0
 0  0 417376 631572  34564 2867408    0    0     0   252  307  445  0  0 100  0  0
 0  0 417376 631604  34564 2867412    0    0     0    12  222  351  0  0 100  0  0
 0  0 417376 631620  34564 2867412    0    0     0     0  259  451  0  0 100  0  0
 0  0 417376 631324  34568 2867424    0    0     4     0  290  466  0  0 100  0  0
```
###输出结果说明
* procs: 进程
	1. r: 运行队列中进程数量
	2. b: 等待IO的进程数量
* memory: 内存
	1. swpd: 使用虚拟内存大小
	2. free: 可用内存大小
	3. buff: 用作缓冲的内存大小
	4. cache: 用作缓存的内存大小
* swap: 交换区（虚拟内存）
	1. si: 每秒从交换区写到内存的大小
	2. so: 每秒写入交换区的内存大小
* io: 磁盘IO (块的大小为1024bytes)
	1. bi: 每秒读取的块数
	2. bo: 每秒写入的块数
* system: 系统
	1. in: 每秒中断数，包括时钟中断
	2. cs: 每秒上下文切换数
* cpu: （以百分比表示）
	1. us: 用户进程执行时间(user time)
	2. sy: 系统进程执行时间(system time)
	3. id: 空闲时间(包括IO等待时间),中央处理器的空闲时间 。以百分比表示
	4. wa: 等待IO时间

###输出结果分析
1. 如果***r***经常大于`4`，并且***id***经常少于`40`。表示**CPU负荷很重**。
2. 如果***si***和***so***长期不等于`0`。表示**内存不足**。
3. 如果***disk***经常不为`0`，并且在***b***中的队列大于`3`，表示**IO性能不好**。

##示列
* vmstat 5 5

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat 5 5
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0 417312 543652  16752 2973116   29   28  1116   338    1    1  7  4 84  5  0
 0  0 417312 543716  16780 2973260    0    0     2    22  204  333  0  0 100  0  0
 0  0 417312 543624  16788 2973272    0    0     0     6  255  379  0  1 99  0  0
 0  0 417312 543452  16812 2973300    0    0     1    18  179  298  0  0 100  0  0
 0  0 417312 543468  16836 2973332    0    0     2    23  226  368  0  0 100  0  0
```
在5秒时间间隔进行5次采样

* vmstat -a 2 5

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat -a 2 5
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free  inact active   si   so    bi    bo   in   cs us sy id wa st
 0  0 417312 542184 1213824 3252412   29   28  1116   338    1    1  7  4 84  5  0
 0  0 417312 542316 1213820 3252496    0    0     0    20  146  262  0  0 100  0  0
 0  0 417312 542316 1213820 3252496    0    0     0    40  120  228  0  0 100  0  0
 0  0 417312 542064 1213852 3252556    0    0     8     6  220  359  0  0 100  0  0
 0  0 417312 542060 1213560 3252776    0    0     2     0  239  400  0  0 100  0  0
```
显示非活跃和活跃内存大小，inact:非活跃内存大小; active: 活跃内存大小。

* vmstat -f

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat -f
     14736937 forks
```
查看系统已经fork多少次。这个数据是从/proc/stat中的processes字段里取得的。

* vmstat -s

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat -s
      8010832 K total memory
      4477660 K used memory
      3253964 K active memory
      1216064 K inactive memory
       539568 K free memory
        18328 K buffer memory
      2975276 K swap cache
      8388604 K total swap
       417312 K used swap
      7971292 K free swap
    153679636 non-nice user cpu ticks
          421 nice user cpu ticks
     90493047 system cpu ticks
   1851377226 idle cpu ticks
     99595544 IO-wait cpu ticks
            0 IRQ cpu ticks
       898564 softirq cpu ticks
      1385472 stolen cpu ticks
  24521075678 pages paged in
   7432707910 pages paged out
    161252083 pages swapped in
    154653414 pages swapped out
   3668145070 interrupts
    115973535 CPU context switches
   1457591411 boot time
     14737107 forks
```
查看内存使用的详细信息。这些信息的分别来自于/proc/meminfo,/proc/stat和/proc/vmstat。

* vmstat -d

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat -d
disk- ------------reads------------ ------------writes----------- -----IO------
       total merged sectors      ms  total merged sectors      ms    cur    sec
sdb   29548562 125969608 1290020752 625641923 4228731 150334936 1237227312 2986454856      0 326845
sda   183340095 506970 10701498410 27384109 8464012 1857458 98223296 6137003      0  22563
sdc   53670482 9646662 37058447026 1008466070 35900948 24993834 13742953040 2069265140      0 199726
dm-0  63465727      0 37058440122 1028017887 60035637      0 13742952752 2213825783      0 200773
```
查看磁盘的读写。这些信息主要来自于/proc/diskstats。
merged:表示一次来自于合并的写/读请求，一般系统会把多个连接/邻近的读/写请求合并到一起来操作。

* vmstat -p /dev/sda1

```
[suoper@prod-bigdata-hadoop-19 ~]$ vmstat -p /dev/sda1
sda1          reads   read sectors  writes    requested writes
           183340047 10701500994    8461080   98224184
```
查看/dev/sda1磁盘的读/写。这些信息主要来自于/proc/diskstats。
	1. reads: 来自于这个分区的读的次数
	2. read sectors: 来自于这个分区的读扇区的次数
	3. writes: 来自于这个分区的写的次数
	4. requested writes: 来自于这个分区的写请求次数

* vmstat -m

```
[root@localhost ~]# vmstat -m
Cache                       Num  Total   Size  Pages
ip_conntrack_expect           0      0    136     28
ip_conntrack                  3     13    304     13
ip_fib_alias                 11     59     64     59
ip_fib_hash                  11     59     64     59
AF_VMCI                       0      0    960      4
bio_map_info                100    105   1064      7
dm_mpath                      0      0   1064      7
jbd_4k                        0      0   4096      1
dm_uevent                     0      0   2608      3
dm_tio                        0      0     24    144
dm_io                         0      0     48     77
scsi_cmd_cache               10     10    384     10
sgpool-128                   32     32   4096      1
sgpool-64                    32     32   2048      2
sgpool-32                    32     32   1024      4
sgpool-16                    32     32    512      8
sgpool-8                     45     45    256     15
scsi_io_context               0      0    112     34
ext3_inode_cache          51080  51105    760      5
ext3_xattr                   36     88     88     44
journal_handle               18    144     24    144
journal_head                 56     80     96     40
revoke_table                  4    202     16    202
revoke_record                 0      0     32    112
uhci_urb_priv                 0      0     56     67
UNIX                         13     33    704     11
flow_cache                    0      0    128     30
msi_cache                    33     59     64     59
cfq_ioc_pool                 14     90    128     30
cfq_pool                     12     90    216     18
crq_pool                     16     96     80     48
deadline_drq                  0      0     80     48
as_arq                        0      0     96     40
mqueue_inode_cache            1      4    896      4
isofs_inode_cache             0      0    608      6
hugetlbfs_inode_cache         1      7    576      7
Cache                       Num  Total   Size  Pages
ext2_inode_cache              0      0    720      5
ext2_xattr                    0      0     88     44
dnotify_cache                 0      0     40     92
dquot                         0      0    256     15
eventpoll_pwq                 3     53     72     53
eventpoll_epi                 3     20    192     20
inotify_event_cache           0      0     40     92
inotify_watch_cache           1     53     72     53
kioctx                        0      0    320     12
kiocb                         0      0    256     15
fasync_cache                  0      0     24    144
shmem_inode_cache           254    290    768      5
posix_timers_cache            0      0    128     30
uid_cache                     0      0    128     30
ip_mrt_cache                  0      0    128     30
tcp_bind_bucket               3    112     32    112
inet_peer_cache               0      0    128     30
secpath_cache                 0      0     64     59
xfrm_dst_cache                0      0    384     10
ip_dst_cache                  5     10    384     10
arp_cache                     1     15    256     15
RAW                           3      5    768      5
UDP                           5     10    768      5
tw_sock_TCP                   0      0    192     20
request_sock_TCP              0      0    128     30
TCP                           4      5   1600      5
blkdev_ioc                   14    118     64     59
blkdev_queue                 20     30   1576      5
blkdev_requests              13     42    272     14
biovec-256                    7      7   4096      1
biovec-128                    7      8   2048      2
biovec-64                     7      8   1024      4
biovec-16                     7     15    256     15
biovec-4                      7     59     64     59
biovec-1                     23    202     16    202
bio                         270    270    128     30
utrace_engine_cache           0      0     64     59
Cache                       Num  Total   Size  Pages
utrace_cache                  0      0     64     59
sock_inode_cache             33     48    640      6
skbuff_fclone_cache           7      7    512      7
skbuff_head_cache           319    390    256     15
file_lock_cache               1     22    176     22
Acpi-Operand               4136   4248     64     59
Acpi-ParseExt                 0      0     64     59
Acpi-Parse                    0      0     40     92
Acpi-State                    0      0     80     48
Acpi-Namespace             2871   2912     32    112
delayacct_cache              81    295     64     59
taskstats_cache               4     53     72     53
proc_inode_cache           1427   1440    592      6
sigqueue                      0      0    160     24
radix_tree_node           13166  13188    536      7
bdev_cache                   23     24    832      4
sysfs_dir_cache            5370   5412     88     44
mnt_cache                    26     30    256     15
inode_cache                2009   2009    560      7
dentry_cache              60952  61020    216     18
filp                        479   1305    256     15
names_cache                   3      3   4096      1
avc_node                     14     53     72     53
selinux_inode_security      994   1200     80     48
key_jar                       2     20    192     20
idr_layer_cache              74     77    528      7
buffer_head              164045 164800     96     40
mm_struct                    51     56    896      4
vm_area_struct             1142   1958    176     22
fs_cache                     35    177     64     59
files_cache                  36     55    768      5
signal_cache                 72    162    832      9
sighand_cache                68     84   2112      3
task_struct                  76     80   1888      2
anon_vma                    458    864     24    144
pid                          83    295     64     59
shared_policy_node            0      0     48     77
Cache                       Num  Total   Size  Pages
numa_policy                  37    144     24    144
size-131072(DMA)              0      0 131072      1
size-131072                   0      0 131072      1
size-65536(DMA)               0      0  65536      1
size-65536                    1      1  65536      1
size-32768(DMA)               0      0  32768      1
size-32768                    2      2  32768      1
size-16384(DMA)               0      0  16384      1
size-16384                    5      5  16384      1
size-8192(DMA)                0      0   8192      1
size-8192                     7      7   8192      1
size-4096(DMA)                0      0   4096      1
size-4096                   110    111   4096      1
size-2048(DMA)                0      0   2048      2
size-2048                   602    602   2048      2
size-1024(DMA)                0      0   1024      4
size-1024                   344    352   1024      4
size-512(DMA)                 0      0    512      8
size-512                    433    480    512      8
size-256(DMA)                 0      0    256     15
size-256                   1139   1155    256     15
size-128(DMA)                 0      0    128     30
size-64(DMA)                  0      0     64     59
size-64                    5639   5782     64     59
size-32(DMA)                  0      0     32    112
size-128                    801    930    128     30
size-32                    3005   3024     32    112
kmem_cache                  137    137   2688      1
```
查看系统的slab信息。这组信息来自于/proc/slabinfo。
slab: 由于内核会有许多小对象，这些对象构造销毁十分频繁，比如i-node，dentry，这些对象如果每次构建的时候就向内存要一个页(4kb)，而其实只有几个字节，这样就会非常浪费，为了解决这个问题，就引入了一种新的机制来处理在同一个页框中如何分配小存储区，而slab可以对小对象进行分配,这样就不用为每一个对象分配页框，从而节省了空间，内核对一些小对象创建析构很频繁，slab对这些小对象进行缓冲,可以重复利用,减少内存分配次数。


