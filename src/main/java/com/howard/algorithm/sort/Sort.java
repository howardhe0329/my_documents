package com.howard.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author howard he
 * @create 2018/10/16 16:23
 */
public class Sort {

    /**
     * 插入排序
     * @param nums
     */
    public void insert(int[] nums) {
        // 遍历n-1次
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int index = i;
            // 遍历 n - 1次
            for (int j = i - 1; j >= 0 && nums[j] > tmp; j--) {
                // 位移
                nums[j + 1] = nums[j];
                index = j;
            }
            if (index < i) {
                // 替换
                nums[index] = tmp;
            }
        }
    }

    /**
     * 冒泡排序
     * @param nums
     */
    public void bubble(int[] nums) {
        boolean swapped = true;
        int len = nums.length;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < len; i++) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                    swapped = true;
                }
            }
            len --;
        }
    }

    /**
     * 选择排序
     * @param nums
     */
    public void select(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    /**
     * 归并排序
     * @param nums
     */
    public void mergeSort(int[] nums) {
        merge(nums, 0, nums.length - 1);
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     */
    private void merge(int[] nums, int start, int end) {
        // 基线条件 ，递归退出的条件
        if (start >= end) {
            return;
        }
        int mid = (end - start + 1) / 2 + start;
        merge(nums, start, mid - 1);
        merge(nums, mid, end);
        // 合并数组
        merge(nums, start, mid, end);
    }

    /**
     *
     * @param nums
     * @param start
     * @param mid
     * @param end
     */
    private void merge(int[] nums, int start, int mid, int end) {
        int left = start;
        int right = mid;
        int[] tmp = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            if (left < mid && right <= end) {
                if (nums[left] <= nums[right]) {
                    tmp[i - start] = nums[left];
                    left++;
                } else {
                    tmp[i - start] = nums[right];
                    right++;
                }
            } else if (left < mid) {
                tmp[i - start] = nums[left];
                left++;
            } else if (right <= end) {
                tmp[i - start] = nums[right];
                right++;
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[i + start] = tmp[i];
        }
    }

    /**
     * 快速排序
     * @param nums
     */
    public void quickSort(int[] nums) {
        quick(nums, 0, nums.length - 1);
    }

    private void quick(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = partition(nums, start, end);
        quick(nums, start, p - 1);
        quick(nums, p + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        int randomIndex = (int)((end - start + 1) * Math.random()) + start;
        if (randomIndex != start) {
            swap(nums, start, randomIndex);
        }
        int pivot = nums[start];
        int j = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] < pivot) {
                swap(nums, j + 1, i);
                j++;
            }
        }
        swap(nums, start, j);
        return j;
    }

    /**
     * 交换
     * @param nums
     * @param a
     * @param b
     */
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {5, 3, 9, 4};
        Sort sort = new Sort();
        sort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
