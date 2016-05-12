#lsof命令
> lsof是一个列出当前系统打开文件的工具。通过文件可以访问常规数据，还可以访问网络连接硬件。如传输控制协议 (TCP) 和用户数据报协议 (UDP) 套接字等。通过lsof工具能够查看这个列表对系统监测以及排错将是很有帮助的。

##命令格式
lsof [参数][文件]
##命令功能
用于查看你进程开打的文件，打开文件的进程，进程打开的端口(TCP、UDP)。找回/恢复删除的文件。是十分方便的系统监视工具，因为 lsof 需要访问核心内存和各种文件，所以需要root用户执行。

##文件类型
1. 普通文件
2. 目录
3. 网络文件系统的文件
4. 字符或设备文件
5. (函数)共享库
6. 管道，命名管道
7. 符号链接
8. 网络文件（例如：NFS file、网络socket，unix域名socket）
9. 还有其它类型的文件，等等

##命令参数
* -a 列出打开文件存在的进程
* -c <进程名> 列出指定进程所打开的文件
* -g 列出GID号进程详情
* -d <文件号> 列出占用该文件号的进程
* +d <目录>  列出目录下被打开的文件
* +D <目录>  递归列出目录下被打开的文件
* -n <目录>  列出使用NFS的文件
* -i <条件>  列出符合条件的进程。（4、6、协议、:端口、 @ip ）
* -p <进程号> 列出指定进程号所打开的文件
* -u 列出UID号进程详情
* -h 显示帮助信息
* -v 显示版本信息

##命令输出结果
命令：lsof -i tcp

