package com.howard.juc.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by howard on 16/1/15.
 */
public class BoundedBuffer {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final String[] items = new String[10];
    int putptr, takeptr, count;

    public void put(String value) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = value;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public boolean put(String value, TimeUnit unit, long timeout) throws InterruptedException {
        long nanos = TimeUnit.SECONDS.toNanos(10);
        lock.lock();
        try {
            while (count == items.length) {
                if(nanos <= 0) {
                    return false;
                }
                nanos = notFull.awaitNanos(nanos);
            }
            items[putptr] = value;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            String res = items[takeptr];
            if(++takeptr == items.length)
                takeptr = 0;
            --count;
            notFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BoundedBuffer buffer = new BoundedBuffer();
        for (int i = 0; i < 11; i++) {
            try {
                buffer.put(String.valueOf(i), TimeUnit.SECONDS,  10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long elaeped = System.currentTimeMillis() - start;
        System.out.println("exit, execute time: " + elaeped);

    }
}
