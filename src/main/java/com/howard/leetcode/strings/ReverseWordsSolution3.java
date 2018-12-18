package com.howard.leetcode.strings;

/**
 * 反转字符串中的单词 III
 *
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例 1:
 *
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * @author howard he
 * @create 2018/10/16 17:06
 */
public class ReverseWordsSolution3 {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                sb.append(s.substring(i, end)).append(" ");
                end = i;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        ReverseWordsSolution3 solution3 = new ReverseWordsSolution3();
        String result = solution3.reverseWords(s);
        System.out.println(result);
    }
}
