package com.howard.algorithm.time.complexity;

/**
 * 线性对数阶
 * <p>
 * 常见的快速排序、归并排序算法都是O(nlogn)
 *
 * @author howard he
 * @create 2018/9/28 10:59
 */
public class LinearLogarithmicOrder {

    /**
     * 时间复杂度为 O(nlogn)
     * <p>
     * 嵌套循环，所以要用乘法计算复杂度
     * 外层循环执行了n次, 内层循环执行了logn次，所以总执行次数为 n * logn
     *
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int count = 0;
        // 循环n次
        for (int i = 0; i < n; i++) {
            int a = 1;
            // 循环执行了log2n次
            while ((a = a << 1) < n) {
                // 循环计数器
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LinearLogarithmicOrder o = new LinearLogarithmicOrder();
        int result = o.cal(100);
        System.out.println(result);
        System.out.println(100 * (int) (Math.log(100) / Math.log(2)));
        System.out.println("n^2: " + (int) Math.pow(100, 2));
    }
}
