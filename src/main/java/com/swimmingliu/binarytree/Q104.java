package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 先序：根、左、右
 * 中序：左、根、右
 * 后序：左、右、根
 */
public class Q104 {
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

//    // 后续遍历
//    public static int maxDepth(TreeNode root) {
//        if (root == null) return 0;
//        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
//    }

    // 按层遍历
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        List<TreeNode> layerNodes = new LinkedList<>();
        List<TreeNode> tmp  = new LinkedList<>();
        layerNodes.add(root);
        int res = 0;
        while (!layerNodes.isEmpty()){
            tmp = new LinkedList<>();
            for (TreeNode node : layerNodes) {
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            layerNodes = tmp;
            res ++;
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
