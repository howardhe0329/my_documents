package com.howard.algorithm.time.complexity;

/**
 * 立方阶
 *
 * @author howard he
 * @create 2018/9/28 11:19
 */
public class CubicOrder {

    /**
     * 时间复杂度为 O(n^3)
     * <p>
     * 嵌套循环，所以要用乘法计算复杂度
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
                // 循环n次
                for (int k = 0; k < n; k++) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CubicOrder o = new CubicOrder();
        int result = o.cal(100);
        System.out.println(result);
        System.out.println("n^2: " + (int) Math.pow(100, 3));
    }
}
