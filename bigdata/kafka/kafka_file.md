#Kafka文件存储结构介绍
> 上一节介绍了kafka的基本概念和架构, 本节主要介绍kafka的文件存储结构.

###文件系统架构

###文件系统结构-目录
> 我们知道一个Topic包含一个或多个Partition. 假如kafka集群中只有一个broker, 数据文件目录为/tmp/kafka-logs(在server.properties里可以设置路径).
我们现在开始创建一个topic名为nginx_services, partition有16, 我们通过kafka-topics.sh命令可以查看topic的结构:

    [hadoop@node1 bin]$ ./kafka-topics.sh --describe --topic nginx_services --zookeeper 192.168.10.71:2181
    Topic:nginx_services	PartitionCount:16	ReplicationFactor:2	Configs:
    	Topic: nginx_services	Partition: 0	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 1	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 2	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 3	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 4	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 5	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 6	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 7	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 8	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 9	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 10	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 11	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 12	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 13	Leader: -1	Replicas: 0,1	Isr:
    	Topic: nginx_services	Partition: 14	Leader: -1	Replicas: 1,0	Isr:
    	Topic: nginx_services	Partition: 15	Leader: -1	Replicas: 0,1	Isr:
    	
    	PartitionCount 是partition的数量; ReplicationFactor: 副本数量; Replicas: 1,0 代表broker.id 0,1两个节点; Isr: 正在服务中的节点;
        Learder: 负责消息的读和写.

###目录结构:

    [hadoop@node2 kafka-logs]$ ll
    总用量 72
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 29 11:10 nginx_services-0
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 29 11:25 nginx_services-1
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 12:30 nginx_services-10
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 29 11:45 nginx_services-11
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 12:15 nginx_services-12
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 24 17:10 nginx_services-13
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 15:45 nginx_services-14
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 29 11:05 nginx_services-15
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 15:40 nginx_services-2
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 10:45 nginx_services-3
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 12:35 nginx_services-4
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 10:55 nginx_services-5
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 29 11:10 nginx_services-6
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 22 15:20 nginx_services-7
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 22 16:05 nginx_services-8
    drwxrwxr-x. 2 hadoop hadoop 4096 12月 28 15:55 nginx_services-9
    
每个partition目录名字是topic名称-partition序号(topic-partition_index), 如果kafka是集群的话,会把这些partition均匀的分布到各个broker中.
每个partition目录中存储着海量的message消息,那它是怎么存储的呢?文件存储结构又是怎样的? 下面给大家分析一下.
下面我进入partition目录中看看里面存储着是什么

    [hadoop@168-6-tomcat nginx_services-0]$ ll
    总用量 24721216
    -rw-rw-r-- 1 hadoop hadoop     239264 12月 24 19:01 00000000000010574524.index
    -rw-rw-r-- 1 hadoop hadoop 1073716822 12月 24 19:01 00000000000010574524.log
    -rw-rw-r-- 1 hadoop hadoop     310712 12月 25 08:46 00000000000011172372.index
    -rw-rw-r-- 1 hadoop hadoop 1073724203 12月 25 08:45 00000000000011172372.log
    -rw-rw-r-- 1 hadoop hadoop     235992 12月 25 11:55 00000000000011792716.index
    -rw-rw-r-- 1 hadoop hadoop 1073728042 12月 25 11:55 00000000000011792716.log
    -rw-rw-r-- 1 hadoop hadoop     255968 12月 25 16:18 00000000000012382702.index
    -rw-rw-r-- 1 hadoop hadoop 1073726160 12月 25 16:18 00000000000012382702.log
    -rw-rw-r-- 1 hadoop hadoop     249464 12月 25 22:21 00000000000013022594.index
    -rw-rw-r-- 1 hadoop hadoop 1073718237 12月 25 22:21 00000000000013022594.log
    -rw-rw-r-- 1 hadoop hadoop     273096 12月 26 09:59 00000000000013645244.index
    -rw-rw-r-- 1 hadoop hadoop 1073715210 12月 26 09:58 00000000000013645244.log
    
我们看到里面文件名都是带有偏移量的.index和.log文件.这些文件就是所说的segment file. 其中00000000000000000000.index是最开始的文件(我这里没有显示
是因为这些文件会定期清除(可通过server.properties设置文件保留时间),起始偏移量为0.00000000000010574524.index文件起始偏移量为10574524. 以起始偏移
量命名并排序这些文件，那么当消费者要拉取某个消息起始偏移量位置的数据变的相当简单，只要根据传上来的offset**二分查找**文件列表，定位到具体文件 然后将绝对
offset减去文件的起始节点转化为相对offset，即可开始传输数据。

###segment file
> 每个partition里的数据存储在大小相等的多个segment file中.

####segment file组成
* segment data file
* segment index file

此两个文件一一对应并成对的出现.

segment index file索引文件组成结构如下:
00000000000000000000.index为文件名称, 每次记录相应log文件记录的相对条数和物理偏移位置位置, 共8bytes;
当前segment file offset - last seg file offset记录条数  offset, 共4byte;
对应segment file物理偏移地址 position, 4byte

segment data file索引文件组成结构如下:
00000000000000000000.log为文件名称, 
4 byte CRC32：使用crc32算法计算除CRC32这4byte外的buffer;
1 byte “magic"：表示数据文件协议版本号;
1 byte “attributes"：表示标识独立版本，标识压缩类型，编码类型;
key data：可选，可以存储判断或表示这个消息块的元数据信息;
payload data：消息体，该消息体可能会存储多条消息记录，内部是按照序号有序存储的;

###文件结构分析
> 一个消息(message chunk)数据块可能包含多条消息，但同一个数据块中的消息只有一个offset(partiions第多少msg chunk)，
所以当一个消息块有多条数据处理完部分数据发生异常时，消费者重新去取数据，就会再次取得这个数据块，然后消费过的数据就会被重新消费

###kafka客户端访问流程
![](https://github.com/howardhe0329/my_documents/blob/master/apache/kafka/img/kafka_2.png)

* 当建立连接请求时，首先客户端向kafka broker发送连接请求，broker中由Acceptor thread线程接收并建立连接后，把client的socket以轮询方式转交给相应的processor thread。
* 当client向broker发送数据请求，由processor thread处理并接收client数据放到request缓冲区中，以待IO thread进行逻辑处理和计算并把返回result放到response缓冲区中.
* 接着唤醒processor thread，processor thread抱住response队列循环发送所有response数据给client.
##总结
高效文件系统特点

* 一个大文件分成多个小文件段。
* 多个小文件段，容易定时清除或删除已经消费完文件，减少磁盘占用。
* index全部映射到memory直接操作，避免segment file被交换到磁盘增加IO操作次数。
* 根据索引信息，可以确定发送response到consumer的最大大小。
* 索引文件元数据存储用的是相对前一个segment file的offset存储，节省空间大小。


