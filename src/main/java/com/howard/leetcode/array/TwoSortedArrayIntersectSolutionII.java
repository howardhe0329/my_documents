package com.howard.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 两个已排序的数组的交集II
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author howard he
 * @create 2018/11/12 15:31
 */
public class TwoSortedArrayIntersectSolutionII {

    public int[] intersect(int[] nums1, int[] nums2) {
        int p1 = 0;
        int p2 = 0;
        List<Integer> resultList = new ArrayList<>();
        while (true) {
            if (p1 >= nums1.length || p2 >= nums2.length) {
                break;
            }
            if (nums1[p1] > nums2[p2]) {
                p2 ++;
            } else if (nums1[p1] < nums2[p2]) {
                p1 ++;
            } else {
                resultList.add(nums1[p1]);
                p1 ++;
                p2 ++;
            }
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[] {2, 4, 6, 7};
        TwoSortedArrayIntersectSolutionII solution = new TwoSortedArrayIntersectSolutionII();
        int[] result = solution.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
