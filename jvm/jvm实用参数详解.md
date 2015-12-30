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
    
###JVM上的垃圾收集
评估一个垃圾收集(GC)算法如何根据如下两个标准:

* 吞吐量越高算法越好
* 暂停时间越短算法越好

吞吐量垃圾收集算法

    -XX:+UseSerialGC    串行垃圾收集器
    -XX:+UseParallelGC  多线程并行执行年轻代垃圾收集
    -XX:+UseParallelOldGC   老代并行垃圾收集
    -XX:ParallelGCThreads   垃圾收集的线程数量。
    
例如:
XX:ParallelGCThreads=6表示每次并行垃圾收集将有6个线程执行。 如果不明确设置该标志，虚拟机将使用基于可用(虚拟)处理器数量计算的默认值。 决定因素是由Java Runtime。availableProcessors()方法的返回值N，如果N<=8，并行垃圾收集器将使用N个垃圾收集线程，如果N>8个可用处理器，
垃圾收集线程数量应为3+5N/8。当JVM独占地使用系统和处理器时使用默认设置更有意义。 但是，如果有多个JVM(或其他耗CPU的系统)在同一台机器上运行，我们应该使用-XX:ParallelGCThreads来减少垃圾收集线程数到一个适当的值。 
例如，如果4个以服务器方式运行的JVM同时跑在在一个具有16核处理器的机器上，设置-XX:ParallelGCThreads=4是明智的，它能使不同JVM的垃圾收集器不会相互干扰。

    -XX:-UseAdaptiveSizePolicy  停用一些人体工程学
    -XX:GCTimeRatio JVM吞吐量要达到的目标值,默认值是99
    -XX:MaxGCPauseMillis    最大暂停时间的目标值(以毫秒为单位). 

如果统计表明正在经历的暂停其时间存在超过目标值的风险时，JVM会修改堆和GC设置以降低它们。 需要注意的是，年轻代和年老代垃圾收集的统计数据是分开计算的，还要注意，默认情况下，最大暂停时间没有被设置。
如果最大暂停时间和最小吞吐量同时设置了目标值，实现最大暂停时间目标具有更高的优先级。当设置最大暂停时间目标时，我们应注意不要选择太小的值。 正如我们现在所知道的，为了保持低暂停时间，JVM需要增加GC次数，那样可能会严重影响可达到的吞吐量。 
这就是为什么对于要求低暂停时间作为主要目标的应用程序(大多数是Web应用程序)，我会建议不要使用吞吐量收集器，而是选择CMS收集器。

###CMS收集器
HotSpot JVM的并发标记清理收集器(CMS收集器)的主要目标就是：低应用停顿时间。该目标对于大多数交互式应用很重要，比如web应用。在我们看一下有关JVM的参数之前,让我们简要回顾CMS收集器的操作和使用它时可能出现的主要挑战。
CMS收集器处理老年代的对象,然而其操作要复杂得多。吞吐量收集器总是暂停应用程序线程，并且可能是相当长的一段时间，然而这能够使该算法安全地忽略应用程序。相比之下，CMS收集器被设计成在大多数时间能与应用程序线程并行执行，
仅仅会有一点(短暂的)停顿时间。GC与应用程序并行的缺点就是，可能会出现各种同步和数据不一致的问题。为了实现安全且正确的并发执行，CMS收集器的GC周期被分为了好几个连续的阶段。

####CMS收集器的过程
CMS收集器的GC周期由6个阶段组成。其中4个阶段(名字以Concurrent开始的)与实际的应用程序是并发执行的，而其他2个阶段需要暂停应用程序线程。

1. 初始标记：为了收集应用程序的对象引用需要暂停应用程序线程，该阶段完成后，应用程序线程再次启动。
2. 并发标记：从第一阶段收集到的对象引用开始，遍历所有其他的对象引用。
3. 并发预清理：改变当运行第二阶段时，由应用程序线程产生的对象引用，以更新第二阶段的结果。
4. 重标记：由于第三阶段是并发的，对象引用可能会发生进一步改变。因此，应用程序线程会再一次被暂停以更新这些变化，并且在进行实际的清理之前确保一个正确的对象引用视图。这一阶段十分重要，因为必须避免收集到仍被引用的对象。
5. 并发清理：所有不再被应用的对象将从堆里清除掉。
6. 并发重置：收集器做一些收尾的工作，以便下一次GC周期能有一个干净的状态。

