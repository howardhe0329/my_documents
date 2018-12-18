package com.howard.leetcode.tree.binary;

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * @author howard he
 * @create 2018/11/16 14:48
 */
public class MaxDepthBinaryTreeSolution {

    private int answer;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_depth = maxDepth(root.left);
        int right_depth = maxDepth(root.right);
        return (left_depth >= right_depth ? left_depth : right_depth) + 1;
    }

    public int maxDepth1(TreeNode root) {
        depth(root, 1);
        return answer;
    }

    private void depth(TreeNode n, int depth) {
        if (n == null) {
            return;
        }
        if (n.left == null && n.right == null) {
            answer = answer >= depth ? answer : depth;
        }
        depth(n.left, depth + 1);
        depth(n.right, depth + 1);
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

        MaxDepthBinaryTreeSolution solution = new MaxDepthBinaryTreeSolution();
        int result = solution.maxDepth1(root);
        System.out.println(result);
    }
}
