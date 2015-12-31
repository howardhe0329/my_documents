#Kafka介绍
> Kafka是一个分布式的、基于发布\订阅的消息系统。

##Kafka设计目标

1. 以时间复杂度为O(1)的方式提供消息持久化能力，即使对TB级以上数据也能保证常数时间复杂度的访问性能。
2. 高吞吐率。即使在非常廉价的商用机器上也能做到单机支持每秒100K条以上消息的传输。
3. 支持Kafka Server间的消息分区，及分布式消费，同时保证每个Partition内的消息顺序传输。
4. 同时支持离线数据处理和实时数据处理。
5. Scale out：支持在线水平扩展。

##Kafka架构
基本概念：

1. Broker
    Kafka集群包含一个或多个服务器
2. Topic
	每条消息都有一个类别，这个类别被称为Topic。物理上不同的Topic的消息分开存储，逻辑上一个Topic的消息虽然保存于一个或多个broker上但用户只需指定消息的Topic即可生产或消费数据而不必关心数据存于何处。
3. Partition
	Parition是物理上的概念，每个Topic包含一个或多个Partition。
4. Producer
	负责发布消息到Kafka broker
5. Consumer
	消息消费者，向Kafka broker读取消息的客户端。
6. Consumer Group
	每个Consumer属于一个特定的Consumer Group（可为每个Consumer指定group name，若不指定group name则属于默认的group。
Kafka的整体架构非常简单，是显式分布式架构，producer、broker（kafka）和consumer都可以有多个。Producer，consumer实现Kafka注册的接口，数据从producer发送到broker，broker承担一个中间缓存和分发的作用。broker分发注册到系统中的consumer。broker的作用类似于缓存，即活跃的数据和离线处理系统之间的缓存。客户端和服务器端的通信，是基于简单，高性能，且与编程语言无关的TCP协议
![](https://github.com/howardhe0329/my_documents/blob/master/apache/kafka/kafka_1.png)
##Kafka消息发送流程
![](https://github.com/howardhe0329/my_documents/blob/master/apache/kafka/kafka.png)
1. Producer根据指定的partition方法（round-robin、hash等），将消息发布到指定topic的partition里面。
2. kafka集群接收到Producer发过来的消息后，将其持久化到硬盘，并保留消息指定时长（可配置），而不关注消息是否被消费。
3. Consumer从kafka集群pull数据，并控制获取消息的offset。

##Kafka设计
	
1. 吞吐量
	高吞吐是kafka需要实现的核心目标之一，为此kafka做了以下一些设计:
		1. 数据磁盘持久化：消息不在内存中cache，直接写入到磁盘，充分利用磁盘的顺序读写性能
		2. zero-copy：减少IO操作步骤
		3. 数据批量发送
		4. 数据压缩
		5. Topic划分为多个partition，提高parallelism
2. 负载均衡
		1. producer根据用户指定的算法，将消息发送到指定的partition
		2. 存在多个partiiton，每个partition有自己的replica，每个replica分布在不同的Broker节点上
		3. 多个partition需要选取出lead partition，lead partition负责读写，并由zookeeper负责fail over
		4. 通过zookeeper管理broker与consumer的动态加入与离开
3. 拉取系统
	由于kafka broker会持久化数据，broker没有内存压力，因此，consumer非常适合采取pull的方式消费数据，具有以下几点好处:
		1. consumer根据消费能力自主控制消息拉取速度
		2. consumer根据自身情况自主选择消费模式，例如批量，重复消费，从尾端开始消费等
4. 可扩展性
	当需要增加broker结点时，新增的broker会向zookeeper注册，而producer及consumer会根据注册在zookeeper上的watcher感知这些变化，并及时作出调整

##Kayka的应用场景

1. 消息队列
2. 行为跟踪
3. 元信息监控
4. 日志收集
5. 流处理
6. 事件源
7. 持久性日志（commit log）