```
[suoper@100-144-tomcat ~]$ lsof -i tcp
COMMAND   PID   USER   FD   TYPE   DEVICE SIZE/OFF NODE NAME
java     9002 suoper   46u  IPv6 12963944      0t0  TCP *:18090 (LISTEN)
java     9002 suoper   51u  IPv6 12963948      0t0  TCP *:8099 (LISTEN)
java     9002 suoper   56u  IPv6 12971787      0t0  TCP 100-144-tomcat.qa.shennong.ren:54751->192.168.101.5:XmlIpcRegSvc (SYN_SENT)
java     9002 suoper   58u  IPv6 12970936      0t0  TCP 100-144-tomcat.qa.shennong.ren:18090->192.168.100.1:55914 (ESTABLISHED)
java     9002 suoper   75u  IPv6 12964027      0t0  TCP localhost:8095 (LISTEN)
java     9002 suoper   79u  IPv6 12969612      0t0  TCP 100-144-tomcat.qa.shennong.ren:54750->192.168.101.5:XmlIpcRegSvc (ESTABLISHED)
java     9002 suoper  158u  IPv6 12963962      0t0  TCP 100-144-tomcat.qa.shennong.ren:60023->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  161u  IPv6 12963980      0t0  TCP 100-144-tomcat.qa.shennong.ren:47841->192.168.100.170:amqp (ESTABLISHED)
java     9002 suoper  162u  IPv6 12963966      0t0  TCP 100-144-tomcat.qa.shennong.ren:60025->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  163u  IPv6 12963967      0t0  TCP 100-144-tomcat.qa.shennong.ren:60026->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  164u  IPv6 12963968      0t0  TCP 100-144-tomcat.qa.shennong.ren:60027->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  165u  IPv6 12963969      0t0  TCP 100-144-tomcat.qa.shennong.ren:60028->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  166u  IPv6 12963970      0t0  TCP 100-144-tomcat.qa.shennong.ren:60029->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  167u  IPv6 12963971      0t0  TCP 100-144-tomcat.qa.shennong.ren:60030->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  168u  IPv6 12963972      0t0  TCP 100-144-tomcat.qa.shennong.ren:60031->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  169u  IPv6 12963973      0t0  TCP 100-144-tomcat.qa.shennong.ren:60032->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  170u  IPv6 12963974      0t0  TCP 100-144-tomcat.qa.shennong.ren:60033->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  171u  IPv6 12963981      0t0  TCP 100-144-tomcat.qa.shennong.ren:54734->192.168.101.5:XmlIpcRegSvc (ESTABLISHED)
java     9002 suoper  181u  IPv6 12964013      0t0  TCP 100-144-tomcat.qa.shennong.ren:47855->192.168.100.170:amqp (ESTABLISHED)
java     9002 suoper  182u  IPv6 12963998      0t0  TCP 100-144-tomcat.qa.shennong.ren:60038->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  183u  IPv6 12963999      0t0  TCP 100-144-tomcat.qa.shennong.ren:60039->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  184u  IPv6 12964000      0t0  TCP 100-144-tomcat.qa.shennong.ren:60040->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  185u  IPv6 12964001      0t0  TCP 100-144-tomcat.qa.shennong.ren:60041->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  186u  IPv6 12964002      0t0  TCP 100-144-tomcat.qa.shennong.ren:60042->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  187u  IPv6 12964003      0t0  TCP 100-144-tomcat.qa.shennong.ren:60043->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  188u  IPv6 12964004      0t0  TCP 100-144-tomcat.qa.shennong.ren:60044->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  189u  IPv6 12964005      0t0  TCP 100-144-tomcat.qa.shennong.ren:60045->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  190u  IPv6 12964006      0t0  TCP 100-144-tomcat.qa.shennong.ren:60046->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  191u  IPv6 12964007      0t0  TCP 100-144-tomcat.qa.shennong.ren:60047->pub.OcrDB:33306 (ESTABLISHED)
java     9002 suoper  192u  IPv6 12964014      0t0  TCP 100-144-tomcat.qa.shennong.ren:54748->192.168.101.5:XmlIpcRegSvc (ESTABLISHED)
java    10074 suoper   46u  IPv6 12848531      0t0  TCP *:18060 (LISTEN)
java    10074 suoper   51u  IPv6 12848535      0t0  TCP *:8016 (LISTEN)
java    10074 suoper  153u  IPv6 12848548      0t0  TCP 100-144-tomcat.qa.shennong.ren:56254->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  154u  IPv6 12848549      0t0  TCP 100-144-tomcat.qa.shennong.ren:56255->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  155u  IPv6 12848550      0t0  TCP 100-144-tomcat.qa.shennong.ren:56256->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  156u  IPv6 12848551      0t0  TCP 100-144-tomcat.qa.shennong.ren:56257->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  157u  IPv6 12848552      0t0  TCP 100-144-tomcat.qa.shennong.ren:56258->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  158u  IPv6 12848553      0t0  TCP 100-144-tomcat.qa.shennong.ren:56259->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  159u  IPv6 12848554      0t0  TCP 100-144-tomcat.qa.shennong.ren:56260->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  160u  IPv6 12848555      0t0  TCP 100-144-tomcat.qa.shennong.ren:56261->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  161u  IPv6 12848556      0t0  TCP 100-144-tomcat.qa.shennong.ren:56262->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  162u  IPv6 12848557      0t0  TCP 100-144-tomcat.qa.shennong.ren:56263->pub.OcrDB:33306 (ESTABLISHED)
java    10074 suoper  163u  IPv6 12848560      0t0  TCP 100-144-tomcat.qa.shennong.ren:44071->192.168.100.170:amqp (ESTABLISHED)
java    10074 suoper  181u  IPv6 12848575      0t0  TCP localhost:8065 (LISTEN)
java    32513 suoper   41u  IPv6  1154082      0t0  TCP *:18101 (LISTEN)
java    32513 suoper   59u  IPv6  1154125      0t0  TCP *:28100 (LISTEN)
java    32513 suoper   60u  IPv6  1154129      0t0  TCP localhost:ldoms-migr (LISTEN)
java    32513 suoper   67u  IPv6  5056748      0t0  TCP 100-144-tomcat.qa.shennong.ren:54175->pub.OcrDB:33306 (ESTABLISHED)
```
### 结果说明：
* COMMAND:	进程的名称
* PID:	 进程标识符
* PPID:	父进程标识符
* USER:	进程所有者
* PGID:	进程所属组
* FD:	文件描述符，应用程序通过文件描述符识别该文件。如cwd、txt等
	* cwd:	表示current work dirctory，即：应用程序的当前工作目录，这是该应用程序启动的目录，除非它本身对这个目录进行更改
	* txt:	该类型的文件是程序代码，如应用程序二进制文件本身或共享库
	* lnn:	library references (AIX)
	* er:	FD information error (see NAME column)
	* jld: jail directory (FreeBSD)
	* ltx:	shared library text (code and data);
	* mxx:	hex memory-mapped type number xx
	* m86:	DOS Merge mapped file
	* mem:	memory-mapped file
	* mmap:	memory-mapped device
	* pd:	parent directory
	* rtd:	root directory
	* tr:	kernel trace file (OpenBSD)
	* v86:	VP/ix mapped file
	* 0:	表示标准输出
	* 1:	表示标准输入
	* 2:	表示标准错误
	* 一般在标准输出、标准错误、标准输入后还跟着文件状态模式：r、w、u等
	* u:	表示该文件被打开并处于读取/写入模式
	* r:	表示该文件被打开并处于只读模式
	* w:	表示该文件被打开并处于写入模式
	* 空格:	表示该文件的状态模式为unknow，且没有锁定
	* -:	表示该文件的状态模式为unknow，且被锁定
	* 同时在文件状态模式后面，还跟着相关的锁
	* N:	for a Solaris NFS lock of unknown type
	* r:	for read lock on part of the file
	* R:	for a read lock on the entire file
	* w:	for a write lock on part of the file;（文件的部分写锁）
	* W:	for a write lock on the entire file;（整个文件的写锁）
	* u:	for a read and write lock of any length
	* U:	for a lock of unknown type
	* x:	for an SCO OpenServer Xenix lock on part of the file
	* X:	for an SCO OpenServer Xenix lock on the entire file
	* space:	if there is no lock
