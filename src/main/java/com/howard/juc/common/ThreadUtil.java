package com.howard.juc.common;

/**
 * Created by howard on 16/1/18.
 */
public class ThreadUtil {

    private ThreadUtil() {

    }

    public static String printThreadName() {
        return String.format("Thread name: %s", Thread.currentThread().getName());
    }
}
