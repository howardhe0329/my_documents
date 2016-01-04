#Topic config
> kafka topic的配置参数,配置topic级别参数时，相同(参数)属性topic级别会覆盖全局的，否则默认为全局配置属性值。
创建topic参数可以设置一个或多个--config "Property(属性)"

###创建topic配置参数
    
    ./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 4 --topic test2 --config max.message.bytes=64000 --config flush.message=1

###修改topic配置参数

    ./kafka-topics.sh --zookeeper localhost:2181 --alter --topic test2 --config max.message.bytes=128000
    
###删除topic配置参数

    ./kafka-topics.sh --zookeeper localhost:2181 --alter --topic test2 --delete-config max.message.bytes
    
###topic在zookeeper存储信息
> topic配置信息都在zookeeper上存储, 在路径/config/topics/下

    ./zkCli.sh
    
    [zk: localhost:2181(CONNECTED) 11] get /config/topics/test2
    {"version":1,"config":{"max.message.bytes":"64000","flush.messages":"1"}}
    cZxid = 0x400000216
    ctime = Mon Jan 04 11:48:56 CST 2016
    mZxid = 0x400000216
    mtime = Mon Jan 04 11:48:56 CST 2016
    pZxid = 0x400000216
    cversion = 0
    dataVersion = 0
    aclVersion = 0
    ephemeralOwner = 0x0
    dataLength = 73
    numChildren = 0
    
###topic配置列表

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |