package com.howard.bithacks;

/**
 * @author howard he
 * @create 2018/9/27 17:14
 */
public class BitTest {


    public static int tableSizeFor(int c) {
        int n = c -1;
        System.out.println(n);
        System.out.println(n >>> 1);
        n |= n >>> 1;
        System.out.println(n);
        System.out.println(n >>> 1);
        n |= n >>> 2;
        System.out.println(n);
        System.out.println(n >>> 1);
        n |= n >>> 4;
        System.out.println(n);
        System.out.println(n >>> 1);
        n |= n >>> 8;
        System.out.println(n);
        System.out.println(n >>> 1);
        n |= n >>> 16;
        System.out.println(n);
        return n - (n >> 1);
    }

    public static void main(String[] args) {
        int result = BitTest.tableSizeFor(15);
        System.out.println(result);


        System.out.println("1 | 3: " + (1 | 3));
        System.out.println("1 & 3: " + (1 & 3));
        System.out.println("1 ^ 3: " + (1 ^ 3));
    }
}
