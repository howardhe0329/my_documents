package com.howard.bithacks;

/**
 * Created by howard on 16/10/9.
 */
public class BitOpterationMain {

    public static void main(String[] args) {
        System.out.println( 3 >> 1);
        System.out.println( 1 >> 1);

        System.out.println( 1 << 1);
        System.out.println( 3 << 1);

        System.out.println((1 << 3) - 1);

        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.valueOf("111", 2));

        System.out.println(6 >> 0 & 1);


        int a = 3;
        int b = 4;

        a ^= b;
        System.out.format("one a=%d, b=%d\n", a, b);
        b ^= a;
        System.out.format("two a=%d, b=%d\n", a, b);
        a ^= b;
        System.out.format("finish a=%d, b=%d\n", a, b);

    }
}
