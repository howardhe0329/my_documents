package com.howard.math.mod;

import java.util.Random;

/**
 * 哈希加盐
 *
 * @author howard he
 * @create 2018-12-13 10:42
 */
public class HashSaltTest {

    public int hash(int x) {
        return (x + new Random().nextInt(100)) % 100;
    }

    public static void main(String[] args) {
        HashSaltTest test = new HashSaltTest();
        System.out.println(test.hash(1));
        System.out.println(test.hash(2));
        System.out.println(test.hash(3));
        System.out.println(test.hash(101));
        System.out.println(test.hash(102));
        System.out.println(test.hash(103));


        System.out.println(8 & 7);
        System.out.println(9 & 8);
        System.out.println(16 & 15);

        System.out.println(Integer.MAX_VALUE >> 31);
    }
}