应该指出，尽管CMS收集器为老年代垃圾回收提供了几乎完全并发的解决方案，然而年轻代仍然通过“stop-the-world”方法来进行收集。对于交互式应用，停顿也是可接受的，背后的原理是年轻带的垃圾回收时间通常是相当短的。

CMS收集器有两个方面需要调优

* 堆碎片
* 对象分配率高


    -XX：+UseConcMarkSweepGC 激活CMS收集器。默认HotSpot JVM使用的是并行收集器。
    -XX：UseParNewGC 年轻代使用多线程并行执行垃圾回收。
    
注意最新的JVM版本，当使用-XX：+UseConcMarkSweepGC时，-XX：UseParNewGC会自动开启。因此，如果年轻代的并行GC不想开启，可以通过设置-XX：-UseParNewGC来关掉。
    
    -XX：+CMSConcurrentMTEnabled 并发的CMS阶段将以多线程执行
    -XX：ConcGCThreads   并发CMS过程运行时的线程数

标志-XX：ConcGCThreads=<value>(早期JVM版本也叫-XX:ParallelCMSThreads)定义并发CMS过程运行时的线程数。比如value=4意味着CMS周期的所有阶段都以4个线程来执行。尽管更多的线程会加快并发CMS过程，但其也会带来额外的同步开销。
因此，对于特定的应用程序，应该通过测试来判断增加CMS线程数是否真的能够带来性能的提升。如果还标志未设置，JVM会根据并行收集器中的-XX：ParallelGCThreads参数的值来计算出默认的并行CMS线程数。该公式是ConcGCThreads = (ParallelGCThreads + 3)/4。
因此，对于CMS收集器， -XX:ParallelGCThreads标志不仅影响“stop-the-world”垃圾收集阶段，还影响并发阶段。总之，有不少方法可以配置CMS收集器的多线程执行。正是由于这个原因,建议第一次运行CMS收集器时使用其默认设置, 然后如果需要调优再进行测试。
只有在生产系统中测量(或类生产测试系统)发现应用程序的暂停时间的目标没有达到 , 就可以通过这些标志应该进行GC调优。
    
    -XX:CMSInitiatingOccupancyFraction

当堆满之后，并行收集器便开始进行垃圾收集，例如，当没有足够的空间来容纳新分配或提升的对象。对于CMS收集器，长时间等待是不可取的，因为在并发垃圾收集期间应用持续在运行(并且分配对象)。因此，为了在应用程序使用完内存之前完成垃圾收集周期，CMS收集器要比并行收集器更先启动。
因为不同的应用会有不同对象分配模式，JVM会收集实际的对象分配(和释放)的运行时数据，并且分析这些数据，来决定什么时候启动一次CMS垃圾收集周期。为了引导这一过程， JVM会在一开始执行CMS周期前作一些线索查找。该线索由 -XX:CMSInitiatingOccupancyFraction=<value>来设置，
该值代表老年代堆空间的使用率。比如，value=75意味着第一次CMS垃圾收集会在老年代被占用75%时被触发。通常CMSInitiatingOccupancyFraction的默认值为68(之前很长时间的经历来决定的)。

    -XX：+UseCMSInitiatingOccupancyOnly
    
我们用-XX+UseCMSInitiatingOccupancyOnly标志来命令JVM不基于运行时收集的数据来启动CMS垃圾收集周期。而是，当该标志被开启时，JVM通过CMSInitiatingOccupancyFraction的值进行每一次CMS收集，而不仅仅是第一次。然而，请记住大多数情况下，
JVM比我们自己能作出更好的垃圾收集决策。因此，只有当我们充足的理由(比如测试)并且对应用程序产生的对象的生命周期有深刻的认知时，才应该使用该标志。

    -XX:+CMSClassUnloadingEnabled
    
相对于并行收集器，CMS收集器默认不会对永久代进行垃圾回收。如果希望对永久代进行垃圾回收，可用设置标志-XX:+CMSClassUnloadingEnabled。在早期JVM版本中，要求设置额外的标志-XX:+CMSPermGenSweepingEnabled。注意，即使没有设置这个标志，一旦永久代耗尽空间也会尝试进行垃圾回收，
但是收集不会是并行的，而再一次进行Full GC。

    -XX:+CMSIncrementalMode
    
