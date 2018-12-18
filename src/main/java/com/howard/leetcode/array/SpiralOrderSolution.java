package com.howard.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 *
 * @author howard he
 * @create 2018/10/11 15:34
 */
public class SpiralOrderSolution {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>(0);
        }
        int row = matrix.length;
        int column = matrix[0].length;
        List<Integer> resultList = new ArrayList<>(row * column);
        int i = 0;
        int j = 0;
        int ring = 0;
        return resultList;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SpiralOrderSolution solution = new SpiralOrderSolution();
        List<Integer> result = solution.spiralOrder(matrix);
        System.out.println(result);
    }
}
