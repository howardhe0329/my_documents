package com.howard.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 用法
 * await();
 * signal()或者signalAll();
 * Created by howard on 16/1/16.
 */
public class ConditionTest {

    public static void main(String[] args) {
        Thread.currentThread().setName("main----");
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread thread1 = new Thread(() ->  {
            lock.lock();
            try {
                System.out.println("thread name: " + Thread.currentThread().getName() + ", waiting a signal...");
                // block current thread. release lock?
                condition.await();
                System.out.println("thread name: " + Thread.currentThread().getName() + "; ok, i take a signal...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        thread1.setName("my-thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            lock.lock();
            System.out.println("thread name: " + Thread.currentThread().getName() + ", acquire a lock");
            try {
                Thread.sleep(2000);
                condition.signal();
                System.out.println("thread name: " + Thread.currentThread().getName() + "; i send a signal..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        thread2.setName("my-thread2");
        thread2.start();

        for (int i = 0; i < 3; i++) {
            System.out.println("Thread name: " + thread1.getName() + ", state: " + thread1.getState().name());
            System.out.println("Thread name: " + thread2.getName() + ", state: " + thread2.getState().name());
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
