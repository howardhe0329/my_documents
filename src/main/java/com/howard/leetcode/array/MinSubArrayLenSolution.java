package com.howard.leetcode.array;

/**
 * 长度最小的子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的<b>连续</b>子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例：
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * <p>
 * 进阶：
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * @author howard he
 * @create 2018/9/30 17:08
 */
public class MinSubArrayLenSolution {

    /**
     * 暴力破解
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            for (int j = i; j < nums.length; j++) {
                tmp += nums[j];
                if (tmp >= s) {
                    if (minLen > j - i + 1) {
                        minLen = j - i + 1;
                    }
                    break;
                }
            }
        }
        if (minLen == nums.length - 1) {
            return 0;
        }
        return minLen;
    }

    /**
     * 窗口滑动
     *
     * 时间复杂度 O(n)
     *
     * 两个指针来控制窗口大小
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int s, int[] nums) {
        // 指针1
        int left = 0;
        // 指针2
        int right = 0;
        int sumAll = 0;
        int minLength = nums.length + 1;
        while (left < nums.length) {
            if (right < nums.length && sumAll < s) {
                sumAll += nums[right];
                right++;
            } else {
                sumAll -= nums[left];
                left++;
            }
            if (sumAll >= s && minLength > right - left) {
                minLength = right - left;
            }
        }
        if (minLength == nums.length + 1) {
            return 0;
        }
        return minLength;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int s = 7;
        MinSubArrayLenSolution solution = new MinSubArrayLenSolution();
        int result = solution.minSubArrayLen1(s, nums);
        System.out.println(result);
    }
}
