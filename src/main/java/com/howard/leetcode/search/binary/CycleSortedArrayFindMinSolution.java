package com.howard.leetcode.search.binary;

/**
 * 寻找旋思考排序数组中的最小值
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 * @author howard he
 * @create 2018/10/29 17:14
 */
public class CycleSortedArrayFindMinSolution {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 有序的
            if (nums[left] < nums[right] || left == right) {
                return nums[left];
            }
            int mid = left + ((right - left) >> 1);
            // 有序的部分
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        CycleSortedArrayFindMinSolution solution = new CycleSortedArrayFindMinSolution();
        int result = solution.findMin(nums);
        System.out.println(result);
    }
}
