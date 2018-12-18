package com.howard.leetcode.tree.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历
 *
 * @author howard he
 * @create 2018/11/14 14:18
 */
public class InorderTraversalSolution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode n, List<Integer> list) {
        if (n == null) {
            return;
        }
        // 1. 先遍历左子树
        inorder(n.left, list);
        // 2. 然后访问自已节点
        list.add(n.val);
        // 3. 最后遍历右子树
        inorder(n.right, list);
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pollLast();
            result.add(curr.val);
            curr = curr.right;

        }
        return result;
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

        InorderTraversalSolution solution = new InorderTraversalSolution();
        List<Integer> resultList = solution.inorderTraversal1(root);
        System.out.println(resultList);
    }
}
