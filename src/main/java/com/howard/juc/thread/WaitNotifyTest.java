package com.howard.juc.thread;

import com.howard.juc.common.SleepUtil;
import com.howard.juc.common.ThreadUtil;

/**
 *
 *   Thread name: thread1, state: WAITING
     Thread name: thread2, state: TIMED_WAITING
     Thread name: thread1, state: WAITING
     Thread name: thread2, state: TIMED_WAITING
     Thread name: thread1, state: BLOCKED
     Thread name: thread2, state: RUNNABLE
     Thread name: thread2, notify other wait thread
     Thread name: thread1, state: BLOCKED
     Thread name: thread2, state: TIMED_WAITING
     Thread name: thread2, i sleep 1000ms
     Thread name: thread1, i get signal.
     Thread name: thread1, state: TERMINATED
     Thread name: thread2, state: TERMINATED
 * Created by howard on 16/1/19.
 */
public class WaitNotifyTest {

    static class Signal {

        private boolean state;

        public void getState() {
            synchronized (this) {
                while (!state) {
                    try {
                        wait();
                        System.out.println(ThreadUtil.printThreadName() + ", i get signal.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state = false;
            }
        }

        public void setState() {
            synchronized(this) {
                state = true;
                SleepUtil.sleep(2000);
                notify();
                System.out.println(ThreadUtil.printThreadName() + ", notify other wait thread");
                SleepUtil.sleep(1000);
                System.out.println(ThreadUtil.printThreadName() + ", i sleep 1000ms");
            }
        }
    }

    public static void main(String[] args) {
        Signal signal = new Signal();
        Thread thread1 = new Thread(() -> {
            signal.getState();
        }, "thread1");
        thread1.start();
        Thread thread2 = new Thread(() -> {
            signal.setState();
        }, "thread2");
        thread2.start();

        ThreadUtil.printThreadState(5, 1000, thread1, thread2);
    }
}
