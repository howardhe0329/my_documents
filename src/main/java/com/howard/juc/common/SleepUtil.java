package com.howard.juc.common;

import java.util.concurrent.TimeUnit;

/**
 * Created by howard on 16/1/18.
 */
public class SleepUtil {

    private SleepUtil() {

    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(TimeUnit unit, long millis) {
        try {
            unit.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
