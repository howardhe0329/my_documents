package com.howard.leetcode.review;

import java.util.HashMap;
import java.util.Map;

/**
 * 有效的字母异位词
 *
 * @author howard he
 * @create 2018/11/20 11:36
 */
public class AnagramSolution {

    public boolean isAnagram(String s, String t) {
        if (s == null && t == null || s.length() != t.length()) {
            return false;
        }
        Map<Integer, Integer> dict = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            dict.put(s.codePointAt(i), dict.getOrDefault(s.codePointAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            Integer val = dict.put(t.codePointAt(i), dict.getOrDefault(t.codePointAt(i), 0) - 1);
            if (val == null || val.intValue() < 1) {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccac";
        AnagramSolution solution = new AnagramSolution();
        boolean result = solution.isAnagram(s, t);
        System.out.println(result);
    }
}
