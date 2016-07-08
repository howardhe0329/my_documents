# ps命令
> 是Process Status的缩写。用来列出系统中当前运行的那些进程。列出的是当前那些进程的快照，就是执行ps命令的那个时刻的那些进程，如果想要动态的显示进程信息，就可以使用top命令。

##linux进程状态
1. 运行(正在运行或在运行队列中等待) 
2. 中断(休眠中, 受阻, 在等待某个条件的形成或接受到信号) 
3. 不可中断(收到信号不唤醒和不可运行, 进程必须等待直到有中断发生) 
4. 僵死(进程已终止, 但进程描述符存在, 直到父进程调用wait4()系统调用后释放)
5. 停止(进程收到SIGSTOP, SIGSTP, SIGTIN, SIGTOU信号后停止运行运行) 

##ps命令显示进程状态码：
1. D 不可中断 uninterruptible sleep(usually IO)
2. R 运行 runnable(on run queue)
3. S 中断 sleeping
4. T 停止 traced or stopped
5. Z 僵死 a defunct (”zombie”) process

##命令格式
ps [参数]
##命令参数
* a 显示所有进程
* -a 显示同一终端下的所有程序
* -A 显示所有进程
* c 显示进程的真实名称
* -N 反向选择
* -e 等于“-A”
* e 显示环境变量
* f 显示程序间的关系
* -H 显示树状结构
* r 显示当前终端的进程
* T 显示当前终端的所有程序
* u 指定用户的所有进程
* -au 显示较详细的资讯
* -aux 显示所有包含其他使用者的行程
* -C <命令> 列出指定命令的状况
* --lines <行数> 每页显示的行数
* --width <字符数> 每页显示的字符数
* --help 显示帮助信息
* --version 显示版本信息

##命结果
命令 `ps -aux`

