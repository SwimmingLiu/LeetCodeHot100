package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Q98 {
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

//    // 中序遍历，如果满足条件，应为升序序列 (列表)
//    public static boolean isValidBST(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        dfs(list, root);
//        for (int i = 0; i < list.size() - 1; i ++) {
//            if (list.get(i + 1) <= list.get(i))
//                return false;
//        }
//        return true;
//    }
//
//    public static void dfs(List<Integer> list, TreeNode root){
//        if (root == null) return;
//        dfs(list, root.left);
//        list.add(root.val);
//        dfs(list,root.right);
//    }
//
    // 先序遍历，代入边界范围
//    public static boolean isValidBST(TreeNode root) {
//        long left = Long.MIN_VALUE;
//        long right = Long.MAX_VALUE;
//        return isValidBST(root, left, right);
//    }
//    public static boolean isValidBST(TreeNode root, long left, long right) {
//        if (root == null) return true;
//        int x = root.val;
//        return left < x && x < right && isValidBST(root.left, left, x) && isValidBST(root.right, x, right);
//    }

//    // 中序遍历，过程中判断是否升序
//    private long pre = Long.MIN_VALUE;
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) return true;
//        if (!isValidBST(root.left)) return false; // 左子树不满足规则
//        if (root.val <= pre) return false; // 当前节点不满足规则
//        pre = root.val;
//        return isValidBST(root.right);
//    }

    // 后续遍历，判断每个结点是否在范围内（从底向上传）
    private static long min = Long.MIN_VALUE;
    private static long max = Long.MAX_VALUE;
    public static boolean isValidBST(TreeNode root) {
        return dfs(root)[1] != max;
    }
    private static long[] dfs(TreeNode root) {
        if (root == null) return new long[]{max, min}; // 正常返回(inf, -inf) 对应下面的判断条件：大于左边最大值，小于右边最小值
        long[] left = dfs(root.left); // l_min, l_max
        long[] right = dfs(root.right); // r_min, r_max
        int x = root.val;
        if (x <= left[1] || x >= right[0]) return new long[]{min, max}; // 如果不满足，则返回(-inf, inf)
        return new long[]{Math.min(left[0], x), Math.max(right[1], x)};
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
        System.out.println(isValidBST(treeNodes.get(0)));
    }
}
