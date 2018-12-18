package com.howard.leetcode.array;

/**
 * 最大连续1的个数
 *
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 *
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author howard he
 * @create 2018/10/15 11:20
 */
public class FindMaxConsecutiveOnesSolution {

    /**
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        // 慢指针
        int count = 0;
        // 记录最大数
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count ++;
                continue;
            }
            if (count > maxCount) {
                maxCount = count;
            }
            count = 0;
        }
        if (count > maxCount) {
            maxCount = count;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,0,1,1,1};
        FindMaxConsecutiveOnesSolution solution = new FindMaxConsecutiveOnesSolution();
        int result = solution.findMaxConsecutiveOnes(nums);
        System.out.println(result);
    }
}
