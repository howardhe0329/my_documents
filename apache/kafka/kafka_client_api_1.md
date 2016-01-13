#KafkaProducer
> KafkaProducer类是线程安全, 通常多线程访问单一KafkaProducer实例要比多个KafkaProducer实例要好.

生产者由缓存池组成,该缓冲池持有未发送到服务端的消息记录, 以及一个后台I/O线程. 该后台I/O负责将这些记录
放入到请求中, 并将它们发送到集群中.
    send()方法是异步请求. 当调用send方法时, 它将记录添加到未发送记录的缓存区中并立即返回. 允许发送者批量
发送记录以提高效率.
    通过配置acks参数可以控制向服务端发送记录的请求并认为是完整的. 可以设置all, 0, 1这三个值. 默认是1

* all: 是将请求发送到集群中的leader和followers都确认成功后才返回, 该配置能够保证数据完整性, 但是吞吐量降低; 
* 1: 只要leader确认成功就可以返回.
* 0: 不需leader确认,并立即返回.

    通过配置retries参数可以设置重试次数, 默认是0. 当请求失败时可以通过该配置设置重试次数. 如果设置可能造成会重复
发送记录.

    生产者维护每个partition的缓冲区, 这些缓冲区可以通过batch.size配置设置指定的大小.
    默认缓存区是立即发送的,甚至是可添加的未使用空间的缓冲区. 假如你想减少请求的数量,可以通过配置linger.ms参数设置(默认为0)
大于0值. 如果设置为1, 该设置指定发送者需要等待1毫秒发送一个请求. 希望更多的消息记录写入到同一个请求中. 这类似于TCP的Nagle的算法.

    配置buffer.memory参数可以控制发送者缓存区的可用内存的总量. 如果记录发送到缓冲区快于将记录发送到服务端时,会将缓存区空间耗尽.
当缓存区空间耗尽那么发送到缓冲区的请求将被阻塞. 如果想避免阻塞, 可以设置block.on.buffer.full=false. 该配置将调用发送时会抛出异常.

##KafkaProducer创建实例的流程详解

