package com.howard.leetcode.tree.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历
 *
 * @author howard he
 * @create 2018/11/14 10:10
 */
public class PreorderTraversalSolution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        preorder(root, resultList);
        return resultList;
    }

    private void preorder(TreeNode n, List<Integer> list) {
        if (n == null) {
            return;
        }
        // 1。先访问自已
        list.add(n.val);
        // 2. 然后遍历左子树
        preorder(n.left, list);
        // 3. 最后遍历右子树
        preorder(n.right, list);
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pollLast();
            resultList.add(n.val);
            if (n.right != null) {
                stack.add(n.right);
            }
            if (n.left != null) {
                stack.add(n.left);
            }
        }
        return resultList;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        TreeNode n = root;
        while (n != null) {
            if (n.left == null) {
                resultList.add(n.val);
                n = n.right;
            } else {
                TreeNode predecessor = n.left;
                while ((predecessor.right != null) && (predecessor.right != n)) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    resultList.add(n.val);
                    predecessor.right = n;
                    n = n.left;
                } else {
                    predecessor.right = null;
                    n = n.right;
                }
            }
        }
        return resultList;
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

        root.left = two;
        root.right = three;

        PreorderTraversalSolution solution = new PreorderTraversalSolution();
        List<Integer> resultList = solution.preorderTraversal2(root);
        System.out.println(resultList);
    }
}
