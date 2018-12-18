package com.howard.leetcode.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 同构字符串
 *
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @author howard he
 * @create 2018/11/20 14:31
 */
public class IsomorphicSolution {

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int len = s.length();
        Map<Integer, Integer> map1 = new HashMap<>(len);
        Map<Integer, Integer> map2 = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int a = s.codePointAt(i);
            int b = t.codePointAt(i);
            if (!check(map1, a, b) || !check(map2, b, a)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(Map<Integer, Integer> map, int s, int t) {
        Integer val = map.get(s);
        if (val == null) {
            map.put(s, t);
        } else if (val != null && val.intValue() != t) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "paper";
        String t = "title";
        IsomorphicSolution solution = new IsomorphicSolution();
        boolean result = solution.isIsomorphic(s, t);
        System.out.println(result);
    }
}
