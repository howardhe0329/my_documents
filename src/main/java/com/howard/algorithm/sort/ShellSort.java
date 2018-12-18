package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author howard he
 * @create 2018/4/2 11:26
 */
public class ShellSort {

    public void sort(int[] origin) {
        int len = origin.length;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        ShellSort hillSort = new ShellSort();
        hillSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
