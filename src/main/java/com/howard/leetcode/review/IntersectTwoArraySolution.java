package com.howard.leetcode.review;

import java.util.*;

/**
 * 两个数组的交集II
 *
 * @author howard he
 * @create 2018/11/20 10:37
 */
public class IntersectTwoArraySolution {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        int p1 = 0;
        int p2 = 0;
        List<Integer> resultList = new ArrayList<>();
        while (p1 < nums1.length || p2 < nums2.length) {
            if (p1 < nums1.length && p2 < nums2.length) {
                if (nums1[p1] == nums2[p2]) {
                    resultList.add(nums1[p1]);
                    p1 ++;
                    p2 ++;
                } else if (nums1[p1] > nums2[p2]) {
                    p2 ++;
                } else {
                    p1 ++;
                }
            } else if (p2 < nums2.length) {
                p2 ++;
            } else if (p1 < nums1.length) {
                p1 ++;
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 3};
        int[] nums2 = new int[]{2, 2};
        IntersectTwoArraySolution solution = new IntersectTwoArraySolution();
        int[] result = solution.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
