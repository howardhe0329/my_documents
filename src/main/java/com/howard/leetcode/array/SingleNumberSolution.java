package com.howard.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author howard he
 * @create 2018/11/6 10:57
 */
public class SingleNumberSolution {

    public int singleNumber(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                hashSet.remove(nums[i]);
            } else {
                hashSet.add(nums[i]);
            }
        }
        return hashSet.toArray(new Integer[0])[0];
    }

    /**
     *  a ^ 0 = a
     *  a ^ a = 0
     * @param nums
     * @return
     */
    public int singleNumber1(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1, 2};
        SingleNumberSolution solution = new SingleNumberSolution();
        int result = solution.singleNumber(nums);
        System.out.println(result);
        result = solution.singleNumber1(nums);
        System.out.println(result);

        System.out.println(2 ^ 0);
        System.out.println(2 ^ 1);
    }
}