* TYPE：文件类型，如DIR、REG等，常见的文件类型
	* DIR:	表示目录
	* CHR:	表示字符类型
	* BLK:	块设备类型
	* UNIX:	UNIX 域套接字
	* FIFO:	先进先出 (FIFO) 队列
	* IPv4:	网际协议 (IP) 套接字
* DEVICE:	指定磁盘的名称
* SIZE:	文件的大小
* NODE:	索引节点（文件在磁盘上的标识）
* NAME:	打开文件的确切名称

##示例
1. 查看哪些进程正在使用某个文件

```
[suoper@100-144-tomcat ~]$ lsof /logs/ocr_web/app.log
COMMAND  PID   USER   FD   TYPE DEVICE SIZE/OFF   NODE NAME
java    9002 suoper  152w   REG    8,1  2280384 262575 /logs/ocr_web/app.log
java    9002 suoper  176w   REG    8,1  2280384 262575 /logs/ocr_web/app.log
```
查看这个日志文件被进程标识为9002的JAVA进程所使用

2. 查看某个程序进程打开的文件信息

```
[suoper@100-144-tomcat ~]$ lsof -c java
COMMAND   PID   USER   FD   TYPE             DEVICE  SIZE/OFF     NODE NAME
java     9002 suoper  cwd    DIR                8,1      4096   658913 /webserver/tomcat/ocr-web/bin
java     9002 suoper  rtd    DIR                8,1      4096        2 /
java     9002 suoper  txt    REG                8,1      7734     7034 /usr/local/jdk1.8.0_60/bin/java
java     9002 suoper  mem    REG                8,1     37261  1185505 /usr/local/jdk1.8.0_60/jre/lib/amd64/libawt_headless.so
java     9002 suoper  mem    REG                8,1    772804  1185528 /usr/local/jdk1.8.0_60/jre/lib/amd64/libawt.so
java     9002 suoper  mem    REG                8,1  99158576  1050588 /usr/lib/locale/locale-archive
java     9002 suoper  mem    REG                8,1   1178935  1185596 /usr/local/jdk1.8.0_60/jre/lib/ext/localedata.jar
java     9002 suoper  mem    REG                8,1   3860522  1185593 /usr/local/jdk1.8.0_60/jre/lib/ext/cldrdata.jar
java     9002 suoper  mem    REG                8,1    110960   786510 /lib64/libresolv-2.12.so
java     9002 suoper  mem    REG                8,1     27424   786458 /lib64/libnss_dns-2.12.so
java     9002 suoper  mem    REG                8,1   3133473  1185560 /usr/local/jdk1.8.0_60/jre/lib/charsets.jar
java     9002 suoper  mem    REG                8,1   3501583  1185587 /usr/local/jdk1.8.0_60/jre/lib/resources.jar
java     9002 suoper  mem    REG                8,1     90880   786576 /lib64/libgcc_s-4.4.7-20120601.so.1
java     9002 suoper  mem    REG                8,1    256702  1185536 /usr/local/jdk1.8.0_60/jre/lib/amd64/libsunec.so
java     9002 suoper  mem    REG                8,1     93038  1185496 /usr/local/jdk1.8.0_60/jre/lib/amd64/libnio.so
java     9002 suoper  mem    REG                8,1    115986  1185487 /usr/local/jdk1.8.0_60/jre/lib/amd64/libnet.so
java     9002 suoper  mem    REG                8,1     50145  1185499 /usr/local/jdk1.8.0_60/jre/lib/amd64/libmanagement.so
java     9002 suoper  mem    REG                8,1     28386   658927 /webserver/tomcat/ocr-web/bin/bootstrap.jar
java     9002 suoper  mem    REG                8,1    278680  1185591 /usr/local/jdk1.8.0_60/jre/lib/ext/sunjce_provider.jar
java     9002 suoper  mem    REG                8,1     39771  1185595 /usr/local/jdk1.8.0_60/jre/lib/ext/sunec.jar
java     9002 suoper  mem    REG                8,1    250826  1185590 /usr/local/jdk1.8.0_60/jre/lib/ext/sunpkcs11.jar
java     9002 suoper  mem    REG                8,1    114730  1185571 /usr/local/jdk1.8.0_60/jre/lib/jce.jar
java     9002 suoper  mem    REG                8,1    625764  1185556 /usr/local/jdk1.8.0_60/jre/lib/jsse.jar
java     9002 suoper  mem    REG                8,1  65988272  1185564 /usr/local/jdk1.8.0_60/jre/lib/rt.jar
```
3. 根据进程标识查看某个程序进程打开的文件信息

