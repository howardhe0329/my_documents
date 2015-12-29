###JVM功能
 ***
 
* 自适应内存管理
* 垃圾收集
* 及时编译
* 动态类加载
* 锁优化

###JVM参数分类

* 标准参数
    >标准参数中包括功能和输出的参数都是很稳定的，很可能在将来的JVM版本中不会改变。你可以用java命令（或者是用 java -help）检索出所有标准参数。我们在第一部分中已经见到过一些标准参数，例如：-server。
* X参数
    >非标准化的参数在将来的版本中可能会改变。所有的这类参数都以-X开始，并且可以用java -X来检索。注意，不能保证所有参数都可以被检索出来，其中就没有-Xcomp。
* XX参数
    >然而很多XX参数仍在实验当中（主要是JVM的开发者用于debugging和调优JVM自身的实现

###JVM参数使用
* 对于布尔类型的参数，我们有”+”或”-“，然后才设置JVM选项的实际名称。例如，-XX:+<name>用于激活<name>选项，而-XX:-<name>用于注销选项。
* 对于需要非布尔值的参数，如string或者integer，我们先写参数的名称，后面加上”=”，最后赋值。例如，  -XX:<name>=<value>给<name>赋值<value>

###版本信息

    -showversion
    
在java启动加上上面的命令可以查看版本信息.

###编译字节码

    -Xint   标记会强制JVM执行所有的字节码. 会降低运行速度，通常低10倍或更多.
    -Xcomp  参数与它（-Xint）正好相反，JVM在第一次使用时会把所有的字节码编译成本地代码，从而带来最大程度的优化.
    -Xmixed 默认的参数  混合模式.
    

###命令行中输出所有JVM参数值

    -XX:+PrintFlagsFinal -XX:+PrintFlagsInitial
    
例如:

    java -client -XX:+PrintFlagsFinal Benchmark
    [Global flags]
    uintx AdaptivePermSizeWeight               = 20               {product}
    uintx AdaptiveSizeDecrementScaleFactor     = 4                {product}
    uintx AdaptiveSizeMajorGCDecayTimeScale    = 10               {product}
    uintx AdaptiveSizePausePolicy              = 0                {product}[...]
    uintx YoungGenerationSizeSupplementDecay   = 8                {product}
    uintx YoungPLABSize                        = 4096             {product}
     bool ZeroTLAB                             = false            {product}
     intx hashCode                             = 0                {product}
     
每一行代表一个XX参数, 第一列是参数的数据类型, 第二列是参数名称, 第四列是值, 第五列是参数的类别. 第三列”=”表示第四列是参数的默认值，而”:=” 表明了参数被用户或者JVM赋值了.

    -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions  解锁隐藏的参数
    
例如:

    java -server -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal Benchmark
    
###显示被用户或JVM设置的参数(很实用)
    
    -XX:+PrintCommandLineFlags
    
例如:
    
    java -server -XX:+PrintCommandLineFlags Benchmark
    
    -XX:InitialHeapSize=57505088 -XX:MaxHeapSize=920081408 -XX:ParallelGCThreads=4 -XX:+PrintCommandLineFlags -XX:+UseParallelGC
    
    
###内存调优

    -Xms and -Xmx (or: -XX:InitialHeapSize and -XX:MaxHeapSize)
    
###JVM内存溢出生成heap dump

    -XX:+HeapDumpOnOutOfMemoryError JVM在发生内存溢出时自动的生成堆内存快照
    -XX:HeapDumpPath=/log/heapdump.hprof       堆内存快照会保存在JVM的启动目录下名为java_pid<pid>.hprof 的文件里
    -XX:OnOutOfMemoryError          当内存溢发生时，我们甚至可以可以执行一些指令
    
例如:
    
    java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof -XX:OnOutOfMemoryError ="sh ~/cleanup.sh" MyApp
    
###JVM内存永久代设置

    -XX:PermSize and -XX:MaxPermSize
    
请注意，这里设置的永久代大小并不会被包括在使用参数-XX:MaxHeapSize 设置的堆内存大小中。也就是说，通过-XX:MaxPermSize设置的永久代内存可能会需要由参数-XX:MaxHeapSize 设置的堆内存以外的更多的一些堆内存。

###代码缓存

    -XX:InitialCodeCacheSize and -XX:ReservedCodeCacheSize  
    -XX:+UseCodeCacheFlushing   当代码缓存被填满时让JVM放弃一些编译代码。
    
###新生代大小设置

    -XX:NewSize and -XX:MaxNewSize
    
新生代只是整个堆的一部分，新生代设置的越大，老年代区域就会减少。-XX:MaxNewSize 最大可以设置为-Xmx/2.

    -XX:NewRatio
    
