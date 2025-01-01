package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 先序：根、左、右
 * 中序：左、根、右
 * 后序：左、右、根
 */
public class Q94 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    // 直接递归: 中序：左、根、右
//    public static List<Integer> inorderTraversal(TreeNode root) {
//        if (root == null)
//            return Collections.emptyList();
//        List<Integer> list = new ArrayList<>();
//        list.add(root.val);
//        list.addAll(inorderTraversal(root.left));
//        list.addAll(inorderTraversal(root.right));
//        return list;
//    }

    // dfs递归
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    public static void dfs(List<Integer> list, TreeNode root) {
        if (root == null) return;
        dfs(list, root.left);
        list.add(root.val);
        dfs(list, root.right);
    }

    public static void main(String[] args) {

    }
}
