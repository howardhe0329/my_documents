package com.howard.leetcode.strings;

/**
 * 反转字符串
 *
 * @author howard he
 * @create 2018/10/11 17:06
 */
public class ReverseStringSolution {

    /**
     * 对撞指针
     *
     * 时间复杂度 O(n)
     *
     * @param s
     * @return
     */
    public String reverseString(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }
        int left = 0;
        int right = s.length() - 1;
        char[] chars = new char[s.length()];
        while (left <= right) {
            chars[right] = s.charAt(left);
            chars[left] = s.charAt(right);
            left++;
            right--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s = "hello";
        ReverseStringSolution solution = new ReverseStringSolution();
        String result = solution.reverseString(s);
        System.out.println(result);
    }
}
