package com.howard.algorithm.power;

/**
 * @author howard he
 * @create 2018/9/27 11:52
 */
public class Solution {

    public static int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    /**
     * 判断一个数是不是2的n次方
     * @param n 整数n
     * @return
     */
    public boolean isPowerOf(int n) {
        return (n & n -1) == 0;
    }

    public static void main(String[] args) {
        // 如果一个数x是2的n次访，那么x & x - 1 = 0
        System.out.println("4 & 3: " + (4 & 3));
        System.out.println("5 & 4: " + (5 & 4));
        System.out.println("10 & 9: " + (10 & 9));
        System.out.println("16 & 15: " + (16 & 15));
        // 2的n次方的二进制是 10 -> 2   1000-> 4    10000 -> 8  100000 -> 16

        System.out.println(Math.log(16) + "; " + Math.sqrt(16));

        System.out.println(2 << 3);
        System.out.println(3 << 2);
        System.out.println(4 << 1);
        System.out.println(5 << 1);
    }
}
