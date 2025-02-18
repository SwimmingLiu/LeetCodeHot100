package com.swimmingliu.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡二叉搜索树。
 */
public class Q108 {
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

    // 递归二分恢复平衡二叉搜索树
    public static TreeNode sortedArrayToBST(int[] nums) {
        return recoverBST(nums, 0, nums.length - 1);
    }

    public static TreeNode recoverBST(int[] nums, int left, int right){
        // 边界判断
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recoverBST(nums, left, mid - 1);
        root.right = recoverBST(nums, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        int[] list = new int[]{-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(list));
    }
}
