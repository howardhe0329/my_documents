package com.howard.math.binary;

/**
 * 数制转换
 *
 * @author howard he
 * @create 2018-12-10 10:42
 */
public class MyConvertSolution {

    /**
     * 十进制转换成二进制
     * @param decimalSource
     * @return
     */
    public String decimalToBinary(int decimalSource) {
        int tmp = decimalSource >> 1;
        StringBuilder sb = new StringBuilder();
        sb.append(decimalSource & 1);
        while (tmp > 0) {
            sb.append(tmp & 1);
            tmp = tmp >> 1;
        }
        return sb.reverse().toString();
    }

    /**
     * 十进制转换成二进制
     * @param decimalSource
     * @return
     */
    public String decimalToBinary1(int decimalSource) {
        StringBuilder sb = new StringBuilder();
        while (decimalSource > 0) {
            int bit = decimalSource % 2;
            sb.append(bit);
            decimalSource = decimalSource / 2;
        }
        return sb.reverse().toString();
    }

    /**
     * 二进制转换成十进制
     * @param binarySource
     * @return
     */
    public int binaryToDecimal(String binarySource) {

        return 0;
    }

    public static void main(String[] args) {
        MyConvertSolution solution = new MyConvertSolution();

        int d = 8;
        System.out.println(String.format("十进制数 %d 转换成二进制是 %s", d, solution.decimalToBinary(d)));
        System.out.println(String.format("十进制数 %d 转换成二进制是 %s", d, solution.decimalToBinary1(d)));

        String b = "110010";
        System.out.println(String.format("二进制数 %s 转换成十进制是 %d", b, solution.binaryToDecimal(b)));


        System.out.println(9 >> 1);
        System.out.println(9 | 1);
        System.out.println(5 & 1);
        System.out.println(9 ^ 1);

    }
}
