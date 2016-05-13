#设置ulimit

###显示当前的各种用户进程限制
    
    ulimit -a
    
###设置某用户的最大进程数

    ulimit -u 10000
    
###设置每个进程的可打开的文件数(默认是1024)

    ulimit -n 4096
    
###设置数据段长度
    
    ulimit -d unlimited
    
###设置最大内存大小

    ulimit -d unlimited

###设置堆栈大小

    ulimit -d unlimited

###设置cpu时间

    ulimit -d unlimited

###虚拟内存

    ulimit -d unlimited
    
###解除系统的最大进程数和最大文件打开数限制

    vi /etc/security/limits.conf
    
    #添加下面的行
    * soft nproc 11000
    * hard nproc 11000
    * soft nofile 4100
    * hard nofile 4100
    
说明：* 代表针对所有用户 noproc 代表最大进程数 nofile 代表最大文件打开数
    
###ubuntu系统设置ulimit(针对ubuntu系统)

    echo "session required  pam_limits.so" >> /etc/pam.d/common_session
    
不修改/etc/pam.d/common_session不公生效

