package com.howard.bithacks;

/**
 * Created by howard on 16/10/9.
 */
public class BitOperation {

    public static void main(String[] args) {
        // 非负数右移31位一定为0
        System.out.println(65535 >> 31);
        // 0 取反为 -1
        System.out.println(~0);
        // 负数右移31位一定为0xffff = -1
        System.out.println(-65535 >> 31);
        // -1 & x = x
        System.out.println(-1 & 1);

        //判断数字的奇偶性, x 与 1 进行 '与运算' ,如果结果为1是奇数, 为0是偶数
//        int x = 8;
//        int result = x & 1;
//        System.out.format("数字 {%d} 的奇偶性结果为: %d", x, result);

        //判断数字x的第n位是否是1
//        int x = 9;
//        int n = 2;
//        int result = x & (1 << n);
//        System.out.format("数字 {%d} 第n=%d位结果为: %d \n", x, n, result);

        //将数字x的第n位设置为1
//        int x = 120;
//        int n = 2;
//        int result = x | (1 << n);
//        System.out.format("数字 {%d} 将第n=%d位设置为1，其结果为: %d\n", x, n, result);

        //将数字x的第n位设置为0
//        int x = 124;
//        int n = 2;
//        int result = x & ~(1 << n);
//        System.out.format("数字 {%d} 将第n=%d位设置为0，其结果为: %d\n", x, n, result);

//        int x = 124;
//        System.out.println(Integer.toBinaryString(x));
//        int n = 5;
//        int result = x ^ (1 << (n - 1));
//        System.out.println(Integer.toBinaryString(result));
//        System.out.format("数字 {%d} 将第n=%d位转换，其结果为: %d\n", x, n, result);

        System.out.println(Integer.valueOf("00101010", 2));
        int x = 42;
        System.out.println(Integer.toBinaryString(x));
        int result = x & (x - 1);
        System.out.println(Integer.toBinaryString(result));
        System.out.format("数字 {%d} 将最右边的1设置为0，其结果为: %d\n", x, result);
    }
}
