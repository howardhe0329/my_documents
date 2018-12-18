package com.howard.leetcode.strings;

import java.util.*;

/**
 * 根据字符出现频率排序
 *
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * @author howard he
 * @create 2018/11/20 15:17
 */
public class FrequencySortSolution {

    public String frequencySort(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        if (map.size() == s.length()) {
            return s;
        }
        // 桶排序
        List<Character>[] bucket = new List[s.length() + 1];
        for (char c: map.keySet()) {
            int value = map.get(c);
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<>();
            }
            bucket[value].add(c);
        }
        StringBuilder result = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            List<Character> list = bucket[i];
            if (list != null) {
                for (char c: list) {
                    for (int j = 0; j < map.get(c); j++) {
                        result.append(c);
                    }
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        FrequencySortSolution solution = new FrequencySortSolution();
        String result = solution.frequencySort(s);
        System.out.println(result);
    }
}
