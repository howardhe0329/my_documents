package com.howard.leetcode.review;

import java.util.Arrays;

/**
 * 加一
 *
 * @author howard he
 * @create 2018/11/12 15:50
 */
public class PlusOneSolution {

    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int temp = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            digits[i] = temp;
            if (carry == 0) {
                break;
            }
        }
        if (carry == 1) {
            int[] resultArray = new int[digits.length + 1];
            resultArray[0] = 1;
            digits = resultArray;
        }
        return digits;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{9, 9, 9};
        PlusOneSolution solution = new PlusOneSolution();
        int[] result = solution.plusOne(nums);
        System.out.println(Arrays.toString(result));

    }
}
