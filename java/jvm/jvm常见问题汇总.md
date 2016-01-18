###jstat pid 提示 pid not found
> java进程启动时会生成pid文件, 保存在/tmp/hsperfdata_{USER}/路径下,生成一个{PID}文件.
如果出现jps, jstat, jinfo等命令不能用时, 可以查看/tmp/hsperfdata_{USER}下是否有pid文件, 如果不存在,可能是因为
当前{USER}用户对/tmp/hsperfdata_{USER}没有w权限.

### 查看JAVA哪个线程占用CPU高

