package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 对角线遍历（二维数组）
 *
 * 给定一个含有 M x N 个元素的矩阵（M行，N列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 * 示例:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 *
 * @author howard he
 * @create 2018/10/11 11:08
 */
public class DiagonalOrderSolution {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int[] result = new int[row * column];
        int maxLine = row + column - 1;
        int index = 0;
        for (int l = 0; l < maxLine; l++) {
            boolean odd = (l & 1) == 1;
            for (int i = 0; i <= l && i < row && odd; i++) {
                if (l - i < column) {
                    result[index++] = matrix[i][l - i];
                }
            }
            for (int i = l; i >= 0 && !odd; i--) {
                if (i < row && l - i < column) {
                    result[index++] = matrix[i][l - i];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        DiagonalOrderSolution solution = new DiagonalOrderSolution();
        int[] result = solution.findDiagonalOrder(matrix);
        System.out.println(Arrays.toString(result));
    }
}
