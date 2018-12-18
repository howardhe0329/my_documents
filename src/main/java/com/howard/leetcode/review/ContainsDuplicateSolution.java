package com.howard.leetcode.review;

import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复
 *
 * @author howard he
 * @create 2018/11/12 14:09
 */
public class ContainsDuplicateSolution {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> duplicate = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(!duplicate.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,3,3,4,3,2,4,2};
        ContainsDuplicateSolution solution = new ContainsDuplicateSolution();
        boolean result = solution.containsDuplicate(nums);
        System.out.println(result);
    }
}
