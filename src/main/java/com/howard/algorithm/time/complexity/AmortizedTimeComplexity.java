package com.howard.algorithm.time.complexity;

/**
 * 均摊情况时间复杂度
 *
 * @author howard he
 * @create 2018/9/28 15:05
 */
public class AmortizedTimeComplexity {

    /**
     * 数组固定长度
     */
    private int length = 10;
    /**
     * 创建一个长度为10的空数组
     */
    private int[] array = new int[length];
    /**
     * 记录数组中的元素个数
     */
    private int size;

    /**
     * 向数组中添加一个元素
     * <p>
     * 时间复杂度
     * 最好的情况是： O(1)
     *
     * 最坏的情况是：O(n)
     * 当数组空间满了，这时插入数据需要遍历数组并复制
     *
     * 平均情况是：O(1)
     * 有1/n+1的楖率发生数组空间满的情况。所以用加权平均的计算方法就是：
     * 1 * （1/n+1）+ 1 * （1/n+1） + 1 * （1/n+1）... + n * （1/n+1）= n / n + 1 = O(1)
     *
     * 均摊情况是：O(1)
     * O(1)时间复杂度的插入和O(n)时间复杂度的插入，出现的频率是非常有规律的，而且有一定的前后时间序关系，一般都是一个O(n)插入之后紧跟着n-1个O(1)的插入操作，循环往复。
     * 针对这种特殊场景，我们引入一种更加简单的摊还分析方法。
     * @param x 元素
     */
    private void add(int x) {
        int len = array.length;
        // 数组已满，扩充数组大小
        if (size >= len) {
            // 创建新的数组，长度扩大2倍
            int[] newArray = new int[length * 2];
            // 复制数组中的元素
            for (int i = 0; i < len; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            length = length * 2;
        }
        array[size] = x;
        size++;
    }

    public static void main(String[] args) {

    }
}
