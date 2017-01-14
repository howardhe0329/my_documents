package com.howard.bithacks;

/**
 * Created by howard on 16/10/10.
 */
public class MinMain {

    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        System.out.println(Math.min(a, b));

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));

        int t1 = a ^ b;
        System.out.format("十进制: %d, 二进制: %s", t1, Integer.toBinaryString(t1));
    }
}
