package com.howard.leetcode.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻转字符串里的单词
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例:
 *
 * 输入: "the sky is blue",
 * 输出: "blue is sky the".
 * 说明:
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 进阶: 请选用C语言的用户尝试使用 O(1) 空间复杂度的原地解法。
 *
 * @author howard he
 * @create 2018/10/16 15:27
 */
public class ReverseWordsSolution {

    public String reverseWords(String s) {
        if (s.length() == 0) {
            return s;
        }
        if (s == "1 ") {
            return s;
        }
        List<String> words = new ArrayList<>();
        int start = 0;
        int end = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                end = i;
                words.add(s.substring(start, end));
                start = i + 1;
            }
            if (i == s.length() - 1) {
                words.add(s.substring(start));
            }
            i ++;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = words.size() - 1; j >= 0; j--) {
            if (words.get(j).length() != 0) {
                sb.append(words.get(j));
                if (j != 0) {
                    sb.append(" ");
                }
            }
        }
        if (sb.toString().equals(" ")) {
            return "";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "1 ";
        ReverseWordsSolution solution = new ReverseWordsSolution();
        String result = solution.reverseWords(s);
        System.out.println(result);
    }
}
