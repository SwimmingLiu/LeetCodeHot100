package com.swimmingliu.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * 226. 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class Q226 {
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

    // 依次递归翻转左右子节点，然后翻转当前节点的左右子节点
//    public static TreeNode invertTree(TreeNode root) {
//        if (root == null) return null;
//        invertTree(root.left);
//        invertTree(root.right);
//        TreeNode tmp = new TreeNode();
//        tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//        return root;
//    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }

    public static void main(String[] args) {

    }
}