该标志将开启CMS收集器的增量模式。增量模式经常暂停CMS过程，以便对应用程序线程作出完全的让步。因此，收集器将花更长的时间完成整个收集周期。因此，只有通过测试后发现正常CMS周期对应用程序线程干扰太大时，才应该使用增量模式。由于现代服务器有足够的处理器来适应并发的垃圾收集，所以这种情况发生得很少。

    -XX:+ExplicitGCInvokesConcurrent and -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses
    
如今,被广泛接受的最佳实践是避免显式地调用GC(所谓的“系统GC”)，即在应用程序中调用system.gc()。然而，这个建议是不管使用的GC算法的，值得一提的是，当使用CMS收集器时，系统GC将是一件很不幸的事，因为它默认会触发一次Full GC。幸运的是，有一种方式可以改变默认设置。
标志-XX:+ExplicitGCInvokesConcurrent命令JVM无论什么时候调用系统GC，都执行CMS GC，而不是Full GC。第二个标志-XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses保证当有系统GC调用时，永久代也被包括进CMS垃圾回收的范围内。因此，通过使用这些标志，我们可以防止出现意料之外的”stop-the-world”的系统GC。

    -XX:+DisableExplicitGC
    
然而在这个问题上…这是一个很好提到- XX:+ DisableExplicitGC标志的机会，该标志将告诉JVM完全忽略系统的GC调用(不管使用的收集器是什么类型)。对于我而言，该标志属于默认的标志集合中，可以安全地定义在每个JVM上运行，而不需要进一步思考。

###GC日志

    -XX:+PrintGC
    
参数-XX:+PrintGC（或者-verbose:gc）开启了简单GC日志模式，为每一次新生代（young generation）的GC和每一次的Full GC打印一行信息。

例如:

    [GC 246656K->243120K(376320K), 0.0929090 secs]
    [Full GC 243120K->241951K(629760K), 1.5589690 secs]

每行开始首先是GC的类型（可以是“GC”或者“Full GC”），然后是在GC之前和GC之后已使用的堆空间，再然后是当前的堆容量，最后是GC持续的时间（以秒计）。

    -XX:PrintGCDetails
    
如果不是使用-XX:+PrintGC，而是-XX:PrintGCDetails，就开启了详细GC日志模式。在这种模式下，日志格式和所使用的GC算法有关。我们首先看一下使用Throughput垃圾收集器在young generation中生成的日志。为了便于阅读这里将一行日志分为多行并使用缩进。

    [GC
    [PSYoungGen: 142816K->10752K(142848K)] 246648K->243136K(375296K), 0.093500 secs
    ]
    [Times: user=0.55 sys=0.10, real=0.09 secs]
    
我们可以很容易发现：这是一次在young generation中的GC，它将已使用的堆空间从246648K减少到了243136K，用时0.0935090秒。此外我们还可以得到更多的信息：所使用的垃圾收集器（即PSYoungGen）、young generation的大小和使用情况（在这个例子中“PSYoungGen”垃圾收集器将young generation所使用的堆空间从142816K减少到10752K）。
既然我们已经知道了young generation的大小，所以很容易判定发生了GC，因为young generation无法分配更多的对象空间：已经使用了142848K中的142816K。我们可以进一步得出结论，多数从young generation移除的对象仍然在堆空间中，只是被移到了old generation：通过对比绿色的和蓝色的部分可以发现即使young generation几乎被完全清空
（从142816K减少到10752K），但是所占用的堆空间仍然基本相同（从246648K到243136K）。
详细日志的“Times”部分包含了GC所使用的CPU时间信息，分别为操作系统的用户空间和系统空间所使用的时间。同时，它显示了GC运行的“真实”时间（0.09秒是0.0929090秒的近似值）。如果CPU时间（译者注：0.55秒+0.10秒）明显多于”真实“时间（译者注：0.09秒），我们可以得出结论：GC使用了多线程运行。
这样的话CPU时间就是所有GC线程所花费的CPU时间的总和。实际上我们的例子中的垃圾收集器使用了8个线程。

    [Full GC
    [PSYoungGen: 10752K->9707K(142848K)]
    [ParOldGen: 232384K->232244K(485888K)] 243136K->241951K(628736K)
    [PSPermGen: 3162K->3161K(21504K)], 1.5265450 secs
    ]
    
