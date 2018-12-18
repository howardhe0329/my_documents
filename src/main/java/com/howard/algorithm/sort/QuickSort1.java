package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 双路快速排序
 *
 * @author howard he
 * @create 2018/4/4 15:14
 */
public class QuickSort1 {

    public void sort(int[] origin) {
        quick(origin, 0, origin.length - 1);
    }

    /**
     * 递归
     * @param origin 数组
     * @param left 开始位置
     * @param right 结束位置
     */
    private void quick(int[] origin, int left, int right) {
        // 基线条件，当left >= right 退出递归
        if (left >= right) {
            return;
        }
        // 获取分区点，并将小于pivot的放到数组左边，大于pivot的放到右边，将pivot放到中间
        int p = partition(origin, left, right);
        // 分治思想
        // 左边
        quick(origin, left, p - 1);
        // 右边
        quick(origin, p + 1, right);
    }

    /**
     * 双路快速排序,采用双指针-对撞指针的技巧
     * 把小于pivot 放到数组的左边，大于pivot放到数组的右边
     * @param origin 数组
     * @param left 起始位置
     * @param right 结束位置
     * @return pivot所在的位置
     */
    private int partition(int[] origin, int left, int right) {
        // 为了提高效率，减少造成快速排序的递归树不均匀的概率，
        // 对于一个数组，每次随机选择的数为当前 partition 操作中最小最大元素的可能性为 1/n
        int randomIndex = (int) ((Math.random() * (right - left + 1)) + left);
        swap(origin, left, randomIndex);

        int pivot = origin[left];
        // 指针i指向left + 1
        int i = left + 1;
        // 指针j指向right
        int j = right;
        // i++ 和 j-- 直到i > j时退出循环。对撞指针技巧
        while (i <= j) {
            // i <= j 并且origin[i]小于pivot时，i++
            while (i <= j && origin[i] < pivot) {
                i++;
            }
            // i <= j 并且origin[i]大于pivot时，j++
            while (i <= j && origin[j] >= pivot) {
                j--;
            }
            // 如果i大于j那就么退出循环
            if (i > j) {
                break;
            }
            // i和j上的元素交换
            swap(origin, i, j);
        }
        // 记得要把pivot放在分区点的位置
        swap(origin, left, j);
        return j;
    }

    private void swap(int[] origin, int a, int b) {
        int temp = origin[a];
        origin[a] = origin[b];
        origin[b] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};
        System.out.println(Arrays.toString(a));
        QuickSort1 quickSort1 = new QuickSort1();
        quickSort1.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
