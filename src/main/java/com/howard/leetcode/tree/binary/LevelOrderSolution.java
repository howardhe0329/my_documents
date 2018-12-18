package com.howard.leetcode.tree.binary;

import java.util.*;

/**
 * 二叉树的层次遍历
 *
 * @author howard he
 * @create 2018/11/14 14:55
 */
public class LevelOrderSolution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size > 0) {
                TreeNode n = queue.poll();
                level.add(n.val);
                if (n.left != null) {
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    queue.offer(n.right);
                }
                size--;
            }
            result.add(level);
        }
        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode one = new TreeNode(9);
        TreeNode two = new TreeNode(20);
        TreeNode three = new TreeNode(15);
        TreeNode four = new TreeNode(7);

        root.left = one;
        root.right = two;
        two.left = three;
        two.right = four;

        LevelOrderSolution solution = new LevelOrderSolution();
        List<List<Integer>> result = solution.levelOrder(root);
        System.out.println(result);
    }
}
