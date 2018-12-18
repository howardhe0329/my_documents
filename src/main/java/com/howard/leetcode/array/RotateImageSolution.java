package com.howard.leetcode.array;

import java.util.Arrays;

/**
 * 旋转图像
 * <p>
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 *
 * @author howard he
 * @create 2018/11/13 14:23
 */
public class RotateImageSolution {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int last = n - 1;
        int level = 0;
        int totalLevel = n / 2;
        while (level < totalLevel) {
            for (int i = level; i < last; i++) {
                swap(matrix, level, i, i, last);
                swap(matrix, level, i, last, last - i + level);
                swap(matrix, level, i, last - i + level, level);
            }
            level++;
            last--;
        }

    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] matrix = new int[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count++;
                matrix[i][j] = count;
            }
        }
        RotateImageSolution solution = new RotateImageSolution();
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------------------------");
        solution.rotate(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
