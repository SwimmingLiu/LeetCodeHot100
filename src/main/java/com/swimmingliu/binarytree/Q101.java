package com.swimmingliu.binarytree;

import java.util.*;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
public class Q101 {
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

    // 分别先序遍历 + 根右左遍历，然后看是否相同
//    public static boolean isSymmetric(TreeNode root) {
//        Queue<TreeNode> left = new LinkedList<>();
//        Queue<TreeNode> right = new LinkedList<>();
//        dfsLeft(left, root.left);
//        dfsRight(right, root.right);
//        for (TreeNode treeNode : left) {
//            System.out.println(treeNode.val);
//        }
//        for (TreeNode treeNode : right) {
//            System.out.println(treeNode.val);
//        }
//        while (!left.isEmpty() || !right.isEmpty()) {
//            TreeNode leftNode = left.poll();
//            TreeNode rightNode = right.poll();
//            if ((leftNode == null || rightNode == null) && leftNode != rightNode)
//                return false;
//            else if (leftNode != null && leftNode.val != rightNode.val)
//                return false;
//        }
//        return true;
//    }
//
//    public static void dfsLeft(Queue<TreeNode> list, TreeNode root) {
//        if (root == null) return;
//        list.add(root);
//        dfsLeft(list, root.left);
//        dfsLeft(list, root.right);
//    }
//
//    public static void dfsRight(Queue<TreeNode> list, TreeNode root) {
//        if (root == null) return;
//        list.add(root);
//        dfsRight(list, root.right);
//        dfsRight(list, root.left);
//    }

    // 深度优先搜索
    public static boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }

    public static boolean recur(TreeNode left, TreeNode right){
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return recur(left.left, right.right) && recur(left.right, right.left);
    }


    public static void main(String[] args) {
        Integer[] list = new Integer[]{1,2,2,null,3,null,3};
        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                treeNodes.add(new TreeNode(list[i]));
            else
                treeNodes.add(new TreeNode());
        }
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
        System.out.println(isSymmetric(treeNodes.get(0)));
    }
}
