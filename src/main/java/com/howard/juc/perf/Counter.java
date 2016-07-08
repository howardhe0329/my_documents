package com.howard.juc.perf;

/**
 * Created by howard on 16/5/19.
 */
public class Counter {

    public synchronized void sumLock() {
        sum();
    }


    public void sum() {
        long limit = 500000000;
        long count = 0;
        long statTime = System.currentTimeMillis();
        for (int i = 0; i < limit; i++) {
            count++;
        }
        System.out.println(System.currentTimeMillis() - statTime);
    }
}
