package com.howard.leetcode.tree.binary.search;

/**
 * 插入节点到二叉搜索树中
 *
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 *
 * 例如,
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 *
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 * 或者这个树也是有效的:
 *
 *          5
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *          \
 *           4
 *
 * @author howard he
 * @create 2018/11/23 15:46
 */
public class InsertIntoBSTSolution {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        TreeNode n = root;
        while (n != null) {
            if (val < n.val) {
                if (n.left == null) {
                    n.left = newNode;
                    break;
                } else {
                    n = n.left;
                }
            } else {
                if (n.right == null) {
                    n.right = newNode;
                    break;
                } else {
                    n = n.right;
                }
            }
        }
        return root;
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
        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(7);
        TreeNode three = new TreeNode(1);
        TreeNode four = new TreeNode(3);

        root.left = one;
        root.right = two;
        one.left = three;
        one.right = four;

        InsertIntoBSTSolution solution = new InsertIntoBSTSolution();
        TreeNode result = solution.insertIntoBST(root, 5);
        System.out.println(result);
        System.out.println(root);
    }
}
