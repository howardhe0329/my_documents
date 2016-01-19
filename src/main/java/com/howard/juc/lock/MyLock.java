package com.howard.juc.lock;

import com.howard.juc.common.ThreadUtil;

/**
 * 自已实现锁功能
 * Created by howard on 16/1/19.
 */
public class MyLock {

    /**
     * 是否已获取锁
     */
    private boolean isLocked = false;

    /**
     * 获取锁, 并进入wait
     */
    public synchronized void lock() throws InterruptedException {
        // 如果isLocked 为true, 代表其它线程已获取锁, 则当前线程进入WAITING状态.
        while (isLocked) { //用while 是防止假唤醒.
            //将当前线程挂起
            wait();
        }
        isLocked = true;
        System.out.println(ThreadUtil.printThreadName() + ", i get lock");
    }

    /**
     * 释放锁, 通知其它线程可以获取锁.
     */
    public synchronized void unlock() {
        // 释放锁
        isLocked = false;
        // 唤醒其它线程
        notify();
        System.out.println(ThreadUtil.printThreadName() + ", i release lock");
    }

}
