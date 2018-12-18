package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 1. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 3. 针对所有的元素重复以上的步骤，除了最后一个。
 * 4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * 分析
 * 对于长度为 n 的数组，冒泡排序需要经过 n(n-1)/2 次比较，最坏的情况下，即数组本身是倒序的情况下，需要经过 n(n-1)/2 次交换，所以其
 *
 * 冒泡排序的算法时间平均复杂度为O(n²)。空间复杂度为 O(1)。
 *
 * 总结
 * 1. 冒泡排序的算法时间平均复杂度为O(n²)。
 * 2. 空间复杂度为 O(1)。
 * 3. 冒泡排序为稳定排序。
 *
 * @author howard he
 * @create 2018/3/28 16:26
 */
public class BubbleSort {

    private void sort(int[] origin) {
        int len = origin.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len - i; j++) {
                if (origin[j - 1] > origin[j]) {
                    int temp = origin[j];
                    origin[j] = origin[j - 1];
                    origin[j - 1] = temp;
                }
            }
            System.out.format("第%d次遍历，结果%s \n", i + 1, Arrays.toString(origin));
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
