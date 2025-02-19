package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class Q114 {
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
//    // 利用先序遍历 + 列表
//    public static void flatten(TreeNode root) {
//        List<TreeNode> list = new LinkedList<>();
//        dfs(root, list);
//        for (int i = 0; i < list.size() - 1; i++) {
//            list.get(i).left = null;
//            list.get(i).right = list.get(i + 1);
//        }
//        if (!list.isEmpty()) {
//            list.get(list.size() - 1).left = null;
//            list.get(list.size() - 1).right = null;
//        }
//    }
//
//    public static void dfs(TreeNode root, List<TreeNode> list) {
//        if (root == null) return;
//        list.add(root);
//        dfs(root.left, list);
//        dfs(root.right, list);
//    }

    // 左子树->右子树, 右子树(原)拼接到右子树(新), 循环
     public static void flatten(TreeNode root) {
        while (root != null){
            if (root.left == null){
                root = root.right;
            }else{
                TreeNode pre = root.left;
                while (pre.right != null){ // 找到左子树最右边的节点
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
     }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{1, 2, 5, 3, 4, null, 6};
        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                treeNodes.add(new TreeNode(list[i]));
            else
                treeNodes.add(new TreeNode());
        }
        // 生成二叉树
        for (int i = 0; i < treeNodes.size(); i++) {
            int right = (i + 1) * 2;
            int left = right - 1;
            TreeNode root = treeNodes.get(i);
            if (left < treeNodes.size()) {
                root.left = treeNodes.get(left);
            }
            if (right < treeNodes.size()) {
                root.right = treeNodes.get(right);
            }
            treeNodes.set(i, root);
        }
        flatten(treeNodes.get(0));
        System.out.println(treeNodes.get(0));
    }
}
