package com.howard.leetcode.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 *
 * @author howard he
 * @create 2018/11/13 16:40
 */
public class FirstUniqueCharSolution {

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            boolean contains = map.containsKey(chars[i]);
            map.put(chars[i], i);
            if (contains) {
                chars[i] = '-';
            }
        }
        for (int i = 0; i < chars.length; i++) {
            Integer val = map.get(chars[i]);
            if (val != null && val.intValue() == i) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "cc";
        FirstUniqueCharSolution solution = new FirstUniqueCharSolution();
        int result = solution.firstUniqChar(s);
        System.out.println(result);
    }
}
