package com.howard.algorithm.bsearch;

/**
 * 查找第一个值等于给定值的元素
 *
 * @author howard he
 * @create 2018/10/26 10:13
 */
public class FindFirstSpecValue {

    private int findFirst(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
//            System.out.format("low: %d; high: %d\n", low, high);
        }
        if (low >= nums.length || nums[low] != target) {
            return -1;
        }
        return low;
    }

    private int findLast(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 2);
            if (target < nums[mid]){
                high = mid - 1;
            }  else {
                low = mid + 1;
            }
//            System.out.format("mid: %d; low: %d; high: %d\n", mid, low, high);
        }
        if (high < 0 || nums[high] != target) {
            return -1;
        }
        return high;
    }

    public int findFirstMoreThan(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target > nums[mid]) {
                low = mid + 1;
            } else {
//                if (mid == 0 || (nums[mid - 1] < target)) {
//                    return mid;
//                } else {
//                    high = mid - 1;
//                }
                high = mid - 1;
            }
//            System.out.format("mid: %d; low: %d; high: %d\n", mid, low, high);
        }
        if (low >= nums.length) {
            return -1;
        }
        return low;
    }

    public int findLastLessThan(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target >= nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 3, 3, 3, 6, 7, 9, 12, 14, 18};
        int target = 11;
        FindFirstSpecValue findFirstSpecValue = new FindFirstSpecValue();
        int result = findFirstSpecValue.findFirst(nums, target);
        System.out.println(result);

        result = findFirstSpecValue.findLast(nums, target);
        System.out.println(result);

        result = findFirstSpecValue.findFirstMoreThan(nums, target);
        System.out.println(result);

        result = findFirstSpecValue.findLastLessThan(nums, target);
        System.out.println(result);
    }
}
