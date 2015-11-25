###hbase-env.sh

* JAVA_HOME
* HBASE_CLASSPATH
* HBASE_MANAGES_ZK
    
###设置hbase-site.xml

    <property>
        <name>hbase.rootdir</name>
        <value>hdfs://node1:9000/hbase</value>
        <description>a</description>
    </property>
    <property>
        <name>hbase.cluster.distributed</name>
        <value>true</value>
        <description>a</description>
    </property>
    <property>
        <name>hbase.zookeeper.quorum</name>
        <value>node1,node2,node3</value>
        <description>zk</description>
    </property>
    <property>
        <name>hbase.tmp.dir</name>
        <value>file:/home/hadoop/tmp</value>
    </property>
    
    
###设置regionservers

    node2
    node3
    
###打开shell

    ./hbase shell
    
###打开hbase web管理后台

    http://node1:16010
    