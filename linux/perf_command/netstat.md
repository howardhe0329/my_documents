# netstat命令
> 用于显示与IP、TCP、UDP和ICMP协议相关的统计数据，一般用于检验本机各端口的网络连接情况。netstat是在内核中访问网络及相关信息的程序，它能提供TCP连接，TCP和UDP监听，进程内存管理的相关报告。
> 如果你的计算机有时候接收到的数据报导致出错数据或故障，你不必感到奇怪，TCP/IP可以容许这些类型的错误，并能够自动重发数据报。但如果累计的出错情况数目占到所接收的IP数据报相当大的百分比，或者它的数目正迅速增加，那么你就应该使用netstat查一查为什么会出现这些情况了。

##命令格式
netstat [-acCeFghilMnNoprstuvVwx][-A<网络类型>][--ip]
##命令功能
用于检验本机各端口的网络连接情况。
##命令参数
* -a或–all 显示所有连接中的Socket
* -A<网络类型>或–<网络类型> 列出该网络类型连线中的相关地址
* -c或–continuous 持续列出网络状态
* -C或–cache 显示路由器配置的快取信息
* -e或–extend 显示网络其他相关信息
* -F或–fib 显示FIB
* -g或–groups 显示多重广播功能群组组员名单
* -h或–help 在线帮助
* -i或–interfaces 显示网络界面信息表单
* -l或–listening 显示监控中的服务器的Socket
* -M或–masquerade 显示伪装的网络连接
* -n或–numeric 直接使用IP地址，而不通过域名服务器
* -N或–netlink或–symbolic 显示网络硬件外围设备的符号连接名称
* -o或–timers 显示计时器
* -p或–programs 显示正在使用Socket的程序识别码和程序名称
* -r或–route 显示Routing Table
* -s或–statistice 显示网络工作信息统计表
* -t或–tcp 显示TCP传输协议的连接状况
* -u或–udp 显示UDP传输协议的连线状况
* -v或–verbose 显示指令执行过程
* -V或–version 显示版本信息
* -w或–raw 显示RAW传输协议的连线状况
* -x或–unix 此参数的效果和指定”-A unix”参数相同
* –ip或–inet 此参数的效果和指定”-A inet”参数相同

##输出结果

```
[suoper@prod-bigdata-hadoop-19 ~]$ netstat
Active Internet connections (w/o servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State
tcp        0      0 prod-bigdata-hado:57861 prod-bigdata-hado:50010 ESTABLISHED
tcp        0      0 prod-bigdata-hado:50010 192.168.99.26:56705     TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:54723 TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:38607 ESTABLISHED
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:47660 TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:54725 TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 192.168.99.26:56671     TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:49573 ESTABLISHED
tcp        0      0 prod-bigdata-hado:57853 prod-bigdata-hado:50010 TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:49555 TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:39032 ESTABLISHED
tcp        0      0 prod-bigdata-hado:50010 192.168.99.26:56668     TIME_WAIT
tcp        0      0 prod-bigdata-hado:50010 192.168.99.26:56725     ESTABLISHED
tcp        0      0 prod-bigdata-hado:50010 prod-bigdata-hado:54467 ESTABLISHED
Active UNIX domain sockets (w/o servers)
Proto RefCnt Flags       Type       State         I-Node   Path
unix  2      [ ]         DGRAM                    8414     /run/systemd/notify
unix  2      [ ]         DGRAM                    9713     /run/systemd/shutdownd
unix  5      [ ]         DGRAM                    8435     /run/systemd/journal/socket
unix  12     [ ]         DGRAM                    8437     /dev/log
unix  3      [ ]         STREAM     CONNECTED     13489
unix  3      [ ]         STREAM     CONNECTED     14365
unix  3      [ ]         STREAM     CONNECTED     12815
unix  3      [ ]         STREAM     CONNECTED     13146
unix  3      [ ]         STREAM     CONNECTED     14366
unix  3      [ ]         STREAM     CONNECTED     36627103
unix  3      [ ]         STREAM     CONNECTED     11041
unix  2      [ ]         DGRAM                    11610
unix  3      [ ]         STREAM     CONNECTED     25978
unix  3      [ ]         STREAM     CONNECTED     12863    /var/run/dbus/system_bus_socket
unix  2      [ ]         DGRAM                    12016
unix  3      [ ]         STREAM     CONNECTED     12559
unix  3      [ ]         STREAM     CONNECTED     14368
unix  3      [ ]         STREAM     CONNECTED     11968
unix  3      [ ]         STREAM     CONNECTED     14364
unix  2      [ ]         DGRAM                    13541
unix  3      [ ]         STREAM     CONNECTED     11048    /run/gssproxy.sock
unix  3      [ ]         STREAM     CONNECTED     13518    /run/systemd/journal/stdout
unix  3      [ ]         STREAM     CONNECTED     36627104
```

