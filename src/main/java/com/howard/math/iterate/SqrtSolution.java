package com.howard.math.iterate;

/**
 * 平方根算法
 *
 * @author howard he
 * @create 2018-12-14 09:33
 */
public class SqrtSolution {

    /**
     * 利用二分法计算出一个整数的平方根，可能是准确值或近似值
     * @param n 整数
     * @param threshold 阀值
     * @param maxTry 循环次数
     * @return 返回平方根
     */
    private double sqrt(int n, double threshold, int maxTry) {
        if (n < 1) {
            return -1.0;
        }
        double left = 1;
        double right = (double)n;
        int count = 0;
        while (left < right) {
            double mid = left + (right - left) / 2;
            double square = mid * mid;
            if (Math.abs(square / n - 1) < threshold) {
                return mid;
            } else if (square > n) {
                right = mid;
            } else {
                left = mid;
            }
            count++;
            if (count == maxTry) {
                return mid;
            }
        }
        return -1.0;
    }


    public static void main(String[] args) {
        int n = 10;
        double threshold = 1e-12;
        int maxTry = 10;
        SqrtSolution solution = new SqrtSolution();
        double result = solution.sqrt(n, threshold, maxTry);
        System.out.println(result);

        System.out.println(Math.sqrt(10));
    }
}
