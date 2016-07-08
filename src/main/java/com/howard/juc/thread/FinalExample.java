package com.howard.juc.thread;

/**
 * Created by howard on 16/5/31.
 */
public class FinalExample {

    int i;
    final int j;

    public FinalExample() {
        this.j = 2;
        this.i = 1;
    }

    static FinalExample obj;

    public static void writer() {
        obj = new FinalExample();
    }

    public static void reader() {
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
        System.out.println("a=" + a + ", b=" + b);
    }
}
