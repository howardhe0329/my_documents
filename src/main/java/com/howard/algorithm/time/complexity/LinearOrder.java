package com.howard.algorithm.time.complexity;

/**
 * 线性阶
 *
 * @author howard he
 * @create 2018/9/28 10:56
 */
public class LinearOrder {

    /**
     *
     * 时间复杂度为 O(n)
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int a = 1;
        // step 2
        int count = 0;
        // 循环遍历n次
        for (int i = 0; i < n; i++) {
            count ++;
        }
        return count;
    }

    /**
     * 时间复杂度为 O(m +n)
     * @param m
     * @param n
     * @return
     */
    public int cal(int m, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count ++;
        }
        for (int i = 0; i < m; i++) {
            count ++;
        }
        return count;
    }

    /**
     * 时间复杂度为 O(m * n)
     * 受参数m和参数n的数据规模影响
     * @param m 数据规模m
     * @param n 数据规模 n
     * @return
     */
    public int cal1(int m, int n) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LinearOrder o = new LinearOrder();
        int result = o.cal(100);
        System.out.println(result);
    }
}
