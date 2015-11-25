#ssh无密码登录
生成公钥和私钥
    
    ssh-keygen -t rsa -P ''

然后在~/.ssh/下面生成id_rsa和id_rsa.pub两个文件,然后把id_rsa.puh文件复制到别一台机器上

    scp ~/.ssh/id_rsa.pub xxx@xxx.xx.xx.xxx:/usr/locat/tmp/id_rsa.pub
    
登录到被复制过来的机器，

    cat /usr/local/tmp/id_rsa.pub >> ~/.ssh/authorized_keys

查看authorized_keys 是否有rw权限,如没有:

    chmod 600 ~/.ssh/authorized_keys
    
验证是否登录成功

    ssh xxx.xxx.xxx.xxx
    
