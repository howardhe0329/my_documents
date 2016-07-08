package com.howard.juc.perf;

/**
 * Created by howard on 16/5/19.
 */
public class SingleThreadLock {

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.sumLock();
    }
}