```
[suoper@prod-java-bigdata-26 ~]$ ps aux
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0  45272  6484 ?        Ss   4月01   1:35 /usr/lib/systemd/systemd --switched-root --system --deserialize 21
root         2  0.0  0.0      0     0 ?        S    4月01   0:00 [kthreadd]
root         3  0.0  0.0      0     0 ?        S    4月01   0:02 [ksoftirqd/0]
root         5  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/0:0H]
root         7  0.0  0.0      0     0 ?        S    4月01   0:04 [migration/0]
root       349  0.0  0.0      0     0 ?        S<   4月01   0:00 [ext4-rsv-conver]
root       350  0.0  0.0      0     0 ?        S<   4月01   0:00 [ext4-unrsv-conv]
root       418  0.0  0.2  61392 22180 ?        Ss   4月01   1:27 /usr/lib/systemd/systemd-journald
root       444  0.0  0.0      0     0 ?        S<   4月01   0:00 [rpciod]
root       449  0.0  0.0  43048   372 ?        Ss   4月01   0:00 /usr/lib/systemd/systemd-udevd
root       468  0.0  0.0 116720   448 ?        S<sl 4月01   0:05 /sbin/auditd -n
root       537  0.0  0.0      0     0 ?        S    4月01   4:00 [vballoon]
root       560  0.0  0.0  80200   288 ?        Ssl  4月01  36:52 /usr/bin/gapd
root       562  0.0  0.0  26396   940 ?        Ss   4月01   0:28 /usr/lib/systemd/systemd-logind
root       566  0.0  0.0  19308   480 ?        Ss   4月01   2:06 /usr/sbin/irqbalance --foreground
root       569  0.0  0.0 448224  2896 ?        Ssl  4月01   0:22 /usr/sbin/NetworkManager --no-daemon
avahi      570  0.0  0.0  27984   544 ?        Ss   4月01   0:02 avahi-daemon: running [prod-java-bigdata-26.local]
root       571  0.0  0.1 418488 13084 ?        Ssl  4月01   0:33 /usr/sbin/rsyslogd -n
dbus       572  0.0  0.0  26732   928 ?        Ss   4月01   0:35 /bin/dbus-daemon --system --address=systemd: --nofork --nopidfile --systemd-activation
root       574  0.0  0.0   4336   252 ?        Ss   4月01   0:00 /usr/sbin/acpid
avahi      577  0.0  0.0  27984    20 ?        S    4月01   0:00 avahi-daemon: chroot helper
root       578  0.0  0.0 203352   180 ?        Ssl  4月01   0:03 /usr/sbin/gssproxy -D
root       591  0.0  0.0 126360   556 ?        Ss   4月01   0:12 /usr/sbin/crond -n
root       595  0.0  0.0 110032   292 ttyS0    Ss+  4月01   0:00 /sbin/agetty --keep-baud 115200 38400 9600 ttyS0 vt220
root       596  0.0  0.0 110032   292 tty1     Ss+  4月01   0:00 /sbin/agetty --noclear tty1 linux
root       597  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/0:1H]
root       598  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/3:1H]
root       607  0.0  0.0  53056   280 ?        Ss   4月01   0:00 /usr/sbin/wpa_supplicant -u -f /var/log/wpa_supplicant.log -c /etc/wpa_supplicant/wpa_supplicant.conf -u -f /var/lo
polkitd    608  0.0  0.0 522704  1112 ?        Ssl  4月01   0:12 /usr/lib/polkit-1/polkitd --no-debug
root       610  0.0  0.0 110512   936 ?        S    4月01   0:22 /sbin/dhclient -d -q -sf /usr/libexec/nm-dhcp-helper -pf /var/run/dhclient-eth0.pid -lf /var/lib/NetworkManager/dhc
root       796  0.0  0.0  82548   620 ?        Ss   4月01   0:00 /usr/sbin/sshd -D
root      2606  0.0  0.0      0     0 ?        S    5月09   0:17 [kworker/u16:0]
root      3172  0.0  0.0      0     0 ?        S    5月09   0:00 [kworker/u16:2]
root      3978  0.0  0.0      0     0 ?        S<   4月01   0:30 [kworker/2:1H]
root      4335  0.0  0.0      0     0 ?        S    15:50   0:00 [kworker/2:2]
root      5496  0.0  0.0      0     0 ?        S    16:00   0:00 [kworker/7:0]
root      5603  0.0  0.0      0     0 ?        S    16:01   0:00 [kworker/0:1]
root      6004  0.0  0.0      0     0 ?        S    07:10   0:00 [kworker/5:2]
root      6032  0.0  0.0      0     0 ?        S    5月15   0:01 [kworker/5:0]
root      9231  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/4:1H]
root      9300  0.0  0.0 138704  4828 ?        Ss   16:26   0:00 sshd: suoper [priv]
suoper    9302  0.0  0.0 138704  2072 ?        S    16:26   0:00 sshd: suoper@pts/0
suoper    9303  0.0  0.0 115636  2080 pts/0    Ss+  16:26   0:00 -bash
root      9404  0.0  0.0      0     0 ?        S    5月14   0:02 [kworker/7:2]
root      9742  0.0  0.0      0     0 ?        S    16:30   0:00 [kworker/4:1]
root      9836  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/5:1H]
root     10685  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/1:1H]
suoper   11412  1.2 12.9 7326828 1036720 ?     Sl   5月09 144:49 /usr/local/jdk/bin/java -Xms1024m -Xmx1024m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume.m
root     11826  0.0  0.0      0     0 ?        S    5月16   0:00 [kworker/1:0]
root     11921  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/6:1H]
root     12086  0.0  0.0      0     0 ?        S    5月15   0:01 [kworker/4:2]
suoper   12115  1.3 14.4 7423184 1154016 ?     Sl   5月09 155:49 /usr/local/jdk/bin/java -Xms1024m -Xmx1024m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume.m
root     12797  0.0  0.0      0     0 ?        S    16:50   0:00 [kworker/6:0]
suoper   12824  1.2 20.1 7776216 1613716 ?     Sl   5月09 142:08 /usr/local/jdk/bin/java -Xms1024m -Xmx1024m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume.m
root     13073  0.0  0.0      0     0 ?        S    08:10   0:00 [kworker/3:1]
root     14078  0.0  0.0 138704  4820 ?        Ss   17:00   0:00 sshd: suoper [priv]
suoper   14080  0.0  0.0 138704  2068 ?        R    17:00   0:00 sshd: suoper@pts/5
suoper   14081  0.0  0.0 115636  2096 pts/5    Ss   17:00   0:00 -bash
root     14219  0.0  0.0      0     0 ?        S    08:20   0:00 [kworker/1:2]
root     14504  0.0  0.0      0     0 ?        S<   4月01   0:00 [kworker/7:1H]
suoper   14947  104  5.4 4728104 433024 ?      Sl   4月18 44033:16 /usr/local/jdk/bin/java -Xms512m -Xmx512m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume.m
suoper   15259  0.0  0.0 139496  1588 pts/5    R+   17:07   0:00 ps aux
root     17191  0.0  0.0      0     0 ?        S    5月14   0:01 [kworker/6:1]
root     20353  0.0  0.0      0     0 ?        S    5月15   0:01 [kworker/2:1]
suoper   20687  103  8.0 4867376 648812 ?      Sl   4月26 31723:44 /usr/local/jdk/bin/java -Xms512m -Xmx512m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume.m
suoper   26274  103  8.4 4800812 676348 ?      Sl   4月24 33937:41 /usr/local/jdk/bin/java -Xms512m -Xmx512m -Dcom.sun.management.jmxremote -Dflume.root.logger=info,DAILY -Dflume.m
root     29207  0.0  0.0      0     0 ?        S    05:19   0:01 [kworker/0:2]
root     31677  0.0  0.0      0     0 ?        S    15:10   0:00 [kworker/3:2]
```
###结果说明
* USER: 该process属于那个使用者账号的
* PID: 该process的ID
* %CPU: 该process使用掉的CPU资源百分比
* %MEM: 该process所占用的物理内存百分比
* VSZ: 该process使用掉的虚拟内存量(Kbytes)
* RSS: 该process占用的固定的内存量(Kbytes)
* TTY: 该process是在那个终端机上面运作，若与终端机无关，则显示?，另外，tty1-tty6是本机上面的登入者程序，若为pts/0等等的，则表示为由网络连接进主机的程序
* STAT: 该process状态
	* R: 该程序目前正在运作，或者是可被运作
	* S: 该程序目前正在睡眠当中 (可说是 idle 状态)，但可被某些讯号 (signal) 唤醒
	* T: 该程序目前正在侦测或者是停止了
	* Z: 该程序应该已经终止，但是其父程序却无法正常的终止他，造成zombie(疆尸)程序的状态
