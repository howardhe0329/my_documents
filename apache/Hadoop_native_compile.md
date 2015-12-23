下截Hadoop源码包,找到对应的版本,下截地址:

    http://hadoop.apache.org/releases.html
    
下截protobuf,下载地址:(注意版本号,目前Hadoop支持2.5.0版本)

    https://developers.google.com/protocol-buffers/docs/downloads?hl=zh-cn
    
先安装protobuf需要依赖的包,如果已安装可忽略
    
    yum intall gcc-c++
    yum install gcc
    yum install make
    
安装protobuf
    
    tar zxvf protobuf-2.5.0.tar.gz
    cd protobuf-2.5.0
    ./configure --prefix=/usr/local/protobuf
    make
    make check
    make install
    
然后在安装编译hadoop需要的依赖
    
    yum install cmake
    yum install openssl-devel
    yum install ncurses-devel
    
开始编译hadoop

    tar zxvf hadoop-2.7.1-src.tar.gz
    cd hadoop-2.7.1-src
    mvn package -Pdist,native -DskipTests -Dtar
    
编译成功后,将{HADOOP_HOME}/lib/native下的文件都复制到安装版的Hadoop,/lib/native下.

    执行./hadoop checknative -a
    
