package com.howard.leetcode.tree.binary;

/**
 * 路径总和
 * <p>
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author howard he
 * @create 2018/11/16 16:56
 */
public class PathSumSolution {

    private boolean has;

    public boolean hasPathSum(TreeNode root, int sum) {
        sum(root, sum);
        return has;
    }

    public void sum(TreeNode n, int sum) {
        if (n == null) {
            return;
        }
        sum = sum - n.val;
        if (n.left == null && n.right == null && sum == 0) {
            has = true;
        }
        sum(n.left, sum);
        sum(n.right, sum);
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
        TreeNode root = new TreeNode(1);
        TreeNode one = new TreeNode(2);
//        TreeNode two = new TreeNode(8);
//        TreeNode three = new TreeNode(11);
//        TreeNode four = new TreeNode(13);
//        TreeNode five = new TreeNode(4);
//        TreeNode six = new TreeNode(7);
//        TreeNode seven = new TreeNode(2);
//        TreeNode eight = new TreeNode(1);

        root.left = one;
//        root.right = two;
//
//        one.left = three;
//        three.left = six;
//        three.right = seven;
//
//        two.left = four;
//        two.right = five;
//
//        five.right = eight;

        int sum = 1;
        PathSumSolution solution = new PathSumSolution();
        boolean result = solution.hasPathSum(root, sum);
        System.out.println(result);

    }
}
