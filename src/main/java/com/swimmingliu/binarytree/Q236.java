package com.swimmingliu.binarytree;

import java.util.*;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：
 * 对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 */
public class Q236 {
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

    // 首先分别利用后序遍历分别找到p节点和q节点，然后向上回溯。回溯的过程当中传递该路径包含p/q节点的信息，然后根节点处进行判断。
//    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        return dfs(root, p, q);
//    }
//    public static TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) return null;
//        if (root == p || root == q) return root; // 找到p节点/q节点
//        TreeNode left = dfs(root.left, p, q);
//        TreeNode right = dfs(root.right, p, q);
//        if (left != null && right != null) return root; // 当前节点就是最近祖先节点 (两个节点都不是祖先)
//        if (left == null && right != null) return right; // 传递右子树 (节点可能自身是祖先)
//        else if (left != null && right == null) return left; // 传递左子树 (节点可能自身是祖先)
//        else return null;
//    }

    // 优化写法
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        else  if (left == null) return right;
        else return left;
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
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
    }
}
