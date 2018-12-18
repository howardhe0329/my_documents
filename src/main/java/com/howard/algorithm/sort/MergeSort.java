package com.howard.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 4. 重复步骤3直到某一指针到达序列尾
 * 5. 将另一序列剩下的所有元素直接复制到合并序列尾
 * <p>
 * 总结：
 * <p>
 * 1. 归并排序的算法时间平均复杂度为O(nlog(n))。
 * 2. 归并排序空间复杂度为 O(n)。
 * 3. 归并排序为稳定排序。
 *
 * @author howard he
 * @create 2018/4/2 17:03
 */
public class MergeSort {

    private int times = 0;
    private int splitTimes = 0;

    public void sort(int[] origin) {
        mergeSort(origin, 0, origin.length - 1, false);
    }

    private void mergeSort(int[] origin, int left, int right, boolean isLeft) {
        splitTimes = splitTimes + 1;
        // 分割两个数组的position index
        int mid = (right + left) / 2;
//        System.out.format("分割第%d次，left: %d, mid: %d, right: %d, flag: %s \n", splitTimes, left, mid, right, isLeft);
        if (left >= right) {
            return;
        }
        mergeSort(origin, left, mid, true);
        mergeSort(origin, mid + 1, right, false);

        // 检查是否上一步归并完的数组是否有序，如果有序则直接进行下一次归并
        if (origin[mid] <= origin[mid + 1]) {
            return;
        }
        // 将两边的元素进行归并排序
        merge(origin, left, mid, right);
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
            // 说明左边已放进数组里
            if (i > mid) {
                origin[k] = temp[j - left];
                j++;
                //说明右边已放进数组里了
            } else if (j > right) {
                origin[k] = temp[i - left];
                i++;
                // 当左半个数组的元素值小于右边数组元素值得时候 赋值为左边的元素值
            } else if (temp[i - left] < temp[j - left]) {
                origin[k] = temp[i - left];
                i++;
                //当左半个数组的元素值大于等于右边数组元素值得时候 赋值为左边的元素值 这样也保证了排序的稳定性
            } else {
                origin[k] = temp[j - left];
                j++;
            }
        }
        System.out.format("merge执行了 %d 次, left: %d, mid: %d, right: %d; temp array: %s; origin array: %s \n"
                , times, left, mid, right, Arrays.toString(temp), Arrays.toString(origin));
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 3, 9, 1, 0, 2, 4, 6, 8, 7, 6};
        System.out.println(Arrays.toString(a));
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
