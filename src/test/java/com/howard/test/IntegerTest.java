package com.howard.test;

/**
 * test
 *
 * @author howard he
 * @create 2018/11/5 15:51
 */
public class IntegerTest {

    public static int hash(Object key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (16 - 1);
    }

    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        System.out.printf("key: %d; hash: %d\n", i1, IntegerTest.hash(i1));
        Integer i2 = new Integer(2);
        System.out.printf("key: %d; hash: %d\n", i2, IntegerTest.hash(i2));
        Integer i3 = new Integer(1987);
        System.out.printf("key: %d; hash: %d\n", i3, IntegerTest.hash(i3));
        Integer i4 = new Integer(1986);
        System.out.printf("key: %d; hash: %d\n", i4, IntegerTest.hash(i4));
        Integer i5 = new Integer(1000);
        System.out.printf("key: %d; hash: %d\n", i5, IntegerTest.hash(i5));
    }
}
