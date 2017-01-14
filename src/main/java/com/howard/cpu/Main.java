package com.howard.cpu;

import java.util.Arrays;
import java.util.Random;

/**
 * cpu分支预测
 * Created by howard on 16/10/9.
 */
public class Main {

    public static void main(String[] args) {
        // Generate data
        int arraySize = 32768;
        int data[] = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c)
            data[c] = rnd.nextInt() % 256;

        // !!! With this, the next loop runs faster
        //Arrays.sort(data);

        // Test
        long start = System.nanoTime();
        long sum = 0;

        for (int i = 0; i < 100000; ++i) {
            // Primary loop
            for (int c = 0; c < arraySize; ++c) {
                //会产生分支预测, 利用位运算来取消分支跳转
//                if (data[c] >= 128)
//                    sum += data[c];
                //优化后的代码
                int t = (data[c] - 128) >> 31; //得到的结果是0 或者 -1
                sum += ~t & data[c];
            }
        }

        System.out.println((System.nanoTime() - start) / 1000000000.0);
        System.out.println("sum = " + sum);

    }
}
