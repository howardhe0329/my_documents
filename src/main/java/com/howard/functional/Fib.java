package com.howard.functional;

/**
 * Created by howard on 2017/2/6.
 */
public class Fib {

    public int fib(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fib1(int n) {
        int a = 1;
        int b = 1;
        while ( n > 0) {
            int temp = a;
            a = a + b;
            b = temp;
            n = n -1;
        }
        return b;
    }

    public int fib2(int a, int b, int n) {
        if (n == 0) {
            return b;
        }
        return fib2(b, a + b, n - 1);
    }

    public static void main(String[] args) {
        int n = 0;
        Fib fib = new Fib();
        System.out.println(fib.fib(n));
        System.out.println(fib.fib1(n));
        System.out.println(fib.fib2(0, 1, n));
    }
}
