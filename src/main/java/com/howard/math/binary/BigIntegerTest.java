package com.howard.math.binary;

import java.math.BigInteger;

/**
 * 二进制和十进制转换
 *
 * @author howard he
 * @create 2018-12-10 09:25
 */
public class BigIntegerTest {

    /**
     * 十进制转换成二进制
     * @param decimalSource
     * @return
     */
    public static String decimalToBinary(int decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource));
        return bi.toString(2);
    }

    public static int binaryToDecimal(String binarySource) {
        BigInteger bi = new BigInteger(binarySource, 2);
        return Integer.parseInt(bi.toString());
    }

    public static void main(String[] args) {
        int a = 53;
        String b = "110101";
        System.out.println(String.format("数字 %d 的二进制是 %s", a, BigIntegerTest.decimalToBinary(a)));
        System.out.println(String.format("数字 %s 的十进制是 %d", b, BigIntegerTest.binaryToDecimal(b)));
    }
}
