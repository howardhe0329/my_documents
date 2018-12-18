package com.howard.leetcode.review;

/**
 * 从排序数组中删除复复项
 *
 * @author howard he
 * @create 2018/11/9 11:29
 */
public class RemoveDuplicatesSolution {

    public int removeDuplicates(int[] nums) {
        int index = -1;
        if (nums == null) {
            return index;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        RemoveDuplicatesSolution solution = new RemoveDuplicatesSolution();
        int result = solution.removeDuplicates(nums);
        System.out.println(result);
        for (int i = 0; i < result; i++) {
            System.out.printf("%d ", nums[i]);
        }
    }
}
