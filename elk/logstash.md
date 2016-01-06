###logstash-nginx.conf

    input {
        file {
            path => ["/webserver/tengine/logs/access-services.log"]
            type => "nginx_254"
        }
    }
    filter{
        grok{
            match => {
    	        "message" => "\[%{NUMBER:connection}\] \[%{NUMBER:connection_requests}\] \[%{NUMBER:request_time}\] \[%{NUMBER:request_length}\] \[%{DATA:server_name}\] \[%{IP:remote_addr}\] \[%{USER:remote_user}\] \[%{HTTPDATE:time_local}\] \"%{DATA:request}\" \[%{NUMBER:status}\] \[%{NUMBER:body_bytes_sent} %{NUMBER:bytes_sent}\] \"%{DATA:http_referer}\" \"%{DATA:http_user_agent}\" \"%{IP:http_x_forwarded_for}\" \[%{DATA:msec}\] \[%{NUMBER:upstream_status}\] \[%{HOSTPORT:upstream_addr}\] \[%{NUMBER:upstream_response_time}\] \[%{DATA:request_body}\]"
            }
        }
        if [request] {
            ruby {
                init => "@kname = ['method','uri','verb']"
                code => "event.append(Hash[@kname.zip(event['request'].split(' '))])"
            }
            if [uri] {
                ruby {
                    init => "@kname = ['url_path','url_args']"
                code => "event.append(Hash[@kname.zip(event['uri'].split('?'))])"
                }
                kv {
                    prefix => "url_"
                source => "url_args"
                field_split => "& "
                #remove_field => ["url_args", "uri", "request"]
                }
            }
        }
        mutate {
        	convert => ["status", "integer", "request_time", "integer", "request_length", "integer"]
        }
    #   metrics {
    #       type => "ocr_sdp"
    #       meter => "error.%{}"
    #       add_tag => "metric"
    #       ignore_older_than => 10
    #   }
    #   ruby {
    #       tags => "metric"
    #       code => "event.cancel if event['@fields']['error.504.rate_1m']*60 < 100"
    #   }
    }
    output{
        elasticsearch {
    	    hosts => ["192.168.100.21:9200"]
    	    index => "logstash-nginx254-%{+YYYY.MM.dd}"
        }
        #stdout{
        #    codec => rubydebug
        #}
    #   exec {
    #       tags => "metric"
    #       command => "curl "
    #   }
    }

###logstash-tomcat.conf

    input {
        file {
            path => ["/tomcat/*.log"]
            type => "tomcat_21"
        }
    }
    filter{
        grok{
            patterns_dir => "/home/hadoop/server/logstash-2.0.0/patterns"
            match => {
                "message" => "%{TOMCAT_DATESTAMP:timestamp} \[%{THREADNAME:thread_name}\] %{LOGLEVEL:level}  %{JAVACLASS:class} - %{JAVALOGMESSAGE:content}"
           }
        }
    }
    output{
        stdout{
    	    codec => rubydebug
        }
    }
    
###logstash-kafka.conf

    input{
        kafka{
            zk_connect => "192.168.10.71:2181,192.168.10.76:2181,192.168.10.81:2181"
    	group_id => "logstash"
    	topic_id => "test"
    	codec => plain
    	reset_beginning => false
    	consumer_threads => 2
    	decorate_events => true
        }
    }
    output{
        elasticsearch{
        	hosts => ["192.168.10.76:9200"]
        }
        stdout{codec => rubydebug}
    }
    
###nginx log logstash-kafka-es.conf

    input {
        kafka{
            zk_connect => "192.168.168.3:2181,192.168.168.4:2181,192.168.168.5:2181"
            group_id => "logstash"
            topic_id => "nginx_services"
            codec => plain
            reset_beginning => false
            consumer_threads => 2
            decorate_events => true
        }
    }
    filter {
        json {
            source => "message"
            remove_field => ["message", "connection", "connectionRequests", "timeLocal", "requestVerb", "msec", "upstreamStatus", "sessionKey", "salt", "sign", "keyVersion"]
        }
        mutate {
    	    #rename => ["p_zhenliaoquan.sid", "p_zhenliaoquan_sid"]
    	    split => ["httpXForwardedFor", ","]
            convert => ["requestTime", "float", "requestLength", "integer", "responseStatus", "integer", "requestBodyBytes", "integer", "bodyBytes", "integer", "upstreamResponseTime", "float", "userId", "integer", "userType", "integer"]
        }
        if [httpXForwardedFor][0] != "unknown" and [httpXForwardedFor][0] != "-" {
    	    geoip {
                source => "[httpXForwardedFor][0]"
       	    }
        }
    }
    output {
       elasticsearch {
            hosts => ["192.168.168.21:9200"]
            index => "logstash-nginx-services-%{+YYYY.MM.dd}"
       }
    }
    
###logstash start

    nohup ./logstash -f ../config/nginx-kafka-in-es-ou.conf 2>&1 &
