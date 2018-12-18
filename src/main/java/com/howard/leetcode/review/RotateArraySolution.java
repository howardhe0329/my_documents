package com.howard.leetcode.review;

import java.util.Arrays;

/**
 * 旋转数组
 *
 * @author howard he
 * @create 2018/11/9 15:19
 */
public class RotateArraySolution {

    public void rotate(int[] nums, int k) {
        if (k > nums.length) {
            k = k % nums.length;
        }
        if (k < 1 || k == nums.length) {
            return;
        }
        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int current = i;
            do {
                int next = (current + k) % nums.length;
                swap(nums, i, next);
                current = next;
                count++;
            } while (i != current);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5,6};
        int k = 2;
        RotateArraySolution solution = new RotateArraySolution();
        solution.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
