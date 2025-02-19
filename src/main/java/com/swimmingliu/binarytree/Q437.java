package com.swimmingliu.binarytree;

import java.util.*;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Q437 {
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

//    // 暴力解，层次遍历 + 先序dfs
//    public static int res = 0;
//    public static int pathSum(TreeNode root, int targetSum) {
//        if (root == null) return 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()){
//            Queue<TreeNode> tmp = new LinkedList<>();
//            while (!queue.isEmpty()){
//                TreeNode top = queue.poll();
//                if (top.left != null) tmp.add(top.left);
//                if (top.right != null) tmp.add(top.right);
//                dfs(top, 0, targetSum);
//            }
//            queue = tmp;
//        }
//        return res;
//    }
//    public static void dfs(TreeNode root, long sum, int targetSum) {
//        if (root == null) return;
//        sum += root.val;
//        if (sum == targetSum) res++;
//        dfs(root.left, sum, targetSum);
//        dfs(root.right, sum, targetSum);
//    }

    // 前缀和 -> 利用s[i] - s[k] = target 的特性
    private static int res  = 0;
    public static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> scount = new HashMap<>(); // 存储前缀和的个数
        scount.put(0L, 1); // s[0] = 0, 初始化前缀和
        dfs(root, 0, targetSum, scount);
        return res;
    }
    private static void dfs(TreeNode root, long sum, int targetSum, Map<Long,Integer> scount){
        if (root == null) return;
        sum += root.val;
        res += scount.getOrDefault(sum - targetSum, 0);
        // sum - targetSum 判断是否存在对应的前缀和路径，因为可能存在s[i] - s[k] = targetSum
        scount.merge(sum, 1, Integer::sum); // 更新scount前缀和
        dfs(root.left, sum, targetSum, scount);
        dfs(root.right, sum, targetSum, scount);
        scount.merge(sum, -1, Integer::sum); // 恢复现状
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000};
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
        System.out.println(pathSum(treeNodes.get(0), 0));
    }
}
