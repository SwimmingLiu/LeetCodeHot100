package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 230. 二叉搜索树中第 K 小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 */
public class Q230 {
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


    // 中序遍历后为升序序列，列表直接索引第k个元素
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        return list.get(k - 1);
    }
    public static void dfs(List<Integer> list, TreeNode root){
        if (root == null) return;
        dfs(list, root.left);
        list.add(root.val);
        dfs(list, root.right);
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{0};
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
        System.out.println(kthSmallest(treeNodes.get(0), 1));
    }
}
