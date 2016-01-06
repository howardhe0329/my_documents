#producer config
> 针对0.9版本, 详情见ProducerConfig类


| Property | Default | Value | 说明 |
| -------- |:-------:| :-----| :----|
| bootstrap.servers | 必填 |  | 连接kafka集群的列表, host1:port1,host2:port2 |
| buffer.memory | 32 * 1024 * 1024 |  | 缓存message records的内存大小 |
| retries | 0 |  | 重试次数 |
| acks | 1 |  | 0. 如果设置为0, 那么producer将不会等待broker的ack确认. 但是不保证能持久化(可能会丢失数据). "retries"配置会失效. offset总是设置为-1; 1. leader接收到消息并write log后, producer会收到一个ack确认, 但是有可能followers会丢失数据; all: leader将等待所有isr接收到数据后, producer才会收到一个ack确认. 这个持久性最好, 但是吞吐量相对最低.|
| compression.type | none |  | 消息压缩类型, none, gzip, lz4, snappy |
| batch.size | 16384 |  | 当多条记录被发送同一个分区时, 该producer将尝试批量发送记录以减少请求. 能够提高client和server之间的性能. 消息的大小是以字节为单位. 设置太小会降低吞吐量, 设置太大会增加IO负担. |
| request.timeout.ms | 30 * 1000 |  | producer等待请求响应的最大时间. 包括retries次数 |
| timeout.ms | 30 * 1000 |  | 0.9版本已删除, 见request.timeout.ms |
| linger.ms | 0 |  |  |
| client.id | 无 |  | 用户可自定义的client id，附加在每一条消息上来帮助跟踪 |
| send.buffer.bytes | 128 * 1024 |  | The size of the TCP send buffer (SO_SNDBUF) to use when sending data. socket发送缓冲大小. |
| receive.buffer.bytes | 32 * 1024 |  | The size of the TCP receive buffer (SO_RCVBUF) to use when reading data. |
| max.request.size | 1 * 1024 * 1024 |  | 一个请求的大小, 这个是限制producer的请求大小. 和broker设置的不是同一个含义.  |
| block.on.buffer.full | false |  | 0.9版本已删除, 见metadata.fetch.timeout.ms. 当内存缓冲用完, 将停止接收新的记录(阻塞请求)或者抛出错误. false: the producer will throw a BufferExhaustedException if a recrord is sent and the buffer space is full; true: block, 某些情况下block是不可取的.应该抛出异常. |
| metadata.fetch.timeout.ms | 60 * 1000 |  | 0.9版本已删除, 见max.block.ms 获取metadata的timeout. 当每一次发送该topic的记录时, 必须要先获取该topic的metadata, 以了解topic's partition |
| max.block.ms | 60 * 1000 |  | 控制producer的send()和partitionsFor()的block多长时间, 这些方法被block的原因有多种, 如: buffer full(缓冲区已满), metadata unavailable(元数据不可用),  |
| reconnect.backoff.ms | 50 |  | 尝试重连host的时间 |
| retry.backoff.ms | 100 |  | 重试等待时间, 重新获取metadata |
| metric.reporters |  |  | metric |
| metadata.max.age.ms | 5 * 60 * 1000 |  | 每隔多少MS后刷新metadata, 发现新的brokers或partitions. |
| metrics.sample.window.ms | 30000 |  | 计算指标样本数. |
| metrics.num.samples | 2 |  | 计算指标样本数. |
| max.in.flight.requests.per.connection | 5 |  | 每一个连接允许客户端发送未确认的请求数量. |
| key.serializer |  |  |  |
| value.serializer |  |  |  |
| connections.max.idle.ms | 9 * 60 * 1000 |  | 多长时间后关闭空闲的连接. |
| partitioner.class | DefaultPartitioner |  | 分区算法 |
| security.protocol | PLAINTEXT |  | Protocol used to communicate with brokers. Currently only PLAINTEXT and SSL are supported. |