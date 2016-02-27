# 前言
---

> AbstractQueuedSynchronizer类(AQS), 是J.U.C框架的核心类。 是用来构建锁或者其它同步组件的基础框架。

##基本原理##
> 它使用了一个用int类型的state属性表示同步状态，通过内置的FIFO队列来完成资源获取线程的排队工作。
该类分为两种功能一个是独占功能和共享功能. 它子类中只能实现其中一种功能.

## 同步队列 ##
> AbstractQueuedSynchronizer是用同步队列来完成同步状态的管理。假设当前线程获取同步状态失败时，则把当前线程以及等待信息构造成为一个节点（Node)并将其加入同步队列中，同时会**阻塞当前线程**。当同步状态释放时，会把首节点中的线程唤醒，使其再次尝试获取同步状态。等待队列是"CLH"锁队列的变体。通常CLH锁是一个自旋锁。

## Node节点 ##
> 内部类Node是等待队列中的节点。它用来保存线程的引用、等待状态以及前驱和后继节点。

### Node waitStatus属性 ###
该属性用于描述节点的状态.
共有四种状态:
* CANCELLED = 1 表示线程已取消。由于在同步队列中等待的线程超时或被中断，则会被设置为CANCELLED。
* SIGNAL = -1   表示线程等待触发。后继节点的线程如果释放了同步状态或者被取消，将会通知后继节点，使后继节点的线程得以运行。
* CONDITION = -2    表示线程等待条件。该节点线程等待在Condition上，当其他线程对Condition调用了signal()方法后，该节点将会从等待队列中转移到同步队列中，加入到对同步状态的获取中。
* PROPAGATE = -3    表示下一次共享式同步状态获取将会无条件的被传播下去。
* 0 初始状态。

### Node.prev ###
前驱节点，当节点加入队列时被设置。

### Node.next ###
后继节点

### Node.nextWaiter ###
等待队列中的后继节点。如果当前节点是共享的，那么这个字段将是一个SHARED常量，也就是说节点类型（独点和共享）和等待队列中的后继节点共用同一个字段

### Node.thread ###
待获取同步状态的线程引用

## 源码分析 ##
### 1. acquire()方法 ###
> 先尝试获取锁, 如果没有获取到那么将放入到等待队列中并阻塞当前线程.

    public final void acquire(int arg) {
        //尝试获取锁, 
        if (!tryAcquire(arg) &&
            //如果未获取到锁，则放入等待队列
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            //设置中断
            selfInterrupt();
    }

### 2. tryAcquire()方法 ###
> 由具体子类去实现, 如果获取到锁则返回true, 否则为false

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

### 3. addWaiter()方法 ###
> 将创建Node并将当前线程加入等待队列, 参数Node mode是 独点还是共享模式. 


    private Node addWaiter(Node mode) {
        //创建Node并设置mode
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on          
        // failure
        Node pred = tail;
        if (pred != null) { //如果tail不为null
            // 把新建的node节点的前一节点指向tail.
            node.prev = pred;
            // 然后通过CAS将新创建的node设置为tail,如果设置             
            // 成功则把tail的下一个点指向新建的node,其实就是             
            //自已.并返回node
            // 如果设置未成功,则进入等待队列. 
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        //入队列
        enq(node);
        return node;
    }
    
### 4. enq()方法 ###
> 是一个无限循环(也叫自旋), 将Node节眯放入队列的tail. 


    private Node enq(final Node node) {
        for (;;) {
            Node t = tail;
            //如果tail为null, 可以证明queue是空的, 需要初始化.
            if (t == null) { // Must initialize
                //通过CAS方式创建head, 是一个空的node, 并把tail和head设置为同一个node
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else { // 如果不为空
                // 把新建的node节点的前一节点指向tail.
                node.prev = t;
                // 然后通过CAS将新创建的node设置为tail, 如果设置成功则把tail的下一个点指向新建的node,其实就是自已.并返回node.
                // 并返回tail
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }
    
### 5. acquireQueued()方法 ### 
> 是一个无限循环


    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                //获取node节点的前一个节点
                final Node p = node.predecessor();
                //如果前一个节点是head节点并且尝试获取到锁
                if (p == head && tryAcquire(arg)) {
                    //将node节点设置为head, 并且把node属性thread和prev设为null,
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    //返回false无中断
                    return interrupted;
                }
                // shouldParkAfterFailedAcquire方法是检查前一个节点的状态, 看当前获取锁失败的线程是否需要挂起.
                if (shouldParkAfterFailedAcquire(p, node) &&
                    //如需要, 使用LockSopport类park方法将当前线程挂起, 直到被唤醒
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                //如果失败, 则将当前node节点从队列中删除.
                cancelAcquire(node);
        }
    }
    
### 6. shouldParkAfterFailedAcquire()方法 ###

    //参数pred是参数node的前一个节点, 参数node是当前节点
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        //如当前节点的前一个节点状态为SIGNAL时, 直接返回true. 
        if (ws == Node.SIGNAL)
            /*
             * This node has already set status asking a release
             * to signal it, so it can safely park.
             */
            return true;
        //如果当前节点的前一个节点大于0, 表示已取消.
        if (ws > 0) {
            /*
             * Predecessor was cancelled. Skip over predecessors and
             * indicate retry.
             */
            //循环遍历队列中的前一个元素, 直到waitStatus为非CANCELLED状态.
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            //并将把当前节点指向为非CANCELLED状态的节点的后一节点上.
            pred.next = node;
        } else { //如果前一个节点的状态不是SIGNAL或CANCELLED
            /*
             * waitStatus must be 0 or PROPAGATE.  Indicate that we
             * need a signal, but don't park yet.  Caller will need to
             * retry to make sure it cannot acquire before parking.
             */
             //那么通过CAS将waitStatus的状态更改为SIGNAL状态.
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }
    
###  7. parkAndCheckInterrupt()方法 ###
> 将当前线程挂起


    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }
    
### 8. release()方法 ### 
> 释放锁. 如果为true则成功; 否则失败


    public final boolean release(int arg) {
        //尝试释放锁
        if (tryRelease(arg)) { //如果释放成功
            Node h = head;
            //如果head不为null并且head的waitStatus不为0
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }
    
### 9. tryRelease()方法 ###
> 尝试释放锁, 由子类去实现

### 10. unparkSuccessor()方法 ###
> 唤醒head节点线程


    //TODO 后续继续整理
    private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);
        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }
    
### 11. compareAndSetState(int expect, int update) ###
> 用CAS更新state状态，保证state设置具有原子性。

    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
    
