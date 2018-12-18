package com.howard.leetcode.search.binary;

/**
 * pow
 *
 * @author howard he
 * @create 2018/10/31 11:11
 */
public class PowSolution {

    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1.0 / x;
            n = -n;
        }
        if (n == Integer.MIN_VALUE) {
            if (x == 1 || x == -1) {
                return 1.0;
            } else {
                return 0.0;
            }
        }
        double result = 1.0;
        for (double i = x; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                result *= i;
            }
            i *= i;
        }
        return result;
    }



    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        double x = -1.00000;
        int n = -2147483648;
        PowSolution solution = new PowSolution();
        double result = solution.myPow(x, n);
        System.out.println(result);
        System.out.println(Math.pow(x, n));
    }
}
