package com.howard.leetcode.tree.binary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 每个节点的右向指针
 *
 * 给定一个二叉树
 *
 * struct TreeLinkNode {
 *   TreeLinkNode *left;
 *   TreeLinkNode *right;
 *   TreeLinkNode *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 说明:
 *
 * 你只能使用额外常数空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * 你可以假设它是一个完美二叉树（即所有叶子节点都在同一层，每个父节点都有两个子节点）。
 * 示例:
 *
 * 给定完美二叉树，
 *
 *      1
 *    /  \
 *   2    3
 *  / \  / \
 * 4  5  6  7
 * 调用你的函数后，该完美二叉树变为：
 *
 *      1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \  / \
 * 4->5->6->7 -> NULL
 *
 * @author howard he
 * @create 2018/11/19 15:56
 */
public class ConnectTreeLinkNodeSolution {

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeLinkNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            TreeLinkNode prev = null;
            while (size > 0) {
                TreeLinkNode n = deque.poll();
                if (prev != null) {
                    prev.next = n;
                }
                prev = n;
                if (n.left != null) {
                    deque.offer(n.left);
                }
                if (n.right != null) {
                    deque.offer(n.right);
                }
                size--;
            }
        }
    }

    public void connect1(TreeLinkNode root) {
        if (root == null) {
            return;
        }

    }

    public static class TreeLinkNode {
        int val;
        private TreeLinkNode left;
        private TreeLinkNode right;
        private TreeLinkNode next;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode two = new TreeLinkNode(2);
        TreeLinkNode three = new TreeLinkNode(3);
        TreeLinkNode four = new TreeLinkNode(4);
        TreeLinkNode five = new TreeLinkNode(5);
        TreeLinkNode six = new TreeLinkNode(6);
        TreeLinkNode seven = new TreeLinkNode(7);

        root.left = two;
        root.right = three;

        two.left = four;
        two.right = five;

        three.left = six;
        three.right = seven;

        ConnectTreeLinkNodeSolution solution = new ConnectTreeLinkNodeSolution();
        solution.connect(root);

        System.out.println(root);
    }
}
