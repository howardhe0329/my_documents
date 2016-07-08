package com.howard.juc.thread;

/**
 * Thread yield 让度, 当前线程放弃CPU资源,让其它线程执行
 * Created by howard on 16/5/24.
 */
public class ThreadYieldTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread name: " + Thread.currentThread().getName() + "; i = " + i);
                if( i % 10 == 0) {
                    Thread.yield();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
