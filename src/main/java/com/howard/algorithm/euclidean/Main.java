package com.howard.algorithm.euclidean;

/**
 * @author howard he
 * @create 2018/9/27 10:58
 */
public class Main {

    public int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        }
        if ((a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) == 1) {
            return gcd(a >> 1, b);
        } else if ((a & 1) == 1 && (b & 1) == 0) {
            return gcd(a, b >> 1);
        } else {
            return gcd(b, a - b);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        int result = m.gcd(8, 12);
        System.out.println(result);
    }
}
