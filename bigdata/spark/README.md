## 常见问题

* 1. 16/03/26 22:35:57 INFO HadoopRDD: Input split: hdfs://192.168.99.18:9000/user/suoper/services_business_data/original/16-03-25/23-05/26_14-1458918314096-.1458918323133.gz:0+4054
     16/03/26 22:35:57 ERROR Executor: Exception in task 21800.3 in stage 0.0 (TID 21826)
     java.io.IOException: Cannot obtain block length for LocatedBlock{BP-838859114-192.168.99.18-1457602570813:blk_1077272772_3644162; getBlockSize()=4054; corrupt=false; offset=0; locs=[192.168.99.11:50010, 192.168.99.10:50010, 192.168.99.12:50010]; storageIDs=[DS-c62b86f2-0421-4a9c-bca2-969c887000d9, DS-47e4e893-89ba-45d8-bdf7-09afa3fe7260, DS-b32a3042-cd7b-45a5-b13a-906a8a7a611a]; storageTypes=[DISK, DISK, DISK]}
     
 出现上述错误时, 检查这个文件是否有问题. 如果为0字节大小的文件. 就删除该文件, 问题即解决.