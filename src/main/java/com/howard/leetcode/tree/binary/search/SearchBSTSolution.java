package com.howard.leetcode.tree.binary.search;

/**
 * 二叉搜索树中查找目标值
 *
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 *
 * @author howard he
 * @create 2018/11/23 15:34
 */
public class SearchBSTSolution {

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            int nval = root.val;
            if (val == nval) {
                return root;
            } else if (val < nval) {
                root = root.left;
            } else {
                root = root.right;
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

        SearchBSTSolution solution = new SearchBSTSolution();
        TreeNode result = solution.searchBST(root, 5);
        System.out.println(result);
    }
}
