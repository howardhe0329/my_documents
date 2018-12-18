package com.howard.leetcode.array;

/**
 * 反转字符串中的元音字母
 *
 * @author howard he
 * @create 2018/9/30 16:23
 */
public class ReverseVowelsSolution {

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (!isVowel(chars[left])) {
                left ++;
                continue;
            }
            if (!isVowel(chars[right])) {
                right --;
                continue;
            }
            if (chars[left] != chars[right]) {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
            }
            left ++;
            right --;
        }
        return String.valueOf(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'
                || c == 'A' || c == 'I' || c == 'E' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        String s = "aA";
        ReverseVowelsSolution solution = new ReverseVowelsSolution();
        String result = solution.reverseVowels(s);
        System.out.println(result);
    }
}
