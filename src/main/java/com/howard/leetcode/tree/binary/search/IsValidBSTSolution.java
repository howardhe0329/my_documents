package com.howard.leetcode.tree.binary.search;

/**
 * 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * @author howard he
 * @create 2018/11/21 16:05
 */
public class IsValidBSTSolution {

    public boolean isValidBST(TreeNode root) {
        return inorder(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean inorder(TreeNode n, long min, long max) {
        if (n == null) {
            return true;
        }
        if (n.val >= max || n.val <= min) {
            return false;
        }
        return inorder(n.left, min, n.val) && inorder(n.right, n.val, max);
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
        TreeNode root = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(4);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(6);

        root.left = one;
        root.right = two;
        two.left = three;
        two.right = four;

        IsValidBSTSolution solution = new IsValidBSTSolution();
        boolean result = solution.isValidBST(root);
        System.out.println(result);
    }
}
