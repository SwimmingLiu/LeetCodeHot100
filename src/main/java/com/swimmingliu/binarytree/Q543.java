package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 */
public class Q543 {
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

    // 遍历每一个节点，求经过当前节点的直径，然后求最大值
//    public static int diameterOfBinaryTree(TreeNode root) {
//        int ans = 0;
//        List<TreeNode> treeNodes = new LinkedList<>();
//        dfs(treeNodes, root);
//        for (TreeNode treeNode : treeNodes) {
//            int depthLeft = findDepth(treeNode.left);
//            int depthRight = findDepth(treeNode.right);
//            int res = 0;
//            if (depthLeft == 0 && depthRight == 0)
//                res = 0;
//            else if (depthLeft == 0)
//                res = depthRight;
//            else if (depthRight == 0)
//                res = depthLeft;
//            else
//                res = depthLeft + depthRight;
//            ans = Math.max(res, ans);
//        }
//        return ans;
//    }
//    public static void dfs(List list, TreeNode root) {
//        if (root == null) return;
//        list.add(root);
//        dfs(list, root.left);
//        dfs(list, root.right);
//    }
//    public static int findDepth(TreeNode root) {
//        if (root == null) return 0;
//        int res = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        Queue<TreeNode> tmp;
//        while (!queue.isEmpty()) {
//            tmp = new LinkedList<>();
//            for (TreeNode node : queue) {
//                if (node.left != null) tmp.add(node.left);
//                if (node.right != null) tmp.add(node.right);
//            }
//            queue = tmp;
//            res++;
//        }
//        return res;
//    }


    // 递归求深度的过程中，同时更新答案，注意ans初始化为1，边界条件
    public static int ans;
    public static int findDepth(TreeNode root){
        if (root == null) return 0;
        int left = findDepth(root.left);
        int right = findDepth(root.right);
        ans = Math.max(ans, left + right + 1);
        return Math.max(left, right) + 1;
    }
    public static int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        findDepth(root);
        return ans - 1;
    }


    public static void main(String[] args) {
        Integer[] list = new Integer[]{1, 2};
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
        System.out.println(diameterOfBinaryTree(treeNodes.get(0)));
    }
}