1. 获取用户设置的配置信息.
2. 创建MetricConfig类, 设置监控的配置信息.
3. 设置clientId
4. 创建MetricReporter类, 是用来发送监控信息.
5. 创建Metrics类, 监控各项数据.
6. 创建Partitioner类, 默认是DefaultPartitioner实现类. 功能是将记录分配到各partition中.
7. 创建Metadata类, 获取一些元数据信息, 比如topics, 每个topic下的partitions以及nodes等等.
8. 创建RecordAccumulator类, 功能是将每条记录发送到缓冲区中.
9. 创建NetworkClient类, 主要是建立socket连接,发送等操作. 采用nio
10. 创建Sender类, 该类负责发送数据到服务端.
11. 创建KafkaThread类, 该类是一个后台I/O线程, 主要是用发送记录到服务端.
12. 注册MBean


   private KafkaProducer(ProducerConfig config, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        try {
            log.trace("Starting the Kafka producer");
            //获取用户设置的配置信息.
            Map<String, Object> userProvidedConfigs = config.originals();
            this.producerConfig = config;
            //创建系统当前时间,时间戳
            this.time = new SystemTime();
            //创建Metric配置,包括时间窗口, 事件窗口, Quota(指标), samples.  默认(timeWindowMs = 30000, samples = 2) 
            MetricConfig metricConfig = new MetricConfig().samples(config.getInt(ProducerConfig.METRICS_NUM_SAMPLES_CONFIG))
                    .timeWindow(config.getLong(ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG),
                            TimeUnit.MILLISECONDS);
            //从config 获取clientId, 如果没有配置,则是 producer-1...                
            clientId = config.getString(ProducerConfig.CLIENT_ID_CONFIG);
            if (clientId.length() <= 0)
                clientId = "producer-" + PRODUCER_CLIENT_ID_SEQUENCE.getAndIncrement();
            //创建MetricsReporter list
            List<MetricsReporter> reporters = config.getConfiguredInstances(ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG,
                    MetricsReporter.class);
            //创建JmxReporter,并放入list中
            reporters.add(new JmxReporter(JMX_PREFIX));
            //创建Metrics
            this.metrics = new Metrics(metricConfig, reporters, time);
            //创建Partioner实现类, 主要生成partiton的策略算法
            this.partitioner = config.getConfiguredInstance(ProducerConfig.PARTITIONER_CLASS_CONFIG, Partitioner.class);
            //获取topic的partition信息(也是获取metadata数据)的重试间隔时间
            long retryBackoffMs = config.getLong(ProducerConfig.RETRY_BACKOFF_MS_CONFIG);
            //创建Metadata 参数是 retryBackoffMs = 100ms, metadataExpireMs = 5min. 
            this.metadata = new Metadata(retryBackoffMs, config.getLong(ProducerConfig.METADATA_MAX_AGE_CONFIG));
            //设置每次请求的字节大小 默认是1m 1*1024*1024
            this.maxRequestSize = config.getInt(ProducerConfig.MAX_REQUEST_SIZE_CONFIG);
            // 设置内存缓冲字节大小 默认是 32m  32*1024*1024
            this.totalMemorySize = config.getLong(ProducerConfig.BUFFER_MEMORY_CONFIG);
            // 设置压缩类型
            this.compressionType = CompressionType.forName(config.getString(ProducerConfig.COMPRESSION_TYPE_CONFIG));
            /* check for user defined settings.
             * If the BLOCK_ON_BUFFER_FULL is set to true,we do not honor METADATA_FETCH_TIMEOUT_CONFIG.
             * This should be removed with release 0.9 when the deprecated configs are removed.
             */
             // 检查用户设置的配置, BLOCK_ON_BUFFER_FULL 點认为false , 0.9版本已删除.
            if (userProvidedConfigs.containsKey(ProducerConfig.BLOCK_ON_BUFFER_FULL_CONFIG)) {
                log.warn(ProducerConfig.BLOCK_ON_BUFFER_FULL_CONFIG + " config is deprecated and will be removed soon. " +
                        "Please use " + ProducerConfig.MAX_BLOCK_MS_CONFIG);
                boolean blockOnBufferFull = config.getBoolean(ProducerConfig.BLOCK_ON_BUFFER_FULL_CONFIG);
                if (blockOnBufferFull) {
                    this.maxBlockTimeMs = Long.MAX_VALUE;
                } else if (userProvidedConfigs.containsKey(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG)) {
                    log.warn(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG + " config is deprecated and will be removed soon. " +
                            "Please use " + ProducerConfig.MAX_BLOCK_MS_CONFIG);
                    this.maxBlockTimeMs = config.getLong(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG);
                } else {
                    this.maxBlockTimeMs = config.getLong(ProducerConfig.MAX_BLOCK_MS_CONFIG);
                }
                // 用户是否设置METADATA_FETCH_TIMEOUT_CONFIG , 0.9已删除
            } else if (userProvidedConfigs.containsKey(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG)) {
                log.warn(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG + " config is deprecated and will be removed soon. " +
                        "Please use " + ProducerConfig.MAX_BLOCK_MS_CONFIG);
                this.maxBlockTimeMs = config.getLong(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG);
            } else {
                //配置 当send(), partitionFor()方法或者获取metadata数据的超时时间
                this.maxBlockTimeMs = config.getLong(ProducerConfig.MAX_BLOCK_MS_CONFIG);
            }

            /* check for user defined settings.
             * If the TIME_OUT config is set use that for request timeout.
             * This should be removed with release 0.9
             */
             // 用户是否配置TIMEOUT_CONFIG ,0.9已删除
            if (userProvidedConfigs.containsKey(ProducerConfig.TIMEOUT_CONFIG)) {
                log.warn(ProducerConfig.TIMEOUT_CONFIG + " config is deprecated and will be removed soon. Please use " +
                        ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG);
                this.requestTimeoutMs = config.getInt(ProducerConfig.TIMEOUT_CONFIG);
            } else {
                //请求超时时间 默认30s
                this.requestTimeoutMs = config.getInt(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG);
            }
            //设置metric的tags列表
            Map<String, String> metricTags = new LinkedHashMap<String, String>();
            // 把client-id 写入到metric的tag里.
            metricTags.put("client-id", clientId);
            // 创建记录累加器, 做批量发送数据用. 通过BATCH_SIZE_CONFIG 设置字节大小. 默认是16384,也就是16kb. 
            // totalMemorySize 缓冲内存大小 default 32m; compressionType 压缩类型 default none;
            // LINGER_MS_CONFIG default 0
            // retryBackoffMs 重试时间; metrics; time; metricTags.
            this.accumulator = new RecordAccumulator(config.getInt(ProducerConfig.BATCH_SIZE_CONFIG),
                    this.totalMemorySize,
                    this.compressionType,
                    config.getLong(ProducerConfig.LINGER_MS_CONFIG),
                    retryBackoffMs,
                    metrics,
                    time,
                    metricTags);
            //创建 broker address list        
            List<InetSocketAddress> addresses = ClientUtils.parseAndValidateAddresses(config.getList(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
            // 更新metadata并加载cluster的列表, 从broker中获取metadata信息
            this.metadata.update(Cluster.bootstrap(addresses), time.milliseconds());
            // 创建ChannelBuilder, 主要是ssl 相关的
            ChannelBuilder channelBuilder = ClientUtils.createChannelBuilder(config.values());
            // 创建network client 参数 reconnectBackoffMs default 50ms; socketSendBuffer default 128k; socketReceiveBuffer 32k; requestTimeoutMs default 30s;
            // maxInFlightRequestsPerConnection 5; 
            NetworkClient client = new NetworkClient(
                    new Selector(config.getLong(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG), this.metrics, time, "producer", metricTags, channelBuilder),
                    this.metadata,
                    clientId,
                    config.getInt(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION),
                    config.getLong(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG),
                    config.getInt(ProducerConfig.SEND_BUFFER_CONFIG),
                    config.getInt(ProducerConfig.RECEIVE_BUFFER_CONFIG),
                    this.requestTimeoutMs, time);
            // 创建Sender implements Runnable 负责发送数据 
            //参数 maxRequestSize default 1m; acks default 1; retries default 0; requestTimeout default 30s;
            this.sender = new Sender(client,
                    this.metadata,
                    this.accumulator,
                    config.getInt(ProducerConfig.MAX_REQUEST_SIZE_CONFIG),
                    (short) parseAcks(config.getString(ProducerConfig.ACKS_CONFIG)),
                    config.getInt(ProducerConfig.RETRIES_CONFIG),
                    this.metrics,
                    new SystemTime(),
                    clientId,
                    this.requestTimeoutMs);
            String ioThreadName = "kafka-producer-network-thread" + (clientId.length() > 0 ? " | " + clientId : "");
            //创建IO Thread 后台线程 
            this.ioThread = new KafkaThread(ioThreadName, this.sender, true);
            this.ioThread.start();
            //统计错误
            this.errors = this.metrics.sensor("errors");
            
            if (keySerializer == null) {
                this.keySerializer = config.getConfiguredInstance(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                        Serializer.class);
                this.keySerializer.configure(config.originals(), true);
            } else {
                config.ignore(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG);
                this.keySerializer = keySerializer;
            }
            if (valueSerializer == null) {
                this.valueSerializer = config.getConfiguredInstance(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                        Serializer.class);
                this.valueSerializer.configure(config.originals(), false);
            } else {
                config.ignore(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG);
                this.valueSerializer = valueSerializer;
            }
            config.logUnused();
            //MBean
            AppInfoParser.registerAppInfo(JMX_PREFIX, clientId);
            log.debug("Kafka producer started");
        } catch (Throwable t) {
            // call close methods if internal objects are already constructed
            // this is to prevent resource leak. see KAFKA-2121
            close(0, TimeUnit.MILLISECONDS, true);
            // now propagate the exception
            throw new KafkaException("Failed to construct kafka producer", t);
        }
    }