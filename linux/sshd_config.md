一、修改vi /etc/ssh/sshd_config 文件
1、修改默认端口：默认Port为22,并且已经注释掉了；修改是把注释去掉，并修改成其它的端口。
2、禁止root用户远程登陆：修改PermitRootLogin，默认为yes且注释掉了；修改是把注释去掉，并改成no。
3、PermitEmptyPasswords   no不允许空密码用户login


二、ssh的公钥认证配置：
修改vi /etc/ssh/sshd_config 文件
RSAAuthentication yes        # 启用 RSA 认证（默认是注释掉的，将注释去掉，如果不是yes，改为yes）
PubkeyAuthentication yes     # 启用公钥认证（默认是注释掉的，将注释去掉，如果不是yes，改为yes）
PasswordAuthentication no    # 禁止密码认证(改为no,默认为yes是用密码认证)
StrictModes no   #修改为no,默认为yes.如果不修改用key登陆是出现server refused our key(如果StrictModes为yes必需保证存放公钥的文件夹的拥有与登陆用户名是相同的.“StrictModes”设置ssh在接收登录请求之前是否检查用户家目录和rhosts文件的权限和所有权。这通常是必要的，因为新手经常会把自己的目录和文件设成任何人都有写权限。)


之后重新启动ssh服务:/etc/init.d/ssh restart 