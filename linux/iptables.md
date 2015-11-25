###查看iptables列表

    iptables -L -n
    
###重启iptables

    service iptables restart
    
###停止iptables

    service iptables stop
    
###启动iptables

    service iptables start
    
###修改iptables配置

    vi /etc/sysconfig/iptables
    
###开启端口

    iptables -A INPUT -p tcp --dport 22 -j ACCEPT