###结果说明
* Active Internet connections 有源TCP连接
	* Proto 协议
	* Recv-Q 接收队列
	* Send-Q 发送队列
	* Local Address
	* Foreign Address
	* State
* Active UNIX domain sockets 有源Unix域套接口(和网络套接字一样，但是只能用于本机通信，性能可以提高一倍)
	* Proto 协议
	* RefCnt 连接到本套接口上的进程号
	* Type 显示套接口的类型
	* State 显示套接口当前的状态
	* I-Node
	* Path 表示连接到套接口的其它进程使用的路径名

####套接口类型
* -t: TCP
* -u: UDP
* -raw: RAW类型
* --unix: UNIX域类型
* --ax25: AX25类型
* --ipx: ipx类型
* --netrom: netrom类型

####状态说明
* LISTEN: 侦听来自远方的TCP端口的连接请求
* SYN-SENT: 再发送连接请求后等待匹配的连接请求（如果有大量这样的状态包，检查是否中招了）
* SYN-RECEIVED: 再收到和发送一个连接请求后等待对方对连接请求的确认（如有大量此状态，估计被flood攻击了）
* ESTABLISHED: 代表一个打开的连接
* FIN-WAIT-1: 等待远程TCP连接中断请求，或先前的连接中断请求的确认
* FIN-WAIT-2: 从远程TCP等待连接中断请求
* CLOSE-WAIT: 等待从本地用户发来的连接中断请求
* CLOSING: 等待远程TCP对连接中断的确认
* LAST-ACK: 等待原来的发向远程TCP的连接中断请求的确认（不是什么好东西，此项出现，检查是否被攻击）
* TIME-WAIT: 等待足够的时间以确保远程TCP接收到连接中断请求的确认
* CLOSED: 没有任何连接状态

##示例
* 列出所有端口 `netstat -a`
* 显示当前UDP连接状况 `netstat -nu`
* 显示网卡列表 `netstat -i`

```
[suoper@prod-bigdata-hadoop-19 ~]$ netstat -i
Kernel Interface table
Iface      MTU    RX-OK RX-ERR RX-DRP RX-OVR    TX-OK TX-ERR TX-DRP TX-OVR Flg
eth0      1500 140031721      0      0 0      121928722      0      0      0 BMRU
lo       65536      121      0      0 0           121      0      0      0 LRU
```

* 显示组播组的关系 `netstat -g`

```
[suoper@prod-bigdata-hadoop-19 ~]$ netstat -g
IPv6/IPv4 Group Memberships
Interface       RefCnt Group
--------------- ------ ---------------------
lo              1      all-systems.mcast.net
eth0            1      224.0.0.251
eth0            1      all-systems.mcast.net
lo              1      ff02::1
lo              1      ff01::1
eth0            1      ff02::1:ff9f:8483
eth0            1      ff02::1
eth0            1      ff01::1
```

* 显示网络统计信息 `netstat -s`

