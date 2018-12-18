package com.howard.algorithm.time.complexity;

/**
 * 指数阶
 *
 * @author howard he
 * @create 2018/9/28 11:22
 */
public class ExponentialOrder {

    /**
     * 时间复杂度为 O(2^n)
     *
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int count = 0;
        // 循环2^n次 1 << n 等价于 2^n
        for (int i = 0; i < (1 << n); i++) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        ExponentialOrder o = new ExponentialOrder();
        int result = o.cal(4);
        System.out.println(result);
        System.out.println("2^n: " + (int) Math.pow(2, 4));
    }

}
