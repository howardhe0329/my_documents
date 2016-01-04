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

| Property | Default | server.properties | 说明 |
| -------- |:-------:| :-----------------| :----|
| cleanup.policy | delete | log.cleanup.policy | 日志清理策略选择有：delete和compact主要针对过期数据的处理，或是日志文件达到限制的额度，会被 topic创建时的指定参数覆盖 |
| delete.retention.ms | 86400000 (24 hours) | log.cleaner.delete.retention.ms | 对于压缩的日志保留的最长时间，也是客户端消费消息的最长时间，同log.retention.minutes的区别在于一个控制未压缩数据，一个控制压缩后的数据。会被topic创建时的指定参数覆盖 |
| flush.messages | None | log.flush.interval.messages | log文件”sync”到磁盘之前累积的消息条数,因为磁盘IO操作是一个慢操作,但又是一个”数据可靠性"的必要手段,所以此参数的设置,需要在"数据可靠性"与"性能"之间做必要的权衡.如果此值过大,将会导致每次"fsync"的时间较长(IO阻塞),如果此值过小,将会导致"fsync"的次数较多,这也意味着整体的client请求有一定的延迟.物理server故障,将会导致没有fsync的消息丢失. |
| flush.ms | None | log.flush.interval.ms | 仅仅通过interval来控制消息的磁盘写入时机,是不足的.此参数用于控制"fsync"的时间间隔,如果消息量始终没有达到阀值,但是离上一次磁盘同步的时间间隔达到阀值,也将触发. |
| index.interval.bytes | 4096 | log.index.interval.bytes | 当执行一个fetch操作后，需要一定的空间来扫描最近的offset大小，设置越大，代表扫描速度越快，但是也更好内存，一般情况下不需要搭理这个参数 |
| message.max.bytes | 1,000,000 | message.max.bytes | 表示消息的最大大小，单位是字节 |
| min.cleanable.dirty.ratio | 0.5 | log.cleaner.min.cleanable.ratio | 日志清理的频率控制，越大意味着更高效的清理，同时会存在一些空间上的浪费，会被topic创建时的指定参数覆盖 |
| retention.bytes | None | retention.bytes | topic每个分区的最大文件大小，一个topic的大小限制 = 分区数*log.retention.bytes。-1没有大小限log.retention.bytes和log.retention.minutes任意一个达到要求，都会执行删除，会被topic创建时的指定参数覆盖 |
| retention.ms | None | log.retention.minutes | 数据存储的最大时间超过这个时间会根据log.cleanup.policy设置的策略处理数据，也就是消费端能够多久去消费数据, log.retention.bytes和log.retention.minutes达到要求，都会执行删除，会被topic创建时的指定参数覆盖 |
| segment.bytes | 1GB | log.segment.bytes | topic的分区是以一堆segment文件存储的，这个控制每个segment的大小，会被topic创建时的指定参数覆盖 |
| segment.index.bytes | 10MB | log.index.size.max.bytes | 对于segment日志的索引文件大小限制，会被topic创建时的指定参数覆盖 |
| log.roll.hours | 7 days | log.roll.hours | 这个参数会在日志segment没有达到log.segment.bytes设置的大小，也会强制新建一个segment会被 topic创建时的指定参数覆盖 |