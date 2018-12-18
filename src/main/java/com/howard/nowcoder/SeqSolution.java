package com.howard.nowcoder;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 *
 * @author howard he
 * @create 2018-12-06 15:03
 */
public class SeqSolution {

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList();
        int n = sum;
        while(n > 0) {
            int mod = (sum - n * (n + 1) / 2) % (n + 1);
            int k = (sum - n * (n + 1) / 2) / (n + 1);
            if (mod == 0 && k > 0) {
                ArrayList<Integer> itemList = new ArrayList();
                for(int i = 0; i <= n; i++) {
                    itemList.add(k + i);
                }
                resultList.add(itemList);
            }
            n --;
        }
        return resultList;
    }

    public static void main(String[] args) {
        SeqSolution solution = new SeqSolution();
        ArrayList<ArrayList<Integer>> resultList = solution.FindContinuousSequence(9);
        System.out.println(resultList);
    }
}
