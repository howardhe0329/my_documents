package com.howard.juc.perf;

/**
 * Created by howard on 16/5/19.
 */
public class SingleThread {

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.sum();
    }
}
