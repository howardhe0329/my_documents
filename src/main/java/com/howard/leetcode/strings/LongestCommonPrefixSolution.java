package com.howard.leetcode.strings;

/**
 * 最长公共前缀
 *
 * @author howard he
 * @create 2018/10/12 17:16
 */
public class LongestCommonPrefixSolution {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int index = 0;
        StringBuilder result = new StringBuilder();
        while (index >= 0) {
            char tmp = '0';
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() == 0) {
                    return "";
                }
                if (index >= strs[i].length()) {
                    return result.toString();
                }
                if (tmp == '0') {
                    tmp = strs[i].charAt(index);
                } else if (tmp == strs[i].charAt(index)) {
                    continue;
                } else {
                    return result.toString();
                }
            }
            result.append(tmp);
            index++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] strs = new String[] {};
        LongestCommonPrefixSolution solution = new LongestCommonPrefixSolution();
        String result = solution.longestCommonPrefix(strs);
        System.out.println(result);
    }
}
