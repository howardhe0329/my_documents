package com.howard.leetcode.array;

import java.util.*;

/**
 * 两个数组的交集2
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
 * @create 2018/11/12 15:00
 */
public class TwoArrayIntersectSolutionII {

    /**
     * 如果num1和nums2的长度相差很大的情况下，那么应该是将长度最长的那个数组转化成HashMap形式来存储。
     * 然后在遍历长度小的数组找出交集。
     *
     * 分析：
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> resultList;
        if (nums1.length >= nums2.length) {
            resultList = intersect(nums2, convert(nums1));
        } else {
            resultList = intersect(nums1, convert(nums2));
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }

    private Map<Integer, Integer> convert(int[] nums) {
        HashMap<Integer, Integer> resultMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer val = resultMap.get(nums[i]);
            if (val != null) {
                resultMap.put(nums[i], val.intValue() + 1);
            } else {
                resultMap.put(nums[i], 1);
            }
        }
        return resultMap;
    }

    private List<Integer> intersect(int[] nums, Map<Integer, Integer> map) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            Integer val = map.get(nums[i]);
            if (val != null && val.intValue() > 0) {
                map.put(nums[i], val.intValue() - 1);
                resultList.add(nums[i]);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[] {1, 1};
        TwoArrayIntersectSolutionII solution = new TwoArrayIntersectSolutionII();
        int[] result = solution.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
