package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 找出无序数组中的第 K个最大元素
 *
 * @author howard he
 * @create 2018/9/30 10:14
 */
public class FindKthLargestQuickSolution {

    /**
     * 利用快速排序算法的思想
     *
     * 分析：
     * 从数组A中随机找出一个元素X，将分为两部分Aa和Ab。Aa中的元素大于等于X，Ab中的元素小于X。
     * 这时有两种情部：
     *
     * 1. 如果Aa中的元素个数小于k, 则Ab 中的第k - Aa.length个元素即为第K大数。
     * 2. 如果Aa中的元素个数大于k, 则返回Aa中的第k大数。
     *
     * 时间复杂度近似为O(n)
     * 空间复杂度O(1)
     *
     * @param nums 数组
     * @param k 第k大元素位置
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int index = quickSort(nums, 0, nums.length - 1, k);
        return nums[index + 1];
    }

    /**
     * 快速排序
     * @param nums
     */
    private static int quickSort(int[] nums, int start, int end, int k) {
        // 基线条件
        if (start >= end) {
            return start;
        }
        int p = partition(nums, start, end);
        // 递归
        if (k < p) {
            return quickSort(nums, start, p - 1, k);
        } else if (k > p){
            return quickSort(nums, p + 1, end, k);
        }
        return p;
    }

    private static int partition(int[] nums, int start, int end) {
        int randomIndex = (int)(Math.random() * (end - start + 1)) + start;
        int pivot = nums[randomIndex];
        nums[randomIndex] = nums[start];
        nums[start] = pivot;
        int left = start + 1;
        int right = end;
        // 双指针
        while (true) {
            // 移动左指针
            while (left <= end && nums[left] >= pivot) {
                left ++;
            }
            // 移动右指针
            while (right > start && nums[right] <= pivot ) {
                right --;
            }
            if (left > right) {
                break;
            }
            swap(nums, left, right);
            left ++;
            right --;
        }
        swap(nums, start, right);
        return right;
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int result = FindKthLargestQuickSolution.findKthLargest(nums, k);
        System.out.println(result);
        System.out.println(Arrays.toString(nums));
    }
}
