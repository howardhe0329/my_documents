#前言
> ReentrantLock是可重入独占锁,由[AQS]()实现. 可以替代synchronized关键字, 而且提供了更多的其它功能.

首先理解一下什么是独占锁(排它锁)和共享锁:

* 独占锁是指有且只有一个线程获取到锁
* 共享锁是指N个线程可以共享锁

可重入是指如果当前线程获取锁, 那么这个线程可以多次获取该锁.

##公平锁和非公平锁
公平锁是指最先获取锁的请求一定会最先满足. 反之是非公平锁.通常公平锁性能要低于非公平锁, 因为公平的获取锁没有
考虑到操作系统对线程的调度因素, 这样造成JVM对于等待中的线程调度次序和操作系统对线程的调度之间的不匹配。
但是并不是任何场景都是以TPS作为唯一指标的，因为公平锁能够减少“饥饿”发生的概率，等待越久的请求越是能够得到优先满足。
通过构造方法传入参数可以设置是公平锁还是非公平锁. 默认构造方法是非公平锁. 见如下代码:


    public ReentrantLock(boolean fair) {
        //如果为true是公平锁; 否则为非公平锁
        sync = fair ? new FairSync() : new NonfairSync();
    }

ReentrantLock类有两个内部类分别是: FairSync类(公平锁)和NonfairSync类(非公平锁). 
我们先看看FairSync的lock方法

    final void lock() {
        acquire(1);
    }
    
调用了acquire(1), 该方法是继承了AbstractQueuedSynchronizer类(AQS)来实现的.我们进去看看该方法:

    public final void acquire(int arg) {
        //尝试获取锁
        if (!tryAcquire(arg) &&
            //如果获取不到那么放入到队列中, 并阻塞该线程
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }

该方法是以独占模式获取锁, 忽略中断. 在成功返回时至少调用一次tryAcquire()方法, 否则该线程进入等待队列中, 可能反复阻塞和解除阻塞.
直到调用tryAcquire()成功. 该方法通常用来实现Lock接口的lock方法. 下面我来看看tryAcquire()方法

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

该方法抛出一个不支持操作异常, 看来是需要子类来实现该方法. 那么我们看看FairSync类里的tryAcquire()方法

    protected final boolean tryAcquire(int acquires) {
        //获取当前线程
        final Thread current = Thread.currentThread();
        // 获取state
        int c = getState();
        if (c == 0) {
            // hasQueuedPredecessors() 如果队列中没有其它线程,说明没有其它线程占有锁.
            if (!hasQueuedPredecessors() &&
                // compareAndSetState 通过CAS原理将修改state, 这里是将0修改为1, 因为acquires是参数传过来的. 
                compareAndSetState(0, acquires)) {
                // 如state 修改成功,证明该线程获取到锁.
                //setExclusiveOwnerThread 该方法将当前线程设置为锁的拥有者
                setExclusiveOwnerThread(current);
                return true;
            }
        }
        // 如果 state不为0 ,则判读当前线程和获取锁的线程是否为同一个线程.
        else if (current == getExclusiveOwnerThread()) {
            // 将state 值加上acquires, 也就是加1
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            //更新state
            setState(nextc);
            return true;
        }
        return false;
    }
    
tryAcquire()方法通常是不管能不能获取到锁, 都会立即返回状态. 不会阻塞当前线程. true是获取到锁; false未获取到锁.
我们在看看hasQueuedPredecessors方法, 

    //查找是否有线程一直等待获得比当前线程更长的时间
    public final boolean hasQueuedPredecessors() {
        // tail 是 队列尾
        Node t = tail; // Read fields in reverse initialization order
        // head 是队列头
        Node h = head;
        Node s;
        // head不等于tail并且head的下一个node为null或者head的线程不等于当前线程
        return h != t &&
            ((s = h.next) == null || s.thread != Thread.currentThread());
    }
    
接着我们回到acquire()方法, 分析acquireQueued()

    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }