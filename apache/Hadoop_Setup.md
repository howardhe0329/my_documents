#Hadoop集群环境搭建

###core-site.xml

    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://master:9000</value>
    </property>
    <property>
        <name>io.file.buffer.size</name>
        <value>131072</value>
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/home/hadoop/tmp</value>
        <description>Abase forother temporary directories.</description>
    </property>
    <property>
        <name>hadoop.proxyuser.hadoop.hosts</name>
        <value>*</value>
    </property>
    <property>
        <name>hadoop.proxyuser.hadoop.groups</name>
        <value>*</value>
    </property>
    
###hdfs-site.xml

    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>master:9001</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/home/hadoop/dfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/home/hadoop/dfs/data</value>
    </property>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
    <property>
        <name>dfs.webhdfs.enabled</name>
        <value>true</value>
    </property>
    <property>
        <name>dfs.support.append</name>
        <value>true</value>
    </property>
    <property>
        <name>dfs.datanode.max.xcievers</name>
        <value>4096</value>
    </property>
    
###mapred-site.xml

    <configuration>
        <property>
            <name>mapreduce.framework.name</name>
            <value>yarn</value>
        </property>
        <property>
            <name>mapreduce.jobhistory.address</name>
            <value>master:10020</value>
        </property>
        <property>
            <name>mapreduce.jobhistory.webapp.address</name>
            <value>master:19888</value>
        </property>
    </configuration>
    
###yarn-site.xml

    <configuration>
        <property>
            <name>yarn.nodemanager.aux-services</name>
            <value>mapreduce_shuffle</value>
        </property>
        <property>                                                               
            <name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>
            <value>org.apache.hadoop.mapred.ShuffleHandler</value>
        </property>
        <property>
            <name>yarn.resourcemanager.address</name>
            <value>master:8032</value>
        </property>
        <property>
            <name>yarn.resourcemanager.scheduler.address</name>
            <value>master:8030</value>
        </property>
        <property>
            <name>yarn.resourcemanager.resource-tracker.address</name>
            <value>master:8031</value>
        </property>
        <property>
            <name>yarn.resourcemanager.admin.address</name>
            <value>master:8033</value>
        </property>
        <property>
            <name>yarn.resourcemanager.webapp.address</name>
            <value>master:8088</value>
        </property>
    </configuration>
    
###创建目录

    ~/dfs/name
    ~/dfs/data
    ~/tmp

###配置文件
1. hadoop-env.sh
    修改JAVA_HOME
2. yarn-env.sh
    修改JAVA_HOME
3. slaves
    slave1
    slave2
    
###先格式化namenode:

    ./hdfs namenode -format
    
###启动hdfs
    
    ./start-dfs.sh
    
###启动yarn

    ./start-yarn.sh
    
###验证
访问http://master:50070



