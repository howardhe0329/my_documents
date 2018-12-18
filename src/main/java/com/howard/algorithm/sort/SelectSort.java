package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * 1. 从待排序序列中，找到关键字最小的元素；起始假定第一个元素为最小
 * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
 * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复1，2步，直到排序结束。
 *
 *
 * 总结：
 * 1. 选择排序的算法时间平均复杂度为O(n²)。
 * 2. 选择排序空间复杂度为 O(1)。
 * 3. 选择排序为不稳定排序。
 *
 * @author howard he
 * @create 2018/3/28 16:49
 */
public class SelectSort {

    public void sort(int[] origin) {
        int len = origin.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (origin[minIndex] > origin[j]) {
                    minIndex = j;
                }
            }
            swap(origin, i, minIndex);
            System.out.format("第%d次遍历，结果%s \n", i + 1, Arrays.toString(origin));
        }

    }

    /**
     * 交换
     * @param origin
     * @param a
     * @param b
     */
    private void swap(int[] origin, int a, int b) {
        int temp = origin[a];
        origin[a] = origin[b];
        origin[b] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        SelectSort bubbleSort = new SelectSort();
        bubbleSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
