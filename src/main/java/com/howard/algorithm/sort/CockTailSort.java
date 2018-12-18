package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 鸡尾酒排序算法
 *
 * @author howard he
 * @create 2018/9/27 15:21
 */
public class CockTailSort {

    private void sort(int[] origin) {
        int len = origin.length;
        for (int i = 0; i < len / 2; i++) {
            boolean isStored = true;
            for (int j = i; j < len - i - 1; j++) {
                if (origin[j] > origin[j + 1]) {
                    swap(origin, j, j + 1);
                    isStored = false;
                }
            }
            if (isStored) {
                break;
            }
            isStored = true;
            for (int j = len - i - 1; j > i; j--) {
                if (origin[j] < origin[j - 1]) {
                    swap(origin, j, j - 1);
                    isStored = false;
                }
            }
            if (isStored) {
                break;
            }
            System.out.format("第%d次遍历，结果%s \n", i + 1, Arrays.toString(origin));
        }
    }

    private void swap(int[] origin, int a, int b) {
        int temp = origin[a];
        origin[a] = origin[b];
        origin[b] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[] {3, 5, 9, 1, 0, 2, 4, 6, 8, 7};
//        int[] a = new int[] {2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println(Arrays.toString(a));
        CockTailSort cockTailSort = new CockTailSort();
        cockTailSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
