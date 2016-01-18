package com.howard.juc.thread;

import com.howard.juc.common.SleepUtil;
import com.howard.juc.common.ThreadUtil;

/**
 * Created by howard on 16/1/18.
 */
public class ThreadBlockedState {

    public static void main(String[] args) {
        Object object1 = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                System.out.println(ThreadUtil.printThreadName() + ", i get lock...");
                SleepUtil.sleep(3000);
            }
        }, "thread1");
        thread1.start();
        Thread thread2 = new Thread(() -> {
            synchronized (object1) {
                System.out.println(ThreadUtil.printThreadName() + ", i get lock");
            }
        }, "thread2");
        thread2.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread name: " + thread1.getName() + ", state: " + thread1.getState().name());
            System.out.println("Thread name: " + thread2.getName() + ", state: " + thread2.getState().name());
            SleepUtil.sleep(1000);
        }
    }
}
