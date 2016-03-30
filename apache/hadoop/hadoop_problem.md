#常见问题汇总

##org.apache.hadoop.hdfs.server.namenode.LeaseExpiredException异常解决
org.apache.hadoop.ipc.RemoteException(org.apache.hadoop.hdfs.server.namenode.LeaseExpiredException): No lease on /user/suoper/services_data/result/_metadata (inode 258725): File does not exist. Holder DFSClient_NONMAPREDUCE_-1546726864_91 does not have any open files.

* 修改hadoop配置, dfs.datanode.max.xcievers设置为8192

    vi hdfs-site.xml
    <property>  
      <name>dfs.datanode.max.xcievers</name>  
      <value>8192</value>  
    </property>
    
* 修改linux打开文件最大限制

    echo "fs.file-max = 65535" >> /etc/sysctl.conf   
    echo "* - nofile 65535" >> /etc/security/limits.conf  
    sysctl -p
    ulimit -n
    
##org.apache.hadoop.hdfs.server.datanode.DataNode: prod-bigdata-hadoop-8:50010:DataXceiver error processing WRITE_BLOCK operation  src: /192.168.99.26:45261 dst: /192.168.99.8:50010

    2016-03-14 14:24:43,271 ERROR org.apache.hadoop.hdfs.server.datanode.DataNode: prod-bigdata-hadoop-8:50010:DataXceiver error processing WRITE_BLOCK operation  src: /192.168.99.26:45261 dst: /192.168.99.8:50010
    java.io.IOException: Premature EOF from inputStream
    	at org.apache.hadoop.io.IOUtils.readFully(IOUtils.java:194)
    	at org.apache.hadoop.hdfs.protocol.datatransfer.PacketReceiver.doReadFully(PacketReceiver.java:213)
    	at org.apache.hadoop.hdfs.protocol.datatransfer.PacketReceiver.doRead(PacketReceiver.java:134)
    	at org.apache.hadoop.hdfs.protocol.datatransfer.PacketReceiver.receiveNextPacket(PacketReceiver.java:109)
    	at org.apache.hadoop.hdfs.server.datanode.BlockReceiver.receivePacket(BlockReceiver.java:496)
    	at org.apache.hadoop.hdfs.server.datanode.BlockReceiver.receiveBlock(BlockReceiver.java:889)
    	at org.apache.hadoop.hdfs.server.datanode.DataXceiver.writeBlock(DataXceiver.java:757)
    	at org.apache.hadoop.hdfs.protocol.datatransfer.Receiver.opWriteBlock(Receiver.java:137)
    	at org.apache.hadoop.hdfs.protocol.datatransfer.Receiver.processOp(Receiver.java:74)
    	at org.apache.hadoop.hdfs.server.datanode.DataXceiver.run(DataXceiver.java:239)
    	at java.lang.Thread.run(Thread.java:745)
    	
原因: 文件操作超租期，实际上就是data stream操作过程中文件被删掉了。
解决办法: 修改hdfs-site.xml文件(针对2.x版本)

    <property>
        <name>dfs.datanode.max.transfer.threads</name>
        <value>8192</value>
    </property>

然后重启DataNode
    
