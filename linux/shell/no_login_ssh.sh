#!/usr/bin/env bash
#第一个参数是 ~/.ssh/id_rsa.pub
#第二个参数是远程机器IP
#第三个参数是远程机器的user
if [ $# -lt 3 ]; then
    echo "param error"
    exit 1
fi
#echo $1
#echo $2
#echo $3
FILE=`echo $1 | awk -F '/' '{print $NF}'`
#echo $FILE
scp $1 $3@$2:~/
ssh $3@$2 "cat /home/$3/$FILE >> /home/$3/.ssh/authorized_keys"