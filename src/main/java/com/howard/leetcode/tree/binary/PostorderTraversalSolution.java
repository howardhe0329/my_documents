package com.howard.leetcode.tree.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历
 *
 * @author howard he
 * @create 2018/11/14 14:43
 */
public class PostorderTraversalSolution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode n, List<Integer> list) {
        if (n == null) {
            return;
        }
        postorder(n.left, list);
        postorder(n.right, list);
        list.add(n.val);
        System.out.println(n.val);
    }

    /**
     * 节点
     */
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
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);

        root.right = two;
        two.left = three;

        PostorderTraversalSolution solution = new PostorderTraversalSolution();
        List<Integer> result = solution.postorderTraversal(root);
        System.out.println(result);
    }
}
