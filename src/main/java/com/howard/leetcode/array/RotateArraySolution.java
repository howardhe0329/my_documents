package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 旋转数组
 * <p>
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 *
 * @author howard he
 * @create 2018/10/15 11:43
 */
public class RotateArraySolution {

    /**
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0 || k == nums.length) {
            return;
        }
        // 指针i
        int i = 0;
        // 指针index
        int index = 0;
        // i++，当i >= nums长度时，就退出循环
        while (i < nums.length) {
            // 记录当前的index指针
            int current = index;
            do {
                // 算出替换下一个元素的索引位置。指以k的步长去遍历数组
                int next = (current + k) % nums.length;
                // 然后用index 和 next 位置上的元素替换。
                swap(nums, index, next);
                // 将当前的current的指针指向next
                current = next;
                i ++;
                // 指以k的步长去遍历数组; 当index 和 current 相等时就退出循环, 代表着遍历整个数组
            } while (index != current);
            // 如果i < nums.length；代表着数组中还有元素为遍历到。所以需要将index指针右移一位。
            index++;
        }
    }

    /**
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        k %= nums.length;
        // O(n/2)
        reverse(nums, 0, nums.length -1);
        // O(k/2)
        reverse(nums, 0, k - 1);
        // O((n - k) / 2)
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 反转数组
     *
     * @param nums 数组
     * @param left 数组的起始位置
     * @param right 数组的结束位置
     */
    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left ++;
            right --;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6, 7};
        int k = 3;
        RotateArraySolution solution = new RotateArraySolution();
        solution.rotate1(nums, k);
        System.out.println(Arrays.toString(nums));

    }
}