```
[suoper@100-144-tomcat ~]$ lsof -p 9002
COMMAND  PID   USER   FD   TYPE             DEVICE  SIZE/OFF     NODE NAME
java    9002 suoper  cwd    DIR                8,1      4096   658913 /webserver/tomcat/ocr-web/bin
java    9002 suoper  rtd    DIR                8,1      4096        2 /
java    9002 suoper  txt    REG                8,1      7734     7034 /usr/local/jdk1.8.0_60/bin/java
java    9002 suoper  mem    REG                8,1     37261  1185505 /usr/local/jdk1.8.0_60/jre/lib/amd64/libawt_headless.so
java    9002 suoper  mem    REG                8,1    772804  1185528 /usr/local/jdk1.8.0_60/jre/lib/amd64/libawt.so
java    9002 suoper  mem    REG                8,1  99158576  1050588 /usr/lib/locale/locale-archive
java    9002 suoper  mem    REG                8,1   1178935  1185596 /usr/local/jdk1.8.0_60/jre/lib/ext/localedata.jar
java    9002 suoper  mem    REG                8,1   3860522  1185593 /usr/local/jdk1.8.0_60/jre/lib/ext/cldrdata.jar
java    9002 suoper  mem    REG                8,1    110960   786510 /lib64/libresolv-2.12.so
java    9002 suoper  mem    REG                8,1     27424   786458 /lib64/libnss_dns-2.12.so
java    9002 suoper  mem    REG                8,1   3133473  1185560 /usr/local/jdk1.8.0_60/jre/lib/charsets.jar
java    9002 suoper  mem    REG                8,1   3501583  1185587 /usr/local/jdk1.8.0_60/jre/lib/resources.jar
java    9002 suoper  mem    REG                8,1     90880   786576 /lib64/libgcc_s-4.4.7-20120601.so.1
```
4. 列出所有的网络连接

```
lsof -i
```
5. 列出所有tcp 网络连接信息

```
lsof -i tcp
```
6. 列出谁在使用某个端口

```
lsof -i :8080
```
7. 指定TCP端口

```
lsof -i tcp:80
```
