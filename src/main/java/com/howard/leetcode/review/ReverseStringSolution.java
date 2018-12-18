package com.howard.leetcode.review;

/**
 * 反转字符串
 *
 * @author howard he
 * @create 2018/11/13 16:15
 */
public class ReverseStringSolution {

    public String reverseString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] array = s.toCharArray();
        int left = 0;
        int right = array.length -1;
        while (left < right) {
            swap(array, left++, right--);
        }
        return new String(array);
    }

    private void swap(char[] array, int a, int b) {
        char tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        ReverseStringSolution solution = new ReverseStringSolution();
        String result = solution.reverseString(s);
        System.out.println(result);
    }
}
