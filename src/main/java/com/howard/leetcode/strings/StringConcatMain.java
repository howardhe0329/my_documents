package com.howard.leetcode.strings;

/**
 * 字符串连接的问题
 *
 * @author howard he
 * @create 2018/10/12 10:41
 */
public class StringConcatMain {

    /**
     *
     * 时间复杂度为 O(n^2)
     *
     * 复杂度分析
     * hello是5个字符，并循环n次相连接:
     * 5 + 5 * 2 + 5 * 3 + 5 * 4 + ... + 5 * (n - 3) + 5 * (n - 2) + 5 * (n - 1) + 5 * n
     * = 5 * (1 + 2 + 3 + ... + n -1 + n)
     * = 5 * (n * (n + 1) / 2)
     * = O(n^2)
     *
     * @param n
     * @return
     */
    public String concat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "hello";
        }
        System.out.println("concat exec time: " + (System.currentTimeMillis() - start) + "ms");
        return s;
    }

    /**
     *
     * 时间复杂度为 O(n)
     *
     * 复杂度分析
     * append方法是在char数据的末尾添加字符, 所以时间复杂度为 O(1)
     *
     * @param n
     * @return
     */
    public String concat1(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("hello");
        }
        System.out.println("concat1 exec time: " + (System.currentTimeMillis() - start) + "ms");
        return sb.toString();
    }


    public static void main(String[] args) {
        int n = 10000;
        StringConcatMain main = new StringConcatMain();
        main.concat(n);

        System.out.println(main.concat1(n));

    }
}
