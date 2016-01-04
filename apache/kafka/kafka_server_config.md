#broker config
> server.properties详细配置说明:

| Property | Default | Value | 说明 |
| -------- |:-------:| -----------------:| ----:|
| broker.id |  | 0 | 每一个broker在集群中的唯一表示，要求是正数。当该服务器的IP地址发生改变时，broker.id没有变化，则不会影响consumers的消息情况 |


* broker.id = 0

    >每一个broker在集群中的唯一表示，要求是正数。当该服务器的IP地址发生改变时，broker.id没有变化，则不会影响consumers的消息情况

* log.dirs = /data/kafka-logs

    >kafka数据的存放地址，多个地址的话用逗号分割,多个目录分布在不同磁盘上可以提高读写性能  /data/kafka-logs-1，/data/kafka-logs-2
    
* port = 9092

    >broker server服务端口
    
* message.max.bytes = 6525000

    >表示消息体的最大大小，单位是字节
    
* num.network.threads = 4

    broker处理消息的最大线程数，一般情况下数量为cpu核数
    
* num.io.threads = 8

    >broker处理磁盘IO的线程数，数值为cpu核数2倍
    
* background.threads = 4

    >一些后台任务处理的线程数，例如过期消息文件的删除等，一般情况下不需要去做修改
    
* queued.max.requests =500

    >等待IO线程处理的请求队列最大数，若是等待IO的请求超过这个数值，那么会停止接受外部消息，应该是一种自我保护机制。
    
* host.name

    >broker的主机地址，若是设置了，那么会绑定到这个地址上，若是没有，会绑定到所有的接口上，并将其中之一发送到ZK，一般不设置

* log.cleanup.policy = delete

    > 日志清理策略选择有：delete和compact主要针对过期数据的处理，或是日志文件达到限制的额度，会被 topic创建时的指定参数覆盖
    
* log.retention.minutes = 300 或 log.retention.hours = 168
 
    >数据文件保留多长时间， 存储的最大时间超过这个时间会根据log.cleanup.policy设置数据清除策略
    log.retention.bytes和log.retention.minutes或log.retention.hours任意一个达到要求，都会执行删除
    有2删除数据文件方式：
        按照文件大小删除：log.retention.bytes
        按照2中不同时间粒度删除：分别为分钟，小时
      
* log.retention.bytes = -1

    > topic每个分区的最大文件大小，一个topic的大小限制 = 分区数*log.retention.bytes。-1没有大小限log.retention.bytes和log.retention.minutes任意一个达到要求，都会执行删除，会被topic创建时的指定参数覆盖
    
* log.flush.interval.messages = None 或 log.flush.interval.messages = 1000

    > 表示每当消息记录数达到1000时flush一次数据到磁盘
    log文件”sync”到磁盘之前累积的消息条数,因为磁盘IO操作是一个慢操作,但又是一个”数据可靠性"的必要手段,所以此参数的设置,需要在"数据可靠性"与"性能"之间做必要的权衡.如果此值过大,将会导致每次"fsync"的时间较长(IO阻塞),如果此值过小,将会导致"fsync"的次数较多,这也意味着整体的client请求有一定的延迟.物理server故障,将会导致没有fsync的消息丢失.
    
* log.flush.interval.ms = None 或 log.flush.interval.ms=1000

    > 表示每间隔1000毫秒flush一次数据到磁盘
    仅仅通过interval来控制消息的磁盘写入时机,是不足的.此参数用于控制"fsync"的时间间隔,如果消息量始终没有达到阀值,但是离上一次磁盘同步的时间间隔达到阀值,也将触发.
    
