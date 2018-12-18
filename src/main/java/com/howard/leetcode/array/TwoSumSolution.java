package com.howard.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 *
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author howard he
 * @create 2018/11/13 09:45
 */
public class TwoSumSolution {

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        // 外层循环
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            if (find < 0) {
                continue;
            }
            // 内层循环
            for (int j = 0; j < nums.length; j++) {
                if (find == nums[j] && i != j) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {};
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        // key 是数组中的元素值，value 是数组元素对应下标位置
        Map<Integer, Integer> dictMap = new HashMap<>(nums.length);
        // 只需要循环一次
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            // dictMap.containsKey(find) 时间复杂度O(1)
            // dictMap.get(find) 时间复杂度O(1)
            if (dictMap.containsKey(find) && i != dictMap.get(find).intValue()) {
                return new int[] {dictMap.get(find).intValue(), i};
            }
            // dictMap.put(nums[i], i) 时间复杂度O(1)
            dictMap.put(nums[i], i);
        }
        return new int[] {};
    }

    /**
     * 对撞指针法
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        // 左指针
        int left = 0;
        // 右指针
        int right = nums.length - 1;
        // 对撞指针
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum > target) {
                right --;
            } else if (twoSum < target) {
                left ++;
            } else {
                return new int[] {left, right};
            }
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 4};
        int target = 6;
        TwoSumSolution solution = new TwoSumSolution();
        int[] result = solution.twoSum1(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