除了关于young generation的详细信息，日志也提供了old generation和permanent generation的详细信息。对于这三个generations，一样也可以看到所使用的垃圾收集器、堆空间的大小、GC前后的堆使用情况。需要注意的是显示堆空间的大小等于young generation和old generation各自堆空间的和。以上面为例，堆空间总共占用了241951K，
其中9707K在young generation，232244K在old generation。Full GC持续了大约1.53秒，用户空间的CPU执行时间为10.96秒，说明GC使用了多线程（和之前一样8个线程）。
对不同generation详细的日志可以让我们分析GC的原因，如果某个generation的日志显示在GC之前，堆空间几乎被占满，那么很有可能就是这个generation触发了GC。但是在上面的例子中，三个generation中的任何一个都不是这样的，在这种情况下是什么原因触发了GC呢。对于Throughput垃圾收集器，在某一个generation被过度使用之前，GC ergonomics（参考本系列第6节）决定要启动GC。
Full GC也可以通过显式的请求而触发，可以是通过应用程序，或者是一个外部的JVM接口。这样触发的GC可以很容易在日志里分辨出来，因为输出的日志是以“Full GC(System)”开头的，而不是“Full GC”。
对于Serial垃圾收集器，详细的GC日志和Throughput垃圾收集器是非常相似的。唯一的区别是不同的generation日志可能使用了不同的GC算法（例如：old generation的日志可能以Tenured开头，而不是ParOldGen）。使用垃圾收集器作为一行日志的开头可以方便我们从日志就判断出JVM的GC设置。
对于CMS垃圾收集器，young generation的详细日志也和Throughput垃圾收集器非常相似，但是old generation的日志却不是这样。对于CMS垃圾收集器，在old generation中的GC是在不同的时间片内与应用程序同时运行的。GC日志自然也和Full GC的日志不同。而且在不同时间片的日志夹杂着在此期间young generation的GC日志。
但是了解了上面介绍的GC日志的基本元素，也不难理解在不同时间片内的日志。只是在解释GC运行时间时要特别注意，由于大多数时间片内的GC都是和应用程序同时运行的，所以和那种独占式的GC相比，GC的持续时间更长一些并不说明一定有问题。
正如我们在第7节中所了解的，即使CMS垃圾收集器没有完成一个CMS周期，Full GC也可能会发生。如果发生了GC，在日志中会包含触发Full GC的原因，例如众所周知的”concurrent mode failure“。

    -XX:+PrintGCTimeStamps和-XX:+PrintGCDateStamps
    
使用-XX:+PrintGCTimeStamps可以将时间和日期也加到GC日志中。表示自JVM启动至今的时间戳会被添加到每一行中。例子如下：

    0.185: [GC 66048K->53077K(251392K), 0.0977580 secs]
    0.323: [GC 119125K->114661K(317440K), 0.1448850 secs]
    0.603: [GC 246757K->243133K(375296K), 0.2860800 secs]

如果指定了-XX:+PrintGCDateStamps，每一行就添加上了绝对的日期和时间。

    2014-01-03T12:08:38.102-0100: [GC 66048K->53077K(251392K), 0.0959470 secs]
    2014-01-03T12:08:38.239-0100: [GC 119125K->114661K(317440K), 0.1421720 secs]
    2014-01-03T12:08:38.513-0100: [GC 246757K->243133K(375296K), 0.2761000 secs]
    
如果需要也可以同时使用两个参数。推荐同时使用这两个参数，因为这样在关联不同来源的GC日志时很有帮助。

    -Xloggc
    
缺省的GC日志时输出到终端的，使用-Xloggc:也可以输出到指定的文件。需要注意这个参数隐式的设置了参数-XX:+PrintGC和-XX:+PrintGCTimeStamps，但为了以防在新版本的JVM中有任何变化，我仍建议显示的设置这些参数。

###可管理的JVM参数
一个常常被讨论的问题是在生产环境中GC日志是否应该开启。因为它所产生的开销通常都非常有限，因此我的答案是需要开启。但并不一定在启动JVM时就必须指定GC日志参数。
HotSpot JVM有一类特别的参数叫做可管理的参数。对于这些参数，可以在运行时修改他们的值。我们这里所讨论的所有参数以及以“PrintGC”开头的参数都是可管理的参数。这样在任何时候我们都可以开启或是关闭GC日志。比如我们可以使用JDK自带的jinfo工具来设置这些参数，或者是通过JMX客户端调用HotSpotDiagnostic MXBean的setVMOption方法来设置这些参数。 