package com.howard.leetcode.strings;

/**
 * 颠倒整数
 *
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 *
 * @author howard he
 * @create 2018/11/13 16:26
 */
public class ReverseIntegerSolution {

    public int reverse(int x) {
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        int left = 0;
        if (x < 0) {
            left = 1;
        }
        int right = chars.length - 1;
        while (left < right) {
            swap(chars, left++, right--);
        }
        long res = Long.parseLong(new String(chars));
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

    private void swap(char[] array, int a, int b) {
        char tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        int x = 1534236469;
        ReverseIntegerSolution solution = new ReverseIntegerSolution();
        int result = solution.reverse(x);
        System.out.println(result);

    }
}
