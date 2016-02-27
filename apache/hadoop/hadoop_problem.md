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