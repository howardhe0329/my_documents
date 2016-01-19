package com.howard.juc.lock;

import com.howard.juc.common.SleepUtil;

import java.util.concurrent.locks.Condition;


/**
 * Created by howard on 16/1/19.
 */
public class WaitQueueTest {

    public static void main(String[] args) {
        ExtendsReentrantLock lock = new ExtendsReentrantLock();
        Condition condition = lock.newCondition();

        Thread thread1 = new Thread(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + "; lock start");
            lock.lock();
            try {
                condition.await();
                lock.getWaitingThreads(condition).forEach(thread -> {
                    System.out.println(thread.getName());
                });
                System.out.println("thread name: " + Thread.currentThread().getName() + "; get lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + "; lock start");
            lock.lock();
            try {
                SleepUtil.sleep(10000);
                System.out.println("thread name: " + Thread.currentThread().getName() + "; get lock");
            } finally {
                lock.unlock();
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + "; lock start");
            lock.lock();
            try {
                SleepUtil.sleep(10000);
                condition.signal();
                System.out.println("thread name: " + Thread.currentThread().getName() + "; get lock ");
            } finally {
                lock.unlock();
            }
        });
        thread3.start();
        Thread[] threads = new Thread[]{thread1, thread2, thread3};
        for (int i = 0; i < 5; i++) {
            for (Thread thread : threads) {
                System.out.println("Thread name: " + thread.getName() + ", state: " + thread.getState().name());
            }
            if(i == 2) {
                lock.getQueuedThreads().forEach(thread4 -> {
                    System.out.println(thread4.getName());
                });
            }
            SleepUtil.sleep(1000);
        }
    }
}
