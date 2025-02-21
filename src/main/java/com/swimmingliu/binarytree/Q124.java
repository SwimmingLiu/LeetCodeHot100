package com.swimmingliu.binarytree;

import java.util.*;

/**
 * 124. 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class Q124 {
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
    // 递归的思想 -> 把整个问题分为子问题来解决
    // 计算所有结点的最大路径，可以先计算每一个结点的最大路径
    // 每个结点最大路径 = 左子树路径 + 右子树路径 + 当前结点值
    // 返回给父节点的最大路径 = Max(左子树路径， 右子树路径) + 当前结点值 （如果为负数，就置为0）
    private static int ans = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
       dfs(root);
       return ans;
    }
    private static int dfs(TreeNode root){
         if (root == null) return 0;
        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);
        ans = Math.max(ans, leftMax + rightMax + root.val);
        return Math.max(Math.max(leftMax, rightMax)  + root.val, 0);
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
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
        System.out.println(maxPathSum(treeNodes.get(0)));
    }
}
