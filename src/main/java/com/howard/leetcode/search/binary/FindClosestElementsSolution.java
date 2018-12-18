package com.howard.leetcode.search.binary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 找到K个最接近的元素
 *
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5], k=4, x=3
 * 输出: [1,2,3,4]
 *
 *
 * 示例 2:
 *
 * 输入: [1,2,3,4,5], k=4, x=-1
 * 输出: [1,2,3,4]
 *
 *
 * 说明:
 *
 * k 的值为正数，且总是小于给定排序数组的长度。
 * 数组不为空，且长度不超过 104
 * 数组里的每个元素与 x 的绝对值不超过 104
 *
 *
 * @author howard he
 * @create 2018/10/30 15:30
 */
public class FindClosestElementsSolution {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> resultList = new ArrayList<>(arr.length);
        int left = 0;
        int right = arr.length;
        if (arr.length == k) {
            copy(arr, left, right, resultList);
            return resultList;
        }
        if (x <= arr[left]) {
            copy(arr, left, k, resultList);
            return resultList;
        } else if (x >= arr[right - 1]) {
            copy(arr, right - k, right, resultList);
            return resultList;
        }
        int index = findFirstLessThen(arr, x);
        System.out.println(index);
        if (index == 0) {
            right = k;
        } else if (index == arr.length - 1) {
            left = right - k;
        } else {
            left = index - k < 0 ? 0 : index - k;
            right = index + k > arr.length - 1 ? arr.length - 1 : index + k;
            while (k - 1 < right - left) {
                if (x - arr[left] <= arr[right] - x) {
                    right --;
                } else {
                    left ++;
                }
            }
        }
        copy(arr, left, right + 1, resultList);
        return resultList;
    }

    private void copy(int[] arr, int left, int right, List<Integer> targetList) {
        for (int i = left; i < right; i++) {
            targetList.add(arr[i]);
        }
    }

    private int findFirstLessThen(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.printf("left: %d; right: %d\n", left, right);
        if (target >= nums[right]) {
            return right;
        }
        if (target >= nums[left]) {
            return left;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0,0,0,1,3,5,6,7,8,8};
        int k = 2;
        int x = 2;
        FindClosestElementsSolution solution = new FindClosestElementsSolution();
        List<Integer> result = solution.findClosestElements(nums, k, x);
        System.out.println(result);
    }
}
