package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * @author howard he
 * @create 2018/9/27 14:06
 */
public class BubbleSort3 {

    private void sort(int[] origin) {
        int len = origin.length;
        int lasIndex = 0;
        int sortBorder = len - 1;
        for (int i = 0; i < len - 1; i++) {
            boolean isStored = true;
            for (int j = 1; j < sortBorder; j++) {
                if (origin[j - 1] > origin[j]) {
                    int temp = origin[j];
                    origin[j] = origin[j - 1];
                    origin[j - 1] = temp;
                    isStored = false;
                    lasIndex = j;
                }
            }
            sortBorder = lasIndex;
            System.out.format("第%d次遍历，结果%s \n", i + 1, Arrays.toString(origin));
            if (isStored) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {3, 4, 2, 1, 5, 6, 7, 8, 9};
//        int[] a = new int[] {3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        BubbleSort3 bubbleSort = new BubbleSort3();
        bubbleSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
