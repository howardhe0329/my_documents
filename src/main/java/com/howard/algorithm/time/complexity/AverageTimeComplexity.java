package com.howard.algorithm.time.complexity;

/**
 * 平均情况时间复杂度
 *
 * @author howard he
 * @create 2018/9/28 14:38
 */
public class AverageTimeComplexity {

    /**
     * 从数组中查找元素x所在的位置
     * <p>
     * 复杂度分板
     * <p>
     * 最好情况：O(1)
     * 假如元素x正好在第一个位置，那么时间复杂为O(1)
     * <p>
     * 最坏情况：O(n)
     * 假如元素x正在最后一个位置，那么时间复杂为O(n)
     * <p>
     * 平均情况：O(n)
     *
     * @param array 数组
     * @param x     元素 x
     * @return index -1 表示没有找到
     */
    public int index(int[] array, int x) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
