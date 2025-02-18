package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class Q199 {
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

//    // 层次遍历，每次保存最右边的元素(队列当中的最后一个元素)
//    public static List<Integer> rightSideView(TreeNode root) {
//        if (root == null) return new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        List<Integer> list = new ArrayList<>();
//        queue.add(root);
//        while (!queue.isEmpty()){
//            Queue<TreeNode> tmp = new LinkedList<>();
//            int lastNodeVal = queue.peek().val;
//            while (!queue.isEmpty()){
//                TreeNode node = queue.poll();
//                lastNodeVal = node.val;
//                if (node.left != null) tmp.add(node.left);
//                if (node.right != null) tmp.add(node.right);
//            }
//            list.add(lastNodeVal);
//            queue = tmp;
//        }
//        return list;
//    }

    // 递归：先递归右子树, 再递归左子树，当深度首次达到时，表明为最右侧节点，即右视图
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, 0, list);
        return list;
    }

    public static void dfs(TreeNode root, int depth, List<Integer> list) {
        if (root == null) return;
        if (depth == list.size()) // 深度首次达到，为最右侧节点
            list.add(root.val);
        dfs(root.right, depth + 1, list); // 优先递归右子树
        dfs(root.left, depth + 1, list); // 随后递归左子树
    }


    public static void main(String[] args) {
        Integer[] list = new Integer[]{1, 2, 3, 4, null, null, null, 5};
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
        System.out.println(rightSideView(treeNodes.get(0)));
    }
}
