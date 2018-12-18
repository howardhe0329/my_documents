package com.howard.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角第n行
 *
 * @author howard he
 * @create 2018/10/16 11:29
 */
public class PascalTriangleKthSolution {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> resultList = new ArrayList<>(rowIndex);
        resultList.add(1);
        if (rowIndex == 0) {
            return resultList;
        }
        if (rowIndex == 1) {
            resultList.add(1);
            return resultList;
        }
        List<Integer> prevList = new ArrayList<>(rowIndex);
        int row = 1;
        while (row < rowIndex + 1) {
            for (int i = 1; i <= row; i++) {
                if (i == row) {
                    resultList.add(1);
                } else {
                    resultList.set(i, prevList.get(i - 1) + prevList.get(i));
                }
            }
            prevList.clear();
            prevList.addAll(resultList);
            row++;
        }
        return resultList;
    }

    public static void main(String[] args) {
        int row = 3;
        PascalTriangleKthSolution solution = new PascalTriangleKthSolution();
        List<Integer> result = solution.getRow(row);
        System.out.println(result);
    }
}
