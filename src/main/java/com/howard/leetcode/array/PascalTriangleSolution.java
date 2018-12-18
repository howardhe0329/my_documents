package com.howard.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * @author howard he
 * @create 2018/10/12 09:44
 */
public class PascalTriangleSolution {

    /**
     * 生成杨辉三角的二维数组
     *
     * 时间复杂度 O(n^2)
     *
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> triangList = new ArrayList<>(numRows);
        triangList.add(Arrays.asList(1));
        if (numRows == 1) {
            return triangList;
        }
        triangList.add(Arrays.asList(1, 1));
        if (numRows == 2) {
            return triangList;
        }
        for (int i = 2; i < numRows; i++) {
            List<Integer> columnList = new ArrayList<>(i + 1);
            columnList.add(1);
            List<Integer> prevList = triangList.get(i - 1);
            for (int j = 1; j < i; j++) {
                columnList.add(prevList.get(j - 1) + prevList.get(j));
            }
            columnList.add(1);
            triangList.add(columnList);
        }
        return triangList;
    }

    public static void main(String[] args) {
        int n = 10;
        PascalTriangleSolution solution = new PascalTriangleSolution();
        List<List<Integer>> result = solution.generate(n);
        System.out.println(result);
    }
}
