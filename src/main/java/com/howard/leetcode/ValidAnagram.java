package com.howard.leetcode;

import java.util.Arrays;

/**
 * 验证变位词
 *
 * @author howard he
 * @create 2018/3/19 14:50
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        int[] counter = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();

        String s = "hello";
        String t = "leloh";

        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i) + ", " + (s.charAt(i) - 'a'));
        }

        boolean b1 = validAnagram.isAnagram(s, t);

        System.out.println("b1: " + b1);
        boolean b2 = validAnagram.isAnagram1(s, t);
        System.out.println("b2: " + b2);
    }
}
