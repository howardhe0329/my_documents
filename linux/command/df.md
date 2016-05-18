# df命令
> 用来检查linux服务器的文件系统的磁盘空间占用情况。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。

##命令格式
df [选项][文件]
##命令参数
* -a 全部文件系统列表
* -h 方便阅读方式显示
* -H 等于“-h”，但是计算式，1K=1000，而不是1K=1024
* -i 显示inode信息
* -k 区块为1024字节
* -l 只显示本地文件系统
* -m 区块为1048576字节
* --no-sync 忽略 sync 命令
* -P 输出格式为POSIX
* --sync 在取得磁盘信息前，先执行sync命令
* -T 文件系统类型
* --block-size <区块大小> 指定区块大小
* -t <文件系统类型> 只显示选定文件系统的磁盘信息
* -x <文件系统类型> 不显示选定文件系统的磁盘信息
* --help 显示帮助信息
* --version 显示版本信息

##命令结果

```
[suoper@prod-bigdata-hadoop-19 ~]$ df -h
文件系统                   容量  已用  可用 已用% 挂载点
/dev/sda1                   20G  4.8G   14G   26% /
devtmpfs                   3.9G     0  3.9G    0% /dev
tmpfs                      3.9G     0  3.9G    0% /dev/shm
tmpfs                      3.9G  257M  3.6G    7% /run
tmpfs                      3.9G     0  3.9G    0% /sys/fs/cgroup
/dev/mapper/vgData-lvData   99G   38G   56G   41% /data
tmpfs                      783M     0  783M    0% /run/user/0
tmpfs                      783M     0  783M    0% /run/user/1000
```

##示例
* 列出文件系统的类型 `df -T`

```
[suoper@prod-bigdata-hadoop-19 ~]$ df -T
文件系统                  类型         1K-块     已用     可用 已用% 挂载点
/dev/sda1                 ext4      20510288  5018920 14426460   26% /
devtmpfs                  devtmpfs   3996332        0  3996332    0% /dev
tmpfs                     tmpfs      4005416        0  4005416    0% /dev/shm
tmpfs                     tmpfs      4005416   262596  3742820    7% /run
tmpfs                     tmpfs      4005416        0  4005416    0% /sys/fs/cgroup
/dev/mapper/vgData-lvData ext4     103072696 39240752 58573092   41% /data
tmpfs                     tmpfs       801084        0   801084    0% /run/user/0
tmpfs                     tmpfs       801084        0   801084    0% /run/user/1000
```

