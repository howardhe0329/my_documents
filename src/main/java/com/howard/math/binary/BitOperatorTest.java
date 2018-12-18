package com.howard.math.binary;

/**
 * 位操作
 *
 * @author howard he
 * @create 2018-12-10 10:00
 */
public class BitOperatorTest {

    /**
     * 左移操作
     *
     * @param num 十进制整数
     * @param m   移动位数
     * @return
     */
    public static int leftShift(int num, int m) {
        return num << m;
    }

    /**
     * 右移
     *
     * @param num 十进制整数
     * @param m   移动位数
     * @return
     */
    public static int rightShift(int num, int m) {
        return num >>> m;
    }

    /**
     * 二进制按位"或"操作
     *
     * @param num1 第一个数字
     * @param num2 第二个数字
     * @return 二进制按位"或"的结果
     */
    private static int or(int num1, int num2) {
        return (num1 | num2);
    }

    /**
     * 二进制按"与"操作
     *
     * @param num1 第一个数字
     * @param num2 第二个数字
     * @return 二进制按位"与"的结果
     */
    private static int and(int num1, int num2) {
        return (num1 & num2);
    }

    /**
     * 取反
     * @param num 数字
     * @return
     */
    private static int na(int num) {
        return ~num;
    }

    /**
     * 二进制按"异或"操作
     *
     * @param num1 第一个数字
     * @param num2 第二个数字
     * @return 二进制按"异或"的结果
     */
    private static int xor(int num1, int num2) {
        return (num1 ^ num2);
    }

    public static void main(String[] args) {
        int num = 53;
        int m = 1;

        System.out.println(String.format("数字 %d 的二进制向左移 %d 位是 %d", num, m, BitOperatorTest.leftShift(num, m)));
        System.out.println(String.format("数字 %d 的二进制向右移 %d 位是 %d", num, m, BitOperatorTest.rightShift(num, m)));

        System.out.println();

        m = 3;
        System.out.println(String.format("数字 %d 的二进制向左移 %d 位是 %d", num, m, BitOperatorTest.leftShift(num, m)));
        System.out.println(String.format("数字 %d 的二进制向右移 %d 位是 %d", num, m, BitOperatorTest.rightShift(num, m)));

        System.out.println();

        int a = 53;
        int b = 35;

        System.out.println(String.format("数字 %d(%s) 和 数字 %d(%s) 的按位'或'结果是 %d(%s)", a, BigIntegerTest.decimalToBinary(a), b, BigIntegerTest.decimalToBinary(b), BitOperatorTest.or(a, b), BigIntegerTest.decimalToBinary(BitOperatorTest.or(a, b))));
        System.out.println(String.format("数字 %d(%s) 和 数字 %d(%s) 的按位'与'结果是 %d(%s)", a, BigIntegerTest.decimalToBinary(a), b, BigIntegerTest.decimalToBinary(b), BitOperatorTest.and(a, b), BigIntegerTest.decimalToBinary(BitOperatorTest.and(a, b))));
        System.out.println(String.format("数字 %d(%s) 和 数字 %d(%s) 的按位'异或'结果是 %d(%s)", a, BigIntegerTest.decimalToBinary(a), b, BigIntegerTest.decimalToBinary(b), BitOperatorTest.xor(a, b), BigIntegerTest.decimalToBinary(BitOperatorTest.xor(a, b))));


        int c = 6;
        System.out.println(String.format("数字 %d 的原码是：%s. 反码是 %s", c, Integer.toBinaryString(c), BigIntegerTest.decimalToBinary(c)));
        int d = -6;
        System.out.println(String.format("数字 %d 的原码是：%s. 反码是 %s", d, Integer.toBinaryString(d), Integer.toBinaryString(d)));
    }

}
