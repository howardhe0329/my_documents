package com.howard.leetcode.search.binary;

/**
 * 寻找旋转排序数组中的最小值 II
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 *
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 *
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 * @author howard he
 * @create 2018/11/1 10:15
 */
public class CycleSortedArrayFindMinTwoSolution {

    private int count = 0;

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 有序的
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[left] == nums[mid] && nums[left] == nums[right] && left != mid) {
                int res = findMin(nums, left, right);
                if (res >= mid) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else if (nums[left] <= nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.printf("left: %d; right: %d\n", left, right);
        return nums[right];
    }

    private int findMin(int[] nums, int left, int right) {
        while (left + 1 < right) {
            count++;
            int mid = left + ((right - left) >> 1);
            if (nums[left] == nums[mid] && nums[left] == nums[right] && left != mid) {
                int a = findMin(nums, left, mid);
                if (a != -1) {
                    return a;
                }
                return findMin(nums, mid, right);
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {10, 10, 10, 10, 10, 1, 10, 10, 10, 10, 10, 10, 10};
//        int[] nums = new int[] {2, 2, 2, 0, 1};
//        int[] nums = new int[] {10, 1, 10, 10, 10};
        CycleSortedArrayFindMinTwoSolution solution = new CycleSortedArrayFindMinTwoSolution();
        int result = solution.findMin(nums);
        System.out.println(result);
        System.out.printf("count: %d; length: %d", solution.count, nums.length);
    }
}
