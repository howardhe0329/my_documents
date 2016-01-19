package com.howard.juc.lock;

import com.howard.juc.common.ThreadUtil;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by howard on 16/1/19.
 */
public class LockSupportTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(ThreadUtil.printThreadName() + ", thread start");
            LockSupport.park();
            System.out.println(ThreadUtil.printThreadName() + ", thread end");
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            System.out.println(ThreadUtil.printThreadName() + ", thread start");
            LockSupport.unpark(thread1);
            System.out.println(ThreadUtil.printThreadName() + ", thread end");
        });
        thread2.start();
    }
}
