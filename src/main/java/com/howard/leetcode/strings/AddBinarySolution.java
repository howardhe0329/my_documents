package com.howard.leetcode.strings;

/**
 * 二进制求和
 *
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * @author howard he
 * @create 2018/10/12 11:28
 */
public class AddBinarySolution {

    public String addBinary(String a, String b) {
        int p = 0;
        int aLength = a.length();
        int bLength = b.length();
        int maxLength = aLength >= bLength ? aLength : bLength;
        char[] resultChars = new char[maxLength];
        char one = '0';
        while (p < maxLength) {
            int aIndex = aLength - 1 - p;
            int bIndex = bLength - 1 - p;
            char aChar = '0';
            char bChar = '0';
            boolean move = false;
            if (aIndex >= 0 && bIndex >= 0) {
                aChar = a.charAt(aIndex);
                bChar = b.charAt(bIndex);
            } else if (aIndex >= 0) {
                aChar = a.charAt(aIndex);
            } else if (bIndex >= 0) {
                bChar = b.charAt(bIndex);
            }
            char result = '0';
            if (aChar != bChar) {
                result = '1';
            } else if (aChar == '1') {
                move = true;
            }
            if (result != one) {
                result = '1';
            } else if (one == '1') {
                result = '0';
                move = true;
            }
            resultChars[maxLength - p - 1] = result;
            if (move) {
                one = '1';
            } else {
                one = '0';
            }
            p++;
        }
        if (one == '1') {
            return "1".concat(String.valueOf(resultChars));
        }
        return String.valueOf(resultChars);
    }

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        AddBinarySolution solution = new AddBinarySolution();
        String result = solution.addBinary(a, b);
        System.out.println(result);
    }
}
