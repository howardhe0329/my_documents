package com.howard.leetcode.array;

/**
 * 盛最多水的容器
 *
 * @author howard he
 * @create 2018/9/30 16:48
 */
public class MaxAreaSolution {

    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int distance = right - left;
            int h = (height[left] <= height[right]) ? height[left] : height[right];
            int area = distance * h;
            if (maxArea < area) {
                maxArea = area;
            }
            if (height[left] <= height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[] {1,8,6,2,5,4,8,3,7};
        MaxAreaSolution solution = new MaxAreaSolution();
        int result = solution.maxArea(height);
        System.out.println(result);
    }
}
