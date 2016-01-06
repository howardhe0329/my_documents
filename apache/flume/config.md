###flume-kafka-hdfs.properties
> 从kafka中获取数据写入到hdfs中

    t1.sources = kafkaSources
    t1.channels = memoryChannel
    t1.sinks = hdfsSink
    
    #sources kafka
    t1.sources.kafkaSources.type = org.apache.flume.source.kafka.KafkaSource
    t1.sources.kafkaSources.channels = memoryChannel
    t1.sources.kafkaSources.zookeeperConnect = node1:2181,node2:2181,node3:2181
    t1.sources.kafkaSources.topic = test
    t1.sources.kafkaSources.groupId = test1
    
    #sinks hdfs
    t1.sinks.hdfsSink.type = hdfs
    t1.sinks.hdfsSink.channel = memoryChannel
    t1.sinks.hdfsSink.hdfs.path = hdfs://node1:9000/user/flume/test
    t1.sinks.hdfsSink.hdfs.filePrefix = test
    t1.sinks.hdfsSink.hdfs.round = true
    t1.sinks.hdfsSink.hdfs.roundValue = 10
    t1.sinks.hdfsSink.hdfs.roundUnit = minute
    t1.sinks.hdfsSink.hdfs.fileType = DataStream
    
    #channels memory
    t1.channels.memoryChannel.type = memory
    t1.channels.memoryChannel.capacity = 100
    
    t2.sources = kafkaSources
    t2.channels = memoryChannel2
    t2.sinks = esSink
    
    #sources kafka
    t2.sources.kafkaSources.type = org.apache.flume.source.kafka.KafkaSource
    t2.sources.kafkaSources.channels = memoryChannel2
    t2.sources.kafkaSources.zookeeperConnect = node1:2181,node2:2181,node3:2181
    t2.sources.kafkaSources.topic = test
    t2.sources.kafkaSources.groupId = test2
    
    #sinks hdfs
    t2.sinks.esSink.type = org.apache.flume.sink.elasticsearch.ElasticSearchSink
    t2.sinks.esSink.channel = memoryChannel2
    t2.sinks.esSink.indexName = testlog
    t2.sinks.esSink.indexType = log
    t2.sinks.esSink.batchSize = 100
    t2.sinks.esSink.hostNames = node2:9300
    t2.sinks.esSink.serializer = org.apache.flume.sink.elasticsearch.ElasticSearchDynamicSerializer
    
    #channels memory
    t2.channels.memoryChannel2.type = memory
    t2.channels.memoryChannel2.capacity = 100
    
###flume-tail-kafka.properties
> 将nginx中的access.log日志写入到kafka中

    agent-1.sources = s1
    agent-1.sinks = k1
    agent-1.channels = c1
    
    agent-1.channels.c1.type = SPILLABLEMEMORY
    agent-1.channels.c1.memoryCapacity = 5000
    agent-1.channels.c1.checkpointDir = /webserver/flume-1.6.0-bin/checkpoint
    agent-1.channels.c1.dataDirs = /webserver/flume-1.6.0-bin/data
    agent-1.channels.c1.overflowCapacity = 1000000
    
    agent-1.sources.s1.type = exec
    agent-1.sources.s1.command = tail -F /webserver/tengine/logs/access.log
    agent-1.sources.s1.channels = c1
    agent-1.sources.s1.fileHeader = true
    
    agent-1.sources.s1.interceptors = i
    agent-1.sources.s1.interceptors.i.type = com.xsl.data.flume.ext.interceptor.NginxLogFormatInterceptor$Builder
    agent-1.sources.s1.interceptors.i.regex = \\[(\\d+)\\] \\[(\\d+)\\] \\[(.*)\\] \\[(\\d+)\\] \\[(.*)\\] \\[(.*)\\] \\[(.*)\\] \\[(.*)\\] \"(GET|POST|PUT|DELETE|HEAD) ([\\w/-[_].]+)\\??(.*) (HTTP/[01].[01])\" \\[(\\d+)\\] \\[(\\d+) (\\d+)\\] \"(.*)\" \"(.*)\" \"(.*)\" \\[(.*)\\] \\[(.*)\\] \\[(.*)\\] \\[(.*)\\] \\[(.*)\\] \\[(.*)\\]
    
    agent-1.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
    agent-1.sinks.k1.topic = nginx_services
    agent-1.sinks.k1.brokerList = 192.168.168.6:9092,192.168.168.7:9092,192.168.168.8:9092
    agent-1.sinks.k1.requiredAcks = 1
    agent-1.sinks.k1.batchSize = 20
    agent-1.sinks.k1.channel = c1
    
