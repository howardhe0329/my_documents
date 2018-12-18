package com.howard.leetcode.tree.binary.search;

import java.util.*;

/**
 * 二叉搜索树迭代器
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * 注意: next() 和hasNext() 操作的时间复杂度是O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 *
 * @author howard he
 * @create 2018/11/21 16:52
 */
public class BinarySearchTreeIteratorSolution {

    public static class BinarySearchTreeIterator {

        private Deque<TreeNode> deque;

        public BinarySearchTreeIterator(TreeNode root) {
            this.deque = new LinkedList<>();
            init(root);
        }

        private void init(TreeNode root) {
            while (root != null) {
                deque.offer(root);
                root = root.left;
            }
        }


        /**
         * O(1)
         * @return
         */
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        /**
         * O(1)
         * @return
         */
        public int next() {
            TreeNode n = deque.pollLast();
            TreeNode cur = n;
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    deque.offer(cur);
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        break;
                    }
                }
            }
            return n.val;
        }
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
        TreeNode two = new TreeNode(6);
        TreeNode three = new TreeNode(4);
        TreeNode four = new TreeNode(7);

        root.left = one;
        root.right = two;
        two.left = three;
        two.right = four;

        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
