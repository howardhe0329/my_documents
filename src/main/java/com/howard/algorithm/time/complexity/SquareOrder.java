package com.howard.algorithm.time.complexity;

/**
 * 平方阶
 * <p>
 * 常见算法：冒泡排序、选择排序、插入排序等等
 *
 * @author howard he
 * @create 2018/9/28 11:14
 */
public class SquareOrder {

    /**
     * 时间复杂度为 O(n^2)
     * <p>
     * 嵌套循环，所以要用乘法计算复杂度
     * 外层循环执行了n次, 内层循环执行了n次，所以总执行次数为 n * n
     *
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int count = 0;
        // 循环n次
        for (int i = 0; i < n; i++) {
            // 循环n次
            for (int j = 0; j < n; j++) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SquareOrder o = new SquareOrder();
        int result = o.cal(100);
        System.out.println(result);
        System.out.println("n^2: " + (int) Math.pow(100, 2));
    }
}
