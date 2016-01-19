package com.howard.juc.common;

/**
 * Created by howard on 16/1/18.
 */
public class ThreadUtil {

    private ThreadUtil() {

    }

    public static String printThreadName() {
        return printThreadName(null);
    }

    public static String printThreadName(Thread thread) {
        if(thread == null) {
            thread = Thread.currentThread();
        }
        return String.format("Thread name: %s", thread.getName());
    }

    public static void printThreadState(Integer loop, Integer millis, Thread... threads) {
        if(loop == null || loop == 0) {
            loop = 1;
        }
        boolean isSleep = true;
        if(millis == null || millis == 0) {
            isSleep = false;
        }
        for (int i = 0; i < loop; i++) {
            for (Thread thread : threads) {
                System.out.println("Thread name: " + thread.getName() + ", state: " + thread.getState().name());
            }
            if (isSleep)
                SleepUtil.sleep(millis);
        }
    }
}
