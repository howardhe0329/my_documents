package com.howard.leetcode.review;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 只出现一次数字
 *
 * @author howard he
 * @create 2018/11/12 14:19
 */
public class SingleNumberSolution {

    /**
     *  n ^ 0 = n
     *  n ^ n = 0
     *
     *  n ^ a = b
     *  b ^ a = n
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public int singleNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            total += nums[i];
        }
        Iterator<Integer> iterator = set.iterator();
        int uniqueTotal = 0;
        while (iterator.hasNext()) {
            uniqueTotal += iterator.next();
        }
        return uniqueTotal * 2 - total;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 1, 1, 4, 4};
        SingleNumberSolution solution = new SingleNumberSolution();
        int result = solution.singleNumber1(nums);
        System.out.println(result);

    }
}
