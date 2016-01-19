#Lock接口
> 实现与synchronized同样的功能, 但是Lock接口实现了更多的高级功能. 如可以设置timeout, 或是尝试获取锁

## 接口方法详解
### 1. lock()方法
获取锁，如果锁被其它线程所获取，则当前线进入等待队列，即**阻塞**当前线程，直到其它线程获放锁并且当前线程获取到锁。
### 2. lockInterruptibly（）方法
获取锁，如果锁被其它线程所获取，则当前线程进入等待队列，即**阻塞**当前线程，直到其它线程获放锁并且当前线程获取到锁。但是，如果执行currentThread.interrupt()方法，会中断当前线程并抛出InterruptedException. 
### 3. boolean tryLock()方法
尝试获取锁，不会被阻塞。 如果获取到锁则返回true, 否则false。
### 4. boolean tryLock(long time, TimeUnit unit)方法
尝试获取锁，并等待指定的时间（这段时间是阻塞状态）。如果获取到锁则返回true, 否则false。
### 5. void unlock()方法
释放锁。如果当前线程未获取锁，调用unlock()方法则会抛IllegalMonitorStateException异常。
### 6. Condition newCondition()方法
创建一个Condition。有关Condition接口说明请见[Condition接口说明](/java/j.u.c/Condition接口说明.md)

## Lock的实现类 ##
### ReentrantLock ###
> 可重入的互斥锁。[ReentrantLock源码分析](/java/j.u.c/ReentrantLock源码分析.md)


# ReadWriteLock接口
> 读写锁

## ReadWriteLock的实现类
### ReentrantReadWriteLock ###


