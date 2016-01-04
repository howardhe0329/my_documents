#Kafka broker

###启动server

    nohup ./kafka-server-start.sh ../config/server.properties 2>&1 &
    
###停止server

    ./kafka-server-stop.sh
    
###创建topic

    ./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test --config xxx=xxx
    
replication-factor 创建逼本数量 partitions 分区数量

###查看topic列表

    ./kafka-topics.sh --list --zookeeper localhost:2181
    
###修改topic

    ./kafka-topics.sh --alter --zookeeper localhost:2181 --topic test --config xxx=xxx
    
###增加topic的partition数量

    ./kafka-topics.sh --describe --topic nginx_services --zookeeper 192.168.168.3:2181
    
###删除topic

    ./kafka-topics.sh --zookeeper localhost:2181 --delete  --topic __consumer_offsets

###kafka-preferred-replica-election.sh
在创建一个topic时，kafka尽量将partition均分在所有的brokers上，并且将replicas也j均分在不同的broker上。
每个partitiion的所有replicas叫做"assigned replicas"，"assigned replicas"中的第一个replicas叫"preferred replica"，刚创建的topic一般"preferred replica"是leader。leader replica负责所有的读写。
但随着时间推移，broker可能会停机，会导致leader迁移，导致机群的负载不均衡。我们期望对topic的leader进行重新负载均衡，让partition选择"preferred replica"做为leader。

    ./bin/kafka-preferred-replica-election.sh --zookeeper localhost:2181
    
###查看topic节点信息

    ./kafka-topics.sh --describe --zookeeper localhost:2181 --topic test

输出结果：

    Topic:test	PartitionCount:1	ReplicationFactor:1	Configs:
    	Topic: test	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
    	
leader: 负责消息的读和写；   replicas: 列出所有副本的节点；    isr: 正在服务中的节点
    
###发送消息

    ./kafka-console-producer.sh --broker-list localhost:9092 --topic test
    
###接收消息

    ./kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
    
###查看consoumer offset位置

    bin/kafka-run-class.sh kafka.tools.ConsumerOffsetChecker --zkconnect localhost:2181 --group test
    

    

    
