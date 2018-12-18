package com.howard.leetcode.tree.binary;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * @author howard he
 * @create 2018/11/19 14:18
 */
public class BuildTreeSolution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreePostIn(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public TreeNode buildTreePostIn(int[] inorder, int il, int ir, int[] postorder, int pl, int pr, Map<Integer, Integer> inorderMap) {
        if (pl > pr || il > ir) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pr]);
        int index = inorderMap.get(postorder[pr]);
        TreeNode left = buildTreePostIn(inorder, il, index - 1, postorder, pl, pl + index - il - 1, inorderMap);
        TreeNode right = buildTreePostIn(inorder, index + 1, ir, postorder, pl + index - il, pr - 1, inorderMap);
        root.left = left;
        root.right = right;
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
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};

        BuildTreeSolution solution = new BuildTreeSolution();
        TreeNode result = solution.buildTree(inorder, postorder);
        System.out.println(result);
    }
}
