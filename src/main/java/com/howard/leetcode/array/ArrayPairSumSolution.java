package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 数组拆分1
 * <p>
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,4,3,2]
 * <p>
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 * 提示:
 * <p>
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 *
 * @author howard he
 * @create 2018/10/15 09:39
 */
public class ArrayPairSumSolution {

    public int arrayPairSum(int[] nums) {
        insertSort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    private void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] >= nums[j - 1]) {
                    break;
                }
                swap(nums, j, j - 1);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,3,1,0,0,6};
        ArrayPairSumSolution solution = new ArrayPairSumSolution();
        int result = solution.arrayPairSum(nums);
        System.out.println(result);
        System.out.println(Arrays.toString(nums));
    }
}
