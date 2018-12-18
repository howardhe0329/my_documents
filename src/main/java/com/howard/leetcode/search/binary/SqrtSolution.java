package com.howard.leetcode.search.binary;

/**
 * x的平方根
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 *
 * @author howard he
 * @create 2018/10/29 14:27
 */
public class SqrtSolution {

    /**
     * 利用二分查找算法来求出一个数的平方根
     * <p>
     * 注意边界条件和整数溢出的问题
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 注意溢出的问题。
            long tmp = (long) mid * mid;
            if (tmp == x) {
                return mid;
            } else if (tmp > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return right;
    }

    public static void main(String[] args) {
        int x = 2147395599;
        SqrtSolution solution = new SqrtSolution();
        int result = solution.mySqrt(x);
        System.out.println(result);
        System.out.println(Math.sqrt(x));
    }
}