可以设置新生代和老年代的相对大小。这种方式的优点是新生代大小会随着整个堆大小动态扩展。参数 -XX:NewRatio 设置老年代与新生代的比例。例如 -XX:NewRatio=3 指定老年代/新生代为3/1. 老年代占堆大小的 3/4 ，新生代占 1/4 .
如果针对新生代,同时定义绝对值和相对值,绝对值将起作用。

    -XX:SurvivorRatio
    
参数 -XX:SurvivorRatio 与 -XX:NewRatio 类似，作用于新生代内部区域。-XX:SurvivorRatio 指定伊甸园区(Eden)与幸存区大小比例. 例如, -XX:SurvivorRatio=10 表示伊甸园区(Eden)是 幸存区To 大小的10倍(也是幸存区From的10倍).
所以,伊甸园区(Eden)占新生代大小的10/12, 幸存区From和幸存区To 每个占新生代的1/12 .注意,两个幸存区永远是一样大的.

设定幸存区大小有什么作用? 假设幸存区相对伊甸园区(Eden)太小, 相应新生对象的伊甸园区(Eden)永远很大空间, 我们当然希望,如果这些对象在GC时全部被回收,伊甸园区(Eden)被清空,一切正常.
然而,如果有一部分对象在GC中幸存下来, 幸存区只有很少空间容纳这些对象.结果大部分幸存对象在一次GC后，就会被转移到老年代 ,这并不是我们希望的.考虑相反情况, 假设幸存区相对伊甸园区(Eden)太大,当然有足够的空间，容纳GC后的幸存对象. 
但是过小的伊甸园区(Eden),意味着空间将越快耗尽，增加新生代GC次数，这是不可接受的。总之,我们希望最小化短命对象晋升到老年代的数量，同时也希望最小化新生代GC 的次数和持续时间.我们需要找到针对当前应用的折中方案, 寻找适合方案的起点是 了解当前应用中对象的年龄分布情况。

    -XX:+PrintTenuringDistribution
    
参数 -XX:+PrintTenuringDistribution 指定JVM 在每次新生代GC时，输出幸存区中对象的年龄分布。
例如:

    Desired survivor size 75497472 bytes, new threshold 15 (max 15)
    - age 1: 19321624 bytes, 19321624 total
    - age 2: 79376 bytes, 19401000 total
    - age 3: 2904256 bytes, 22305256 total

第一行说明幸存区To大小为 75 MB. 也有关于老年代阀值(tenuring threshold)的信息, 老年代阀值，意思是对象从新生代移动到老年代之前，经过几次GC(即, 对象晋升前的最大年龄). 上例中,老年代阀值为15,最大也是15.
之后行表示，对于小于老年代阀值的每一个对象年龄,本年龄中对象所占字节 (如果当前年龄没有对象,这一行会忽略). 上例中,一次 GC 后幸存对象大约 19 MB, 两次GC 后幸存对象大约79 KB , 三次GC 后幸存对象大约 3 MB .
每行结尾，显示直到本年龄全部对象大小.所以,最后一行的 total 表示幸存区To 总共被占用22 MB . 幸存区To 总大小为 75 MB ,当前老年代阀值为15，可以断定在本次GC中，没有对象会移动到老年代。
现在假设下一次GC 输出为：

    Desired survivor size 75497472 bytes, new threshold 2 (max 15)
    - age 1: 68407384 bytes, 68407384 total
    - age 2: 12494576 bytes, 80901960 total
    - age 3: 79376 bytes, 80981336 total
    - age 4: 2904256 bytes, 83885592 total
    
对比前一次老年代分布。明显的,年龄2和年龄3 的对象还保持在幸存区中，因为我们看到年龄3和4的对象大小与前一次年龄2和3的相同。同时发现幸存区中,有一部分对象已经被回收,因为本次年龄2的对象大小为 12MB ，而前一次年龄1的对象大小为 19 MB。
最后可以看到最近的GC中，有68 MB 新对象，从伊甸园区移动到幸存区。注意,本次GC 幸存区占用总大小 84 MB -大于75 MB. 结果,JVM 把老年代阀值从15降低到2，在下次GC时，一部分对象会强制离开幸存区，这些对象可能会被回收(如果他们刚好死亡)或移动到老年代。

    -XX:InitialTenuringThreshold    可以设定老年代阀值的初始值和最大值
    -XX:MaxTenuringThreshold        可以设定老年代阀值的初始值和最大值
    -XX:TargetSurvivorRatio         设定幸存区的目标使用率.
    
例如:

    -XX:MaxTenuringThreshold=10 -XX:TargetSurvivorRatio=90 设定老年代阀值的上限为10,幸存区空间目标使用率为90%。
    
