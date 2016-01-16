#Condition接口
> 是多线程之间协调通讯的工具类, 使得某个或某些线程等待某个条件(Condition). 
只有当该条件具备(signal 或者 signalAll方法被带调用)时, 这些等待线程才会被唤醒, 从而重新争夺锁.

1. await()方法
将当前线程挂起, 等待其它线程唤醒.
2. awaitUninterruptibly() 方法

3. signal()方法
唤醒其它挂起的线程
4. signalAll()方法
唤醒其它所有挂起的线程