package com.howard.leetcode.sortcolor;

import java.util.Arrays;

/**
 * 颜色分类
 *
 * @author howard he
 * @create 2018/9/29 17:46
 */
public class Solution {

    public static void sortColors(int[] nums) {
        int[] countArray = new int[3];
        for (int i = 0; i < nums.length; i++) {
            countArray[nums[i]] ++;
        }
        int idx = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                nums[idx++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 0, 2, 1, 1, 0};
        System.out.println(Arrays.toString(nums));
        Solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
