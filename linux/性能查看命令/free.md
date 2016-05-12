#free命令
> 显示系统内存的使用情况

## free 命令参数
-b	以Byte为单位显示内存使用情况
-k	以KB为单位显示内存使用情况
-m	以MB为单位显示内存使用情况
-g	以GB为单位显示内存使用情况
-o	不显示缓冲区调节列
-s	<间隔秒数> 　持续观察内存使用状况
-t 显示内存总和列
-V 显示版本信息
## free命令结果说明
示例：

```
[suoper@prod-bigdata-hadoop-19 ~]$ free -m
              total        used        free      shared  buff/cache   available
Mem:           7823        4300         418          36        3103        3112
Swap:          8191         420        7771
```

Mem: 内存使用情况
1. total 内存总大小
2. used	已使用内存存大
3. free	空闲内存大小
4. shard	多个进程共享的内存总额
5. buffer/cached	磁盘缓存的大小
6. available	
Swap: 交换区信息 (通常指虚拟内存）
1. total 交换总量
2. used	使用量
3. free 空闲交换区

