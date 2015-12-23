###修改elasticsearch.yml配置文件
路径：
    ~/server/elasticsearch-2.0.0/config/elasticsearch.yml
    
修改内容：

    network.host: 192.168.100.21 #修改为本机的地址
    
启动服务：

    cd ~/server/elasticsearch-2.0.0/bin
    ./elasticsearch start &
    
是否启动成功可以用jps命令查看是否有有asticsearch进程
    
###修改kibana.yml配置文件
路径：    
    
    ~/server/kibana-4.2.0-linux-x64/config
    
修改内容：

    
    server.host: "192.168.100.21"   #修改为本机的地址
    
    elasticsearch.url: "http://192.168.100.21:9200" #修改elasticsearch的url
    
启动服务：

    cd  ~/server/kibana-4.2.0-linux-x64/bin
    ./kibana &
    
###logstash
路径：

    ~/server/logstash-2.0.0/config
    
修改nginx-in-es-ou.conf
    
    elasticsearch {
        hosts => ["192.168.100.21:9200"]  #修改elasticsearch的地址
        index => "logstash-nginx254-%{+YYYY.MM.dd}" #定久index的名字，必须要包含"%{+YYYY.MM.dd}" 每天会生成一个索引文件。方便以后可以删除历史索引文件
    }
   
    根据nginx.conf的log_format内容来修改:
    
    filter {
        grok {
            match => {
                "message" => "\[%{NUMBER:connection}\] \[%{NUMBER:connection_requests}\] \[%{NUMBER:request_time}\] \[%{NUMBER:request_length}\] \[%{DATA:server_name}\] \[%{IP:remote_addr}\] \[%{USER:remote_user}\] \[%{HTTPDATE:time_local}\] \"%{DATA:request}\" \[%{NUMBER:status}\] \[%{NUMBER:body_bytes_sent} %{NUMBER:bytes_sent}\] \"%{DATA:http_referer}\" \"%{DATA:http_user_agent}\" \"%{IP:http_x_forwarded_for}\" \[%{DATA:msec}\] \[%{NUMBER:upstream_status}\] \[%{HOSTPORT:upstream_addr}\] \[%{NUMBER:upstream_response_time}\] \[%{DATA:request_body}\]"
            }
        }
    }
    
启动logstash服务：

    ./logstash -f ../config/nginx-in-es-ou.conf &
    
是否启动成功可以用jps命令查看是否有Main进程