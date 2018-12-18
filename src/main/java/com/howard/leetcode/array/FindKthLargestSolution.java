package com.howard.leetcode.array;

/**
 * 找出数组的第K个最大元素
 *
 * @author howard he
 * @create 2018/9/30 09:41
 */
public class FindKthLargestSolution {

    /**
     * 时间复杂度受参数nums数组的长度n和参数k的影响，所以复杂度计算是：
     * $O(n * k) = O(kn)$
     *
     * 最好的情况是k为1，那么其实就是最最大的元素。时间复杂度就为$O(n)$
     * 最坏的情况是k为n, 那么其实就是找最小的元素。时间复杂度就是$O(n^2)$
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[maxIndex] < nums[j]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = nums[i];
                nums[i] = nums[maxIndex];
                nums[maxIndex] = temp;
            }
        }
        return nums[k - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,5,6,4};
        int k = 2;
        int result = FindKthLargestSolution.findKthLargest(nums, k);
        System.out.println(result);
    }
}
