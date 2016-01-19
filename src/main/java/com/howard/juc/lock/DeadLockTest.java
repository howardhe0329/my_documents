package com.howard.juc.lock;

import com.howard.juc.common.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dead Lock example. 可以通过线程dump,可以查找原因:
 * Found one Java-level deadlock:
     =============================
     "Thread-1":
     waiting for ownable synchronizer 0x00000007976094f8, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
     which is held by "Thread-0"
     "Thread-0":
     waiting for ownable synchronizer 0x0000000797605168, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
     which is held by "Thread-1"

     Java stack information for the threads listed above:
     ===================================================
     "Thread-1":
     at sun.misc.Unsafe.park(Native Method)
     - parking to wait for  <0x00000007976094f8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     at com.howard.juc.lock.DeadLockTest.lambda$main$1(DeadLockTest.java:35)
     at com.howard.juc.lock.DeadLockTest$$Lambda$2/668386784.run(Unknown Source)
     at java.lang.Thread.run(Thread.java:745)
     "Thread-0":
     at sun.misc.Unsafe.park(Native Method)
     - parking to wait for  <0x0000000797605168> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
     at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
     at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
     at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
     at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
     at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
     at com.howard.juc.lock.DeadLockTest.lambda$main$0(DeadLockTest.java:20)
     at com.howard.juc.lock.DeadLockTest$$Lambda$1/381259350.run(Unknown Source)
     at java.lang.Thread.run(Thread.java:745)

     Found 1 deadlock.

 * Created by howard on 16/1/18.
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            lock1.lock();
            System.out.println("thread name: " + Thread.currentThread().getName() + "; lock1.lock()");
            try {
                Thread.sleep(100);
                lock2.lock();
                System.out.println("thread name: " + Thread.currentThread().getName() + "; lock2.lock()");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            lock2.lock();
            System.out.println("thread name: " + Thread.currentThread().getName() + "; lock2.lock()");
            try {
                lock1.lock();
                System.out.println("thread name: " + Thread.currentThread().getName() + "; lock1.lock()");
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });
        thread2.start();

        ThreadUtil.printThreadState(5, 1000, thread1, thread2);
    }
}
