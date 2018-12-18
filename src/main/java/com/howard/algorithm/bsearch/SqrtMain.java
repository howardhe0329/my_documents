package com.howard.algorithm.bsearch;

/**
 * @author howard he
 * @create 2018/10/25 10:58
 */
public class SqrtMain {

    /**
     * 牛顿迭代法
     * @param a
     * @return
     */
    public double sqrt(int a) {
        double x1 = 1, x2;
        x2 = x1 / 2.0 + a / (2 * x1);
        while (Math.abs(x2 - x1) > 1e-4) {
            x1 = x2;
            x2 = x1 / 2.0 + a / (2 * x1);
        }
        return x2;
    }

    public double sqrt1(double a) {
        if (a <= 1) {
            return a;
        }
        double low = 0;
        double high = a;
        double mid = low + (high - low) / 2;
        int n = 1;
        while (Math.abs(high - low) > 1e-6) {
            System.out.println("第" + n + "次：mid: " + mid);
            if (mid * mid > a) {
                high = mid;
            } else if (mid * mid  < a) {
                low = mid;
            } else if (Math.abs(mid * mid - a) < 1e-6){
                return mid;
            }
            n++;
            String slice = String.valueOf(mid);
            if (slice.substring(slice.indexOf(".")).length() > 6) {
                break;
            }
            mid = low + (high - low) / 2;
        }
        return mid;
    }

    public static void main(String[] args) {
        SqrtMain main = new SqrtMain();
        int x = 2;
        double result = main.sqrt(x);
        System.out.println(result);
        System.out.println(main.sqrt1(x));
        System.out.println(Math.sqrt(x));

        double b = 1e-6;
        double c = 0.000001;
        System.out.println(b == c);
    }
}
