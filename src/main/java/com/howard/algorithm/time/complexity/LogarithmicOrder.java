package com.howard.algorithm.time.complexity;

/**
 * 对数阶
 *
 * 典型的二分查找、分治算法的复杂度为 O(logn)
 *
 * @author howard he
 * @create 2018/9/28 10:32
 */
public class LogarithmicOrder {

    /**
     *
     * 时间复杂度为 O(logn)
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int a = 1;
        // step 2
        int count = 0;
        // 循环执行了 log2n 次, PS：a << 1 左位移操作，等价于 a * 2
        while ((a = a << 1) < n) {
            // 循环计数器
            count ++;
        }
        return count;
    }

    public static void main(String[] args) {
        LogarithmicOrder o = new LogarithmicOrder();
        int result = o.cal(100);
        System.out.println(result);
        System.out.println((int)(Math.log(100) / Math.log(2)));
    }
}
