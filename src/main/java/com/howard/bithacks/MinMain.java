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
        System.out.format("十进制: %d, 二进制: %s \n", t1, Integer.toBinaryString(t1));


        System.out.println(1 >> 1);

        System.out.println((0 + 10) >>> 1);


        int OP_READ = 1 << 0;
        int OP_WRITE = 1 << 1;
        int OP_CONNECT = 1 << 2;
        int OP_ACCEPT = 1 << 3;
        System.out.println(String.format("OP_READ: %s; OP_WRITE: %s; OP_CONNECT: %s; OP_ACCEPT: %s", OP_READ, OP_WRITE, OP_CONNECT, OP_ACCEPT));
    }
}
