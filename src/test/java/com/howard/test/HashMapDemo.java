package com.howard.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author howard he
 * @create 2018/11/2 15:03
 */
public class HashMapDemo {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("n: " + n);
        n |= n >>> 1;
        System.out.println("n: " + n);
        n |= n >>> 2;
        System.out.println("n: " + n);
        n |= n >>> 4;
        System.out.println("n: " + n);
        n |= n >>> 8;
        System.out.println("n: " + n);
        n |= n >>> 16;
        System.out.println("n: " + n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(1 << 4); // 16
        System.out.println(1 << 30); // int max
        System.out.println(Integer.MAX_VALUE);
        System.out.println(15 >>> 1);
        System.out.println(15 >>> 2);
        System.out.println(15 >>> 4);
        System.out.println(15 >>> 8);
        System.out.println(15 >>> 16);

        System.out.println(HashMapDemo.tableSizeFor(10000));


        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("1", 2);
    }
}
