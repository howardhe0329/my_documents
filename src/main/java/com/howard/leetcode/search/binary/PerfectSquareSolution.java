package com.howard.leetcode.search.binary;

/**
 * 有效的完全平方数
 *
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 示例 1：
 *
 * 输入：16
 * 输出：True
 * 示例 2：
 *
 * 输入：14
 * 输出：False
 *
 * @author howard he
 * @create 2018/10/31 17:30
 */
public class PerfectSquareSolution {

    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid == 0) {
                return false;
            }
            long guress = (long) mid * mid;
            System.out.println(guress);
            if (guress == num) {
                return true;
            } else if (guress > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        PerfectSquareSolution solution = new PerfectSquareSolution();
        boolean result = solution.isPerfectSquare(num);
        System.out.println(result);
    }
}
