package com.swimmingliu.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class Q105 {
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

//    // 先序找根，中序划界： 先序区间的第一个节点为根，其中序中两侧的节点均为左右子树，递归左右子树。
//    public static TreeNode buildTree(int[] preorder, int[] inorder) {
//        return buildTree(preorder, 0, preorder.length - 1,
//                inorder, 0, inorder.length - 1);
//    }
//    // preLeft和preRight用于标识preorder当中需要用到的子区间 (左右子树递归)
//    public static TreeNode buildTree(int[] preorder, int preLeft, int preRight,
//                                     int[] inorder, int inLeft, int inRight){
//        // 递归退出条件
//        if (preLeft > preRight || inLeft > inRight) return null;
//        TreeNode root = new TreeNode(preorder[preLeft]); // 先序列表的第一个元素就是根节点
//        int pivotIndex = inLeft;
//        while (inorder[pivotIndex] != preorder[preLeft]) // 找到根节点在中序列表中的索引
//            pivotIndex++;
//        root.left = buildTree(preorder, preLeft + 1, preLeft + pivotIndex - inLeft,
//                inorder, inLeft, pivotIndex - 1);
//        root.right = buildTree(preorder, preLeft + pivotIndex - inLeft + 1, preRight,
//                inorder,pivotIndex + 1, inRight);
//        return root;
//    }


    // [Hash快速索引]先序找根，中序划界： 先序区间的第一个节点为根，其中序中两侧的节点均为左右子树，递归左右子树。
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i ++)
            map.put(inorder[i], i);
        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, map);
    }
    // preLeft和preRight用于标识preorder当中需要用到的子区间 (左右子树递归)
    public static TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                                     int[] inorder, int inLeft, int inRight, Map<Integer, Integer> map){
        // 递归退出条件
        if (preLeft > preRight || inLeft > inRight) return null;
        TreeNode root = new TreeNode(preorder[preLeft]); // 先序列表的第一个元素就是根节点
        int pivotIndex = map.get(preorder[preLeft]);
        root.left = buildTree(preorder, preLeft + 1, preLeft + pivotIndex - inLeft,
                inorder, inLeft, pivotIndex - 1, map);
        root.right = buildTree(preorder, preLeft + pivotIndex - inLeft + 1, preRight,
                inorder,pivotIndex + 1, inRight, map);
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
    }
}
