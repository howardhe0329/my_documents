package com.howard.test.juc;

/**
 * Created by howard on 16/6/3.
 */
public class BitTest {

    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static int workerCountOf(int c)  { return c & CAPACITY; }

    public static void main(String[] args) {
        System.out.println(MAXIMUM_CAPACITY);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(Integer.SIZE);

        System.out.println("RUNNING: " + RUNNING + "; SHUTDOWN: " + SHUTDOWN + "; STOP: " + STOP + "; TIDYING: " + TIDYING + "; TERMINATED: " + TERMINATED);

        System.out.println(RUNNING | 0);

        System.out.println(workerCountOf(RUNNING | 0));
    }
}
