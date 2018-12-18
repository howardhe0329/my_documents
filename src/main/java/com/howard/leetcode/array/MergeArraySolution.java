package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 合并有序数组
 *
 * @author howard he
 * @create 2018/9/30 13:29
 */
public class MergeArraySolution {

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int i = m + n - 1;
        while (i >= 0) {
            if ((nums1[p1] > nums2[p2] && p1 >= 0)
                || (p1 >= 0 && p2 < 0)) {
                nums1[i] = nums1[p1];
                p1 --;
            }
            if ((nums2[p2] > nums1[p1] && p2 >= 0)
                || (p1 < 0 && p2 >= 0)) {
                nums1[i] = nums2[p2];
                p2 --;
            }
            i --;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        MergeArraySolution mergeArraySolution = new MergeArraySolution();
        mergeArraySolution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
