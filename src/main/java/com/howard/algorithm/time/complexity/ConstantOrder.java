package com.howard.algorithm.time.complexity;

/**
 * 常量阶
 *
 * @author howard he
 * @create 2018/9/28 10:25
 */
public class ConstantOrder {

    /**
     * 该方法随着n的增长，代码执行的次数并没有增长。那么我们称为常量阶
     * 时间复杂度为 O(1)
     * @param n
     * @return
     */
    public int cal(int n) {
        // step 1
        int a = 1;
        // step 2
        int b = 0;
        // step 3
        return a + b + n;
    }

    public static void main(String[] args) {
        ConstantOrder o = new ConstantOrder();
        o.cal(100);
        o.cal(10);
    }
}
