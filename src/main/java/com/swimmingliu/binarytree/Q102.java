package com.swimmingliu.binarytree;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class Q102 {
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

    // 队列实现二叉树层次遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        queue.add(root);
        while (!queue.isEmpty()){
            LinkedList<TreeNode> tmp = new LinkedList<>();
            List<Integer> resultTmp = new LinkedList<>();
            while (!queue.isEmpty()){
                 TreeNode node = queue.poll();
                 if (node == null) continue;
                 if (node.left != null) tmp.add(node.left);
                 if (node.right != null) tmp.add(node.right);
                 resultTmp.add(node.val);
            }
            queue = tmp;
            result.add(resultTmp);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{3,9,20,null,null,15,7};
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
        System.out.println(levelOrder(treeNodes.get(0)));
    }
}
