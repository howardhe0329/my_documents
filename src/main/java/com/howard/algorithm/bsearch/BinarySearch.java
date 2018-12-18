package com.howard.algorithm.bsearch;

/**
 * 二分查找算法
 *
 * @author howard he
 * @create 2018/10/24 17:20
 */
public class BinarySearch {

    public int binarySearch(int[] nums, int target) {
        // low 指针指向数组第一个元素位置
        int low = 0;
        // high 指针指向数组最后一个元素位置
        int high = nums.length - 1;
        // low > high时退出循环
        while (low <= high) {
            // mid是low和high的中间位置
            int mid = (low + high) / 2;
            // 获取数组元素
            int guess = nums[mid];
            // 如果target等于guess那么找到，返回数组的索引位置
            if (target == guess) {
                return mid;
            } else if (target > guess) {
                // 如果target大于guess，那么low指针移动到mid + 1的位置
                low = mid + 1;
            } else {
                // 如果target小于guress, 那么high指针移动到mid - 1的位置
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 2, 6, 7, 9, 12, 12, 18};
        int target = 12;
        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.binarySearch(nums, target);
        System.out.println(result);

        System.out.println(Math.sqrt(2));
        double a = 1e-4;
        System.out.println(a);
    }
}
