package com.howard.algorithm.bsearch;

/**
 * 循环有序数组查找
 *
 * 有序数组是一个循环有序数组，比如 4，5，6，1，2，3，如何用二分查找算法找出给定的值
 *
 * @author howard he
 * @create 2018/10/26 16:24
 */
public class CycleArrayBinarySearch {

    /**
     * 循环有序数组的二分查找算法
     *
     * 思路
     * 循环有序数组被分二个子数组时，肯定有一个数组是有序的，另一个数组就是循环有序的。
     * 那么我们可以对有序的数组进行二分查找直到找到指定的元素，如果是循环数组那么就继续分成二个子数组，然后继续查找有序的数组。
     *
     * 假设数组为 nums, 数组的长度为 n, 找到目标元素为 target
     *
     * 1. 取mid索引位置 = (high + low) / 2，然后判断nums[low]和nums[mid]的大小，
     * 如果nums[low] <= mums[mid]，那么证明low到mid之间是有序数组。
     * 如果 nums[low] <= target && target < nums[mid] 那么就证明target 在 low ... mid区间，这时把high指针指向 mid - 1；否则将
     * low指针指向 mid + 1。
     * 如果nums[low] > nums[mid], 那么证明mid到high之间是有序数组。
     * 如果 nums[high] >= target && target > nums[mid] 那么就证明target 在 mid ... high区间，这时把low指针指向 mid + 1; 否则
     * 将 high指针指向 mid - 1。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target == nums[mid]) {
                if (mid <= 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
            // 有序数组在左边
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // 有序数组在右边
                if (nums[high] >= target && target > nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[] {4, 5, 6, 1, 2, 3};
        int[] nums = new int[] {7, 1, 2, 2, 2, 3, 4, 5, 6};
//        int[] nums = new int[] {1, 2, 3, 4, 5, 6};
        int target = 2;
        CycleArrayBinarySearch binarySearch = new CycleArrayBinarySearch();
        int result = binarySearch.search(nums, target);
        System.out.println(result);
    }
}
