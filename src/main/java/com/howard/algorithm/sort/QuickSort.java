package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 单路快速排序
 *
 * 应用最多的排序算法
 *
 * 1. 假设数组为 arr[l...r] 假设指定数值为数组第一个元素 int v = arr[l]，假设 j 标记
 *  为比 v 小的最后一个元素， 即 arr[j+1] > v。当前考察的元素为 i 则有arr[l + 1 ... j] < v ， arr[j+1,i) >= v 如上图所示。
 * 2. 假设正在考察的元素值为 e ，e >= v 的时候我们只需交将不动，直接 i++ 去考察下一个元素。
 * 3. 当e < v 由上述假设我们需要将 e 放在<v 的部分 ，此时我们只需将 arr[j] 和 arr[i] 交换一下位置即可。
 * 4. 最后一个元素考察完成以后，我们再讲 arr[l]和 arr[j]调换一下位置就可以了。
 * 5. 上述遍历完成以后  arr[l + 1 ... j] < v ， arr[j+1,i) >= v 就满足了，接下来我们只需要递归的去考察 arr[l + 1 ... j] 和 arr[j+1,r] 即可。
 *
 *
 * @author howard he
 * @create 2018/4/2 14:32
 */
public class QuickSort {

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
        int p = partion(origin, left, right);
        // 分治思想
        // 左边
        quick(origin, left, p - 1);
        // 右边
        quick(origin, p + 1, right);
    }

    /**
     * 单路快速排序
     * 把小于pivot 放到数组的左边，大于pivot放到数组的右边
     * @param origin 数组
     * @param left 起始位置
     * @param right 结束位置
     * @return pivot所在的位置
     */
    private int partion(int[] origin, int left, int right) {
        // 为了提高效率，减少造成快速排序的递归树不均匀的概率，
        // 对于一个数组，每次随机选择的数为当前 partition 操作中最小最大元素的可能性为 1/n
        int randomIndex = (int) (Math.random() * (right - left + 1) + left);
        swap(origin, left, randomIndex);
        int pivot = origin[left];
        // 创建j指针指向left位置
        int j = left;
        // 遍历数组 left + 1 ~ right
        for (int i = left + 1; i <= right; i++) {
            // 小于pivot 那么 j + 1和i进行交换，并j加1；否则继续
            if (origin[i] < pivot) {
                swap(origin, j + 1, i);
                j++;
            }
        }
        swap(origin, left, j);
        return j;
    }

    private void swap(int[] origin, int a, int b) {
        int temp = origin[a];
        origin[a] = origin[b];
        origin[b] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 6};
        System.out.println(Arrays.toString(a));
        QuickSort quickSort = new QuickSort();
        quickSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
