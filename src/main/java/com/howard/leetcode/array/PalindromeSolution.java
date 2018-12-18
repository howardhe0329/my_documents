package com.howard.leetcode.array;

/**
 * 验证回文串
 *
 * @author howard he
 * @create 2018/9/30 15:50
 */
public class PalindromeSolution {

    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left <= right) {
            if (!isLetterOrNumber(chars[left])) {
                left ++;
                continue;
            }
            if (!isLetterOrNumber(chars[right])) {
                right --;
                continue;
            }
            if (left >= right) {
                return true;
            }
            if (chars[left] != chars[right]) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }

    private boolean isLetterOrNumber(char c) {
        int v = (int) c;
        return (v >= 48 && v <= 57) || (v >= 97 && v <= 122);
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        PalindromeSolution solution = new PalindromeSolution();
        boolean result = solution.isPalindrome(s);
        System.out.println(result);

        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) '0');
        System.out.println((int) '9');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
    }
}
