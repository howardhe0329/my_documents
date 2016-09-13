# docker

    sudo docker ps -a -q --filter "status=exited" | xargs sudo docker rm
    sudo docker rmi `sudo docker images -q --filter "dangling=true"`
    
## 清理终止状态容器

    docker rm $(docker ps -a -q)
    
## docker容器的PID

    docker-pid="sudo docker inspect --format '{{.State.Pid}}'"

## docker容器的ip

    docker-ip="sudo docker inspect --format '{{ .NetworkSettings.IPAddress }}'"

## 进入docker容器

    docker exec -it <container name> /bin/bash

## 日期同步

    docker run -dit --name biz-monitor -v /usr/share/zoneinfo/Asia/Shanghai:/etc/localtime xsl-monitor/biz-monitor
    (注意: 需要设置file sharing)
    或者
    docker cp /usr/share/zoneinfo/Asia/Shanghai <container id>:/etc/localtime

    docker cp /etc/localtime <container id>:/etc/localtime