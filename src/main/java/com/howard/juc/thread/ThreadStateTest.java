package com.howard.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * 本代码主要是学习Thread的State的几种状态, 关于状态的详解可以访问:
 *
 * Created by howard on 16/1/16.
 */
public class ThreadStateTest {

    public static void main(String[] args) {
        //create new thread instance, current state is NEW
        Thread thread = new Thread(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + "; starting....");
            int count = 0;
            for (int i = 0; i < 1000000000; i++) {
                count += 1;
            }
            System.out.println("thread name: " + Thread.currentThread().getName() + "; count: " + count);
            try {
                //thread sleep, current state is TIMED_WAITING
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("thread name: " + Thread.currentThread().getName() + " is be interrupted.");
            }
            System.out.println("thread name: " + Thread.currentThread().getName() + "; end....");
        });
        thread.setName("my-thread");
        System.out.println("thread name: " + thread.getName() + "; current state: " + thread.getState().name());
        // start thread, current state is RUNNABLE
        thread.start();
        System.out.println("thread name: " + thread.getName() + "; current state: " + thread.getState().name());
        sleep(TimeUnit.SECONDS, 2);
        System.out.println("thread name: " + thread.getName() + "; current state: " + thread.getState().name());
        // interrupt thread, current state is TERMINATED
        thread.interrupt();
        sleep(TimeUnit.MILLISECONDS, 500);
        System.out.println("thread name: " + thread.getName() + "; current state: " + thread.getState().name());
    }

    private static void sleep(TimeUnit unit, long count) {
        try {
            unit.sleep(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
