package com.howard.math.mod;

/**
 * 求两个数的最大公约数
 *
 * @author howard he
 * @create 2018-12-13 09:44
 */
public class GcdSolution {

    /**
     * 辗转相除法，利用余数
     * @param num1
     * @param num2
     * @return
     */
    public int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        if (num1 > num2) {
            return gcd(num2, num1 % num2);
        } else {
            return gcd(num1, num2 % num1);
        }
    }

    public int gcd1(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        if (num1 > num2) {
            return gcd1(num1 - num2, num2);
        } else {
            return gcd1(num1, num2 - num1);
        }
    }

    /**
     * 辗转相除法
     *
     * @param num1
     * @param num2
     * @return
     */
    public int gcdMod(int num1, int num2) {
        int count = 0;
        while (num2 > 0) {
            int tmp = num1;
            num1 = num2;
            num2 = tmp % num2;
            count ++;
        }
        System.out.println("gcd mod times: " + count);
        return num1;
    }

    /**
     * 相减损术运算
     * @param num1
     * @param num2
     * @return
     */
    public int gcdSubtract(int num1, int num2) {
        while (num2 > 0) {
            if (num1 > num2) {
                num1 = num1 - num2;
            } else {
                num2  = num2 - num1;
            }
        }
        return num1;
    }

    /**
     * 当 a, b 都为偶数 就得出
     *  gcd(a, b) = 2gcd(a / 2, b /2)
     * 当 a 为奇数，b 为偶数
     *  gcd(a, b) = gcd(a, b /2)
     * 当 b 为奇数，a为偶数
     *  gcd(a, b) = gcd(a / 2, b)
     * 当a, b都为奇数，用相减法
     *  gcd(a, b) = gcd(a - b, b)
     * @param num1
     * @param num2
     * @return
     */
    public int gcdNew(int num1, int num2) {
        // 递归的基线条件
        if (num1 == num2) {
            return num1;
        }
        if (num1 < num2) {
            return gcdNew(num2, num1);
        }
        boolean even1 = (num1 & 1) == 0;
        boolean even2 = (num2 & 1) == 0;
        if (even1 && even2) {
            return gcdNew(num1 >> 1, num2 >> 1) << 1;
        } else if (even2) {
            return gcdNew(num1, num2 >> 1);
        } else if (even1) {
            return gcdNew(num1 >> 1, num2);
        } else {
            return gcdNew(num2, num1 - num2);
        }
    }


    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 25;
        GcdSolution solution = new GcdSolution();
        int result = solution.gcd(num1, num2);
        System.out.println("gcd 递归：" + result);
        System.out.println("gcd1 递归：" + solution.gcd1(num1, num2));

        System.out.println("gcd mod方法：" + solution.gcdMod(num1, num2));

        System.out.println("gcd subtract方法：" + solution.gcdSubtract(num1, num2));

        System.out.println("gcd new方法：" + solution.gcdNew(num1, num2));



    }
}