* START: 该process被触发启动的时间
* TIME: 该process实际使用CPU运作的时间
* COMMAND: 该process的实际指令

##示例
* 显示所有进程信息 `ps -A`

```
[suoper@prod-java-bigdata-26 ~]$ ps -A
  PID TTY          TIME CMD
    1 ?        00:01:35 systemd
    2 ?        00:00:00 kthreadd
    3 ?        00:00:02 ksoftirqd/0
    5 ?        00:00:00 kworker/0:0H
    7 ?        00:00:04 migration/0
    8 ?        00:00:00 rcu_bh
    9 ?        00:00:00 rcuob/0
   10 ?        00:00:00 rcuob/1
   11 ?        00:00:00 rcuob/2
   12 ?        00:00:00 rcuob/3
   13 ?        00:00:00 rcuob/4
   14 ?        00:00:00 rcuob/5
   15 ?        00:00:00 rcuob/6
   16 ?        00:00:00 rcuob/7
```

* 显示指定用户信息 `ps -u root`

```
[suoper@prod-java-bigdata-26 ~]$ ps -u suoper
  PID TTY          TIME CMD
 9302 ?        00:00:00 sshd
 9303 pts/0    00:00:00 bash
11412 ?        02:25:01 java
12115 ?        02:36:01 java
12824 ?        02:22:31 java
14080 ?        00:00:00 sshd
14081 pts/5    00:00:00 bash
14947 ?        30-14:06:34 java
17240 pts/5    00:00:00 ps
20687 ?        22-00:57:20 java
26274 ?        23-13:51:45 java
```

* 显示所有进程信息，连同命令行 `ps -ef`
* 找出与cron与syslog这两个服务有关的PID `ps -aux|egrep '(cron|syslog)'`

```
[suoper@prod-java-bigdata-26 ~]$ ps -aux |egrep '(cron|syslog)'
root       571  0.0  0.1 418488 13084 ?        Ssl  4月01   0:33 /usr/sbin/rsyslogd -n
root       591  0.0  0.0 126360   556 ?        Ss   4月01   0:12 /usr/sbin/crond -n
```

* 查看线程数 `ps -eLf |grep java -c`