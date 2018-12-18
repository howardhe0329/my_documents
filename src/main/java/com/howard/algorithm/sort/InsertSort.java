package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * 思想：
 * 把数组分成两部分，一个是已排序的部分，另一个是未排序的部分的。
 *
 * 1. 从第一个元素开始，该元素可以认为已经被排序
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4. 重复步骤 3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置后
 * 6. 重复步骤 2~5
 *
 * 总结：
 * 1. 插入排序的算法时间平均复杂度为O(n²)。
 * 2. 插入排序空间复杂度为 O(1)。
 * 3. 插入排序为稳定排序。
 * 4. 插入排序对于近乎有序的数组来说效率更高，插入排序可用来优化高级排序算法(如归并排序和快速排序）
 *
 * @author howard he
 * @create 2018/3/28 17:14
 */
public class InsertSort {

    public void sort(int[] origin) {
        int len = origin.length;
        for (int i = 1; i < len; i++) {
            // 待排序的元素
            int tmp = origin[i];
            int index = i;
            for (int j = i - 1; j >= 0 && origin[j] > tmp; j--) {
                origin[j + 1] = origin[j];
                index = j;
            }
            if (index < i) {
                origin[index] = tmp;
            }
            System.out.format("第%d次遍历，结果%s \n", i, Arrays.toString(origin));
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        InsertSort insertSort = new InsertSort();
        insertSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
