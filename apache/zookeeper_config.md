###zoo.cfg

    tickTime=2000
    initLimit=10
    syncLimit=5
    dataDir=/home/hadoop/zoo/data
    clientPort=2181
    server.1=master:2888:3888
    server.2=slave1:2888:3888
    server.3=slave1:2888:3888

###创建zookeeper data目录

    mkdir /home/hadoop/zoo/data
    
在data目录下创建myid文件，文件内容为1。和server.id的id对应。
    
###启动zk

    ./zkServer.sh start
    
###查看状态

    ./zkServer.sh status

###停止zk
    
    ./zkServer.sh stop
    
###zk命令行操作

    ./zkCli.sh -server ip:port
    
##zoo.cfg 详解

* clientPort
    监听客户端连接的端口
* dataDir
    存储在内存中数据库快照的位置
    注意 应该谨慎地选择日志存放的位置，使用专用的日志存储设备能够大大地提高系统的性能，如果将日志存储在比较繁忙的存储设备上，那么将会在很大程度上影响系统的性能。
* tickTime
    基本事件单元，以毫秒为单位。它用来控制心跳和超时，默认情况下最小的会话超时时间为两倍的 tickTime 
* dataLogDir
    将管理机器把事务日志写入到“ dataLogDir ”所指定的目录
* maxClientCnxns
    这个操作将限制连接到 ZooKeeper 的客户端的数量，限制并发连接的数量，它通过 IP 来区分不同的客户端。此配置选项可以用来阻止某些类别的 Dos 攻击。将它设置为 0 或者忽略而不进行设置将会取消对并发连接的限制
* minSessionTimeout 和 maxSessionTimeout
    最小的会话超时时间以及最大的会话超时时间。其中，最小的会话超时时间默认情况下为 2 倍的 tickTme 时间，最大的会话超时时间默认情况下为 20 倍的会话超时时间。在启动时，系统会显示相应信息，见下图 2 所示，默认会话超时时间
* initLimit
    此配置表示，允许 follower （相对于 leader 而言的“客户端”）连接并同步到 leader 的初始化连接时间，它以 tickTime 的倍数来表示。当超过设置倍数的 tickTime 时间，则连接失败。
* syncLimit
    此配置表示， leader 与 follower 之间发送消息，请求和应答时间长度。如果 follower 在设置的时间内不能与leader 进行通信，那么此 follower 将被丢弃.
