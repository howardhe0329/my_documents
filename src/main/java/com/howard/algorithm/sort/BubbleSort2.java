package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序改进，设置标志位pos
 *
 * @author howard he
 * @create 2018/4/3 16:07
 */
public class BubbleSort2 {

    private void sort(int[] origin) {
        int len = origin.length;
        int i = len - 1;
        while (i > 0) {
            int pos = 0;
            for (int j = 0; j < i; j++) {
                if (origin[j] > origin[j + 1]) {
                    pos = j;
                    int temp = origin[j + 1];
                    origin[j + 1] = origin[j];
                    origin[j] = temp;
                }
            }
            i = pos;
            System.out.format("第%d次遍历，pos是%d, 结果%s \n", len - i, pos, Arrays.toString(origin));
        }

    }

    public static void main(String[] args) {
        int[] a = new int[] {3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        BubbleSort2 bubbleSort = new BubbleSort2();
        bubbleSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
