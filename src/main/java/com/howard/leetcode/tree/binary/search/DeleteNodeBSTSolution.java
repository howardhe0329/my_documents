package com.howard.leetcode.tree.binary.search;

/**
 * 删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * @author howard he
 * @create 2018/11/23 16:05
 */
public class DeleteNodeBSTSolution {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode del = null;
        TreeNode n = root;
        while (n != null) {
            if (key == n.val) {
                del = n;
                break;
            } else if (key < n.val) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        if (del != null) {
            if (del.left == null && del.right == null) {

            } else if (del.left != null && del.right != null) {
                
            } else if (del.left != null){
                del.val = del.left.val;
                del.left = null;
            } else {
                del.val = del.right.val;
                del.right = null;
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
        TreeNode root = new TreeNode(5);
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(6);
        TreeNode three = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(7);

        root.left = one;
        root.right = two;

        one.left = three;
        one.right = four;

        two.right = five;

        DeleteNodeBSTSolution solution = new DeleteNodeBSTSolution();
        TreeNode result = solution.deleteNode(root, 3);
        System.out.println(result);
    }
}
