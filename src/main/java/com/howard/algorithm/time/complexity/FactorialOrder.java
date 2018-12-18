package com.howard.algorithm.time.complexity;

/**
 * 阶乘阶
 *
 * @author howard he
 * @create 2018/9/28 11:27
 */
public class FactorialOrder {

    /**
     * 时间复杂度为 O(2^n)
     *
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int count = 0;
        int temp = n;
        for (int i = 1; i <= n; i++) {
            temp *= i;
            count++;
        }
        System.out.println(temp);
        return count;
    }

    public static void main(String[] args) {
        FactorialOrder o = new FactorialOrder();
        int result = o.cal(5);
        System.out.println(result);
    }
}
