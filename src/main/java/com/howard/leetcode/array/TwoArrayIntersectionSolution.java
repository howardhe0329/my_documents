package com.howard.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 两个数组的交集
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author howard he
 * @create 2018/11/1 11:30
 */
public class TwoArrayIntersectionSolution {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> hashSet1 = new HashSet<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            hashSet1.add(nums1[i]);
        }
        Set<Integer> hashSet2 = new HashSet<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            if (hashSet1.contains(nums2[i])) {
                hashSet2.add(nums2[i]);
            }
        }
        int[] result = new int[hashSet2.size()];
        int i = 0;
        Iterator<Integer> iterator = hashSet2.iterator();
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i ++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        TwoArrayIntersectionSolution solution = new TwoArrayIntersectionSolution();
        int[] result = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
