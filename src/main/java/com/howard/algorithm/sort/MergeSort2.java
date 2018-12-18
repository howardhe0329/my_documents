package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序迭代实现
 *
 *
 * @author howard he
 * @create 2018/4/3 15:36
 */
public class MergeSort2 {

    private int times;

    public void sort(int[] origin) {
        int len = origin.length;
        for (int i = 1; i <= len; i += i) {
            System.out.format("i执行了第%d次\n", i);
            for (int j = 0; j + i < len; j += i + i) {
                System.out.format("\tj执行了第%d次\n", j);
                merge(origin, j, j + i - 1, Math.min(j + i + i - 1, len - 1 ));
            }
        }
    }

    private void merge(int[] origin, int left, int mid, int right) {
        times = times + 1;
        int[] temp = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
            temp[i - left] = origin[i];
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                origin[k] = temp[j - left];
                j++;
            } else if (j > right) {
                origin[k] = temp[i - left];
                i++;
            } else if (temp[i - left] < temp[j - left]) {
                origin[k] = temp[i - left];
                i++;
            } else {
                origin[k] = temp[j - left];
                j++;
            }
        }
        System.out.format("merge执行了 %d 次, left: %d, mid: %d, right: %d; temp array: %s; origin array: %s\n"
                , times, left, mid, right, Arrays.toString(temp), Arrays.toString(origin));
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 6};
        System.out.println(Arrays.toString(a));
        MergeSort2 mergeSort2 = new MergeSort2();
        mergeSort2.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
