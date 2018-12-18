package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 加一
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * @author howard he
 * @create 2018/10/11 10:19
 */
public class PlusOneSolution {

    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
            return digits;
        }
        int resultLength = digits.length;
        if (digits[0] == 9) {
            resultLength++;
        }
        int[] result = new int[resultLength];
        boolean is = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (!is) {
                result[i] = digits[i];
            } else if (digits[i] != 9) {
                result[i] = digits[i] + 1;
                is = false;
            } else if (i == 0) {
                result[i] = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PlusOneSolution solution = new PlusOneSolution();
        int[] result = solution.plusOne(new int[] {2,4,9,3,9});
        System.out.println(Arrays.toString(result));

    }
}
