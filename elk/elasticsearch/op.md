#ES设置堆内存大小
对于环境变量 $ES_HEAP_SIZE 在设置Elasticsearch堆大小的时候有2个法则可以运用:
1. 不起过RAM的50%
	Lucene很好的利用了文件系统cache，文件系统cache是由内核管理的。如果没有足够的文 件系统cache空间，性能就会变差。
2. 不超过32G
	如果堆小于32GB，JVM能够使用压缩的指针，这会节省许多内存：每个指针就会使用4字节 而不是8字节。把对内存从32GB增加到34GB将意味着你将有 更少 的内存可用，因为所有的指针占用了 双倍的空间。同样，更大的堆，垃圾回收变得代价更大并且可能导致节点不稳定。