```
[suoper@prod-bigdata-hadoop-19 ~]$ netstat -s
Ip:
    139524017 total packets received
    0 forwarded
    0 incoming packets discarded
    139523897 incoming packets delivered
    121536350 requests sent out
Icmp:
    210567 ICMP messages received
    0 input ICMP message failed.
    ICMP input histogram:
        destination unreachable: 210563
        echo requests: 4
    4 ICMP messages sent
    0 ICMP messages failed
    ICMP output histogram:
        echo replies: 4
IcmpMsg:
        InType3: 210563
        InType8: 4
        OutType0: 4
Tcp:
    2493144 active connections openings
    3873489 passive connection openings
    80286 failed connection attempts
    68582 connection resets received
    49 connections established
    139310871 segments received
    196655017 segments send out
    1340827 segments retransmited
    2382 bad segments received.
    87007 resets sent
Udp:
    591 packets received
    0 packets to unknown port received.
    0 packet receive errors
    210706 packets sent
    0 receive buffer errors
    0 send buffer errors
UdpLite:
TcpExt:
    15529 invalid SYN cookies received
    39253 packets pruned from receive queue because of socket buffer overrun
    3578193 TCP sockets finished time wait in fast timer
    320 packets rejects in established connections because of timestamp
    5028052 delayed acks sent
    1816 delayed acks further delayed because of locked socket
    Quick ack mode was activated 141878 times
    16182 times the listen queue of a socket overflowed
    16182 SYNs to LISTEN sockets dropped
    6 packets directly queued to recvmsg prequeue.
    4 bytes directly received in process context from prequeue
    46173749 packet headers predicted
    23063237 acknowledgments not containing data payload received
    40804583 predicted acknowledgments
    17838 times recovered from packet loss by selective acknowledgements
    Detected reordering 12 times using FACK
    Detected reordering 140 times using SACK
    Detected reordering 383 times using time stamp
    163 congestion windows fully recovered without slow start
    272 congestion windows partially recovered using Hoe heuristic
    540 congestion windows recovered without slow start by DSACK
    1069 congestion windows recovered without slow start after partial ack
    TCPLostRetransmit: 41187
    262 timeouts after SACK recovery
    23 timeouts in loss state
    959121 fast retransmits
    12292 forward retransmits
    143612 retransmits in slow start
    8610 other TCP timeouts
    TCPLossProbes: 241404
    TCPLossProbeRecovery: 48758
    352 SACK retransmits failed
    310496 packets collapsed in receive queue due to low socket buffer
    141915 DSACKs sent for old packets
    12 DSACKs sent for out of order packets
    180619 DSACKs received
    44 DSACKs for out of order packets received
    1111 connections reset due to unexpected data
    13781 connections reset due to early user close
    207 connections aborted due to timeout
    TCPDSACKIgnoredOld: 74
    TCPDSACKIgnoredNoUndo: 146035
    TCPSpuriousRTOs: 164
    TCPSackShifted: 650731
    TCPSackMerged: 471960
    TCPSackShiftFallback: 114029
    TCPBacklogDrop: 9219
    TCPRcvCoalesce: 23856485
    TCPOFOQueue: 105735
    TCPOFOMerge: 12
    TCPChallengeACK: 4924
    TCPSYNChallenge: 4252
    TCPSpuriousRtxHostQueues: 3
    TCPAutoCorking: 12862
    TCPFromZeroWindowAdv: 1797
    TCPToZeroWindowAdv: 1861
    TCPWantZeroWindowAdv: 15539
    TCPSynRetrans: 5174
    TCPOrigDataSent: 149599576
    TCPHystartTrainDetect: 29684
    TCPHystartTrainCwnd: 711598
    TCPHystartDelayDetect: 227
    TCPHystartDelayCwnd: 11115
    TCPACKSkippedSynRecv: 4
    TCPACKSkippedPAWS: 127
    TCPACKSkippedSeq: 1428
    TCPACKSkippedTimeWait: 4
    TCPACKSkippedChallenge: 1
IpExt:
    InNoRoutes: 4
    InMcastPkts: 412
    OutMcastPkts: 43
    InBcastPkts: 1868
    InOctets: 48003471212
    OutOctets: 130325149359
    InMcastOctets: 73313
    OutMcastOctets: 6840
    InBcastOctets: 612704
    InNoECTPkts: 139521608
    InECT0Pkts: 2409
```

