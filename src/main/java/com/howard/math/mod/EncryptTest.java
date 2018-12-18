package com.howard.math.mod;

/**
 * 加密与解密
 *
 * @author howard he
 * @create 2018-12-13 11:17
 */
public class EncryptTest {

    private int salt = 590127;

    public int encrypt(int x) {
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int t = Integer.parseInt(String.valueOf(s.charAt(i)));
            sb.append((t + salt) % 7);
        }
        return Integer.parseInt(sb.reverse().toString());
    }

    public int decrypt(int x) {
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int t = Integer.parseInt(String.valueOf(s.charAt(i)));
            sb.append((t + salt) % 7).append(" ");
        }
        return Integer.parseInt(sb.reverse().toString());
    }

    public static void main(String[] args) {
        int x = 625;
        EncryptTest test = new EncryptTest();
        int en = test.encrypt(x);
        System.out.println(test.encrypt(x));

        System.out.println(test.decrypt(en));
    }
}
