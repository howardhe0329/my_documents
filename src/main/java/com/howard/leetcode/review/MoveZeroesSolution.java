package com.howard.leetcode.review;

import java.util.Arrays;

/**
 * 移动零
 *
 * @author howard he
 * @create 2018/11/12 17:22
 */
public class MoveZeroesSolution {

    public void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[zeroIndex++] = nums[i];
                count++;
            }
        }
        System.out.println(count);
        for (int i = zeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes1(int[] nums) {
        // 定义是0的计数器
        int zeroCount = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 是0的话 计数器加1
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }
            // 位移
            nums[i - zeroCount] = nums[i];
            count++;
        }
        System.out.println(count);
        // 把最后几个元素设置成0
        for (int i = nums.length - zeroCount; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0, 3, 12};
        MoveZeroesSolution solution = new MoveZeroesSolution();
        solution.moveZeroes1(nums);
        System.out.println(Arrays.toString(nums));
    }
}