* 显示监听的套接口 `netstat -l`

```
[suoper@prod-bigdata-hadoop-19 ~]$ netstat -l
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State
tcp        0      0 0.0.0.0:http            0.0.0.0:*               LISTEN
tcp        0      0 0.0.0.0:ssh             0.0.0.0:*               LISTEN
tcp        0      0 0.0.0.0:50010           0.0.0.0:*               LISTEN
tcp        0      0 0.0.0.0:50075           0.0.0.0:*               LISTEN
tcp        0      0 0.0.0.0:50020           0.0.0.0:*               LISTEN
tcp6       0      0 [::]:ssh                [::]:*                  LISTEN
udp        0      0 0.0.0.0:48454           0.0.0.0:*
udp        0      0 0.0.0.0:bootpc          0.0.0.0:*
udp        0      0 0.0.0.0:5336            0.0.0.0:*
udp        0      0 0.0.0.0:mdns            0.0.0.0:*
udp6       0      0 [::]:57407              [::]:*
raw6       0      0 [::]:ipv6-icmp          [::]:*                  7
Active UNIX domain sockets (only servers)
Proto RefCnt Flags       Type       State         I-Node   Path
unix  2      [ ACC ]     STREAM     LISTENING     11037    /run/gssproxy.sock
unix  2      [ ACC ]     STREAM     LISTENING     12868    /var/run/acpid.socket
unix  2      [ ACC ]     STREAM     LISTENING     28521    /run/lvm/lvmpolld.socket
unix  2      [ ACC ]     STREAM     LISTENING     13444    /var/run/NetworkManager/private
unix  2      [ ACC ]     STREAM     LISTENING     11036    /var/lib/gssproxy/default.sock
unix  2      [ ACC ]     STREAM     LISTENING     25014    /run/lvm/lvmetad.socket
unix  2      [ ACC ]     STREAM     LISTENING     9676     /run/systemd/private
unix  2      [ ACC ]     STREAM     LISTENING     13522    /var/run/NetworkManager/private-dhcp
unix  2      [ ACC ]     STREAM     LISTENING     10707    /var/run/avahi-daemon/socket
unix  2      [ ACC ]     STREAM     LISTENING     10710    /var/run/dbus/system_bus_socket
unix  2      [ ACC ]     STREAM     LISTENING     10713    /var/run/rpcbind.sock
unix  2      [ ACC ]     SEQPACKET  LISTENING     9705     /run/udev/control
unix  2      [ ACC ]     STREAM     LISTENING     8432     /run/systemd/journal/stdout
```

* 显示所有已建立的有效连接 `netstat -n`
* 显示关于以太网的统计数据 `netstat -e`
* 显示关于路由表的信息 `netstat -r`

```
[suoper@prod-java-bigdata-26 script]$ netstat -r
Kernel IP routing table
Destination     Gateway         Genmask         Flags   MSS Window  irtt Iface
default         gateway         0.0.0.0         UG        0 0          0 eth0
192.168.99.0    0.0.0.0         255.255.255.0   U         0 0          0 eth0
```

* 列出所有TCP端口 `netstat -at`
* 统计机器中网络连接各个状态个数 `netstat -a | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'`
* 把状态全都取出来后使用uniq -c统计后再进行排序 `netstat -nat |awk '{print $6}'|sort|uniq -c|sort -nr`
* 查看连接某服务端口最多的的IP地址 `netstat -nat | grep "192.168.99.19:50010" |awk '{print $5}'|awk -F: '{print $4}'|sort|uniq -c|sort -nr|head -20`
* 找出程序运行的端口 `netstat -ap | grep ssh`
* 显示 PID 和进程名称 `netstat -pt`
* 找出运行在指定端口的进程 `netstat -anpt | grep ':50010'`

