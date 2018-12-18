package com.howard.leetcode.search.binary;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author howard he
 * @create 2018/10/30 09:57
 */
public class SearchRangeSolution {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[] {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (target == nums[left]) {
            return left;
        }
        if (target == nums[right]) {
            return right;
        }
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (target >= nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (target == nums[right]) {
            return right;
        }
        if (target == nums[left]) {
            return left;
        }
        return -1;
    }

    private int findFirst1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                if (mid == 0 || target != nums[mid - 1]) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int findLast1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || target != nums[mid + 1]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 8, 10};
        int target = 9;
        SearchRangeSolution solution = new SearchRangeSolution();
        int[] result = solution.searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
