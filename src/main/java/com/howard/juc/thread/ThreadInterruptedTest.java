package com.howard.juc.thread;

import com.howard.juc.common.SleepUtil;

import java.util.concurrent.TimeUnit;

/**
 * Thread interrupted
 * Created by howard on 16/6/1.
 */
public class ThreadInterruptedTest {

    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        SleepUtil.sleep(TimeUnit.SECONDS, 5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        SleepUtil.sleep(TimeUnit.SECONDS, 2);
    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtil.sleep(TimeUnit.SECONDS, 100);
            }
        }
    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                //loop
            }
        }
    }
}
