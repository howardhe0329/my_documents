package com.howard.leetcode.movezero;

import java.util.Arrays;

/**
 * 移动零
 *
 * @author howard he
 * @create 2018/9/29 14:40
 */
public class Solution {

    /**
     * 时间复杂度 O(n)
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        // 定义是0的计数器
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            // 是0的话 计数器加1
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }
            // 如果无0的元素则不需要位移
            if (zeroCount == 0) {
                continue;
            }
            // 位移
            nums[i - zeroCount] = nums[i];
        }
        // 把最后几个元素设置成0
        for (int i = nums.length - zeroCount; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums));
        Solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
