package com.howard.leetcode.array;

/**
 * 寻找数组的中心索引
 * <p>
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * <p>
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * <p>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 示例 1:
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * <p>
 * 示例 2:
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 * <p>
 * 说明:
 * 1. nums 的长度范围为 [0, 10000]。
 * 2. 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 *
 * @author howard he
 * @create 2018/10/10 16:22
 */
public class PivotIndexSolution {

    /**
     * 时间复杂度为 O(n)
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum - nums[0] == 0) {
            return 0;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            sum -= nums[i];
            if (leftSum == sum - nums[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // -1,-1,-1,-1,-1,0
        // -1,-1,0,0,-1,-1
        // -1,-1,-1,0,1,1
        // 1, 7, 3, 6, 5, 6
        // -1,-1,0,-1,-1,0
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        PivotIndexSolution solution = new PivotIndexSolution();
        int result = solution.pivotIndex(nums);
        System.out.println(result);
    }
}
