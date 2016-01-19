package com.howard.juc.lock;

import com.howard.juc.common.ThreadUtil;

/**
 * Created by howard on 16/1/19.
 */
public class MyReentrantLock {

    /**
     * 是否已获取锁
     */
    private boolean isLocked = false;
    /**
     * 获取锁的线程
     */
    private Thread lockedThread = null;
    /**
     *
     */
    private int lockedCount = 0;

    /**
     * 获取锁, 并进入wait
     */
    public synchronized void lock() throws InterruptedException {
        Thread currThread = Thread.currentThread();
        // 如果isLocked 为true, 代表其它线程已获取锁, 则当前线程进入WAITING状态.
        while (isLocked && lockedThread != currThread) { //用while 是防止假唤醒.
            //将当前线程挂起
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedThread = currThread;
        System.out.println(ThreadUtil.printThreadName() + ", i get lock");
    }

    /**
     * 释放锁, 通知其它线程可以获取锁.
     */
    public synchronized void unlock() {
        if(Thread.currentThread() == lockedThread)
            lockedCount--;
        if(lockedCount == 0) {
            // 释放锁
            isLocked = false;
            // 唤醒其它线程
            notify();
        }
        System.out.println(ThreadUtil.printThreadName() + ", i release lock");
    }
}
