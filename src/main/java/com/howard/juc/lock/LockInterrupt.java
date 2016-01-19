package com.howard.juc.lock;

import com.howard.juc.common.SleepUtil;
import com.howard.juc.common.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如果某线程未获取到锁,进入等待队列时被中断,将继续等待处理.
 * Created by howard on 16/1/19.
 */
public class LockInterrupt {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(ThreadUtil.printThreadName() + ", get lock");
                SleepUtil.sleep(10000);
            } finally {
                lock.unlock();
                System.out.println(ThreadUtil.printThreadName() + ", release lock");
            }
        }, "thread-1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(ThreadUtil.printThreadName() + ", get lock");

            } finally {
                lock.unlock();
                System.out.println(ThreadUtil.printThreadName() + ", release lock");
            }
        }, "thread-2");
        thread2.start();

        SleepUtil.sleep(3000);
        thread2.interrupt();
        System.out.println(ThreadUtil.printThreadName(thread2) + ", interrupt");

        ThreadUtil.printThreadState(5, 1000, thread1, thread2, Thread.currentThread());
    }
}
