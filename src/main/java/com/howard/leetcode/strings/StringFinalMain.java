package com.howard.leetcode.strings;

/**
 * 测试字符串不可变
 *
 * @author howard he
 * @create 2018/10/12 10:28
 */
public class StringFinalMain {

    public static void main(String[] args) {
        String s1 = "Hello World";
//        s1[5] = ','; compile error
        System.out.println(s1);

        s1 += "!";
        System.out.println(s1);

        // indexfOf 时间复杂度 O(n)
        System.out.println("The position of first 'o is: " + s1.indexOf('o'));
        // lastIndexOf 时间复杂度 O(n)
        System.out.println("The position of first 'o is: " + s1.lastIndexOf('o'));


    }
}
