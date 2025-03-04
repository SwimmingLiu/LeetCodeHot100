package com.swimmingliu.dynamicplan;


import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class Q300 {

    // dfs(i) 表示以 nums[i] 结尾的最长递增子序列（LIS）的长度。
    // dfs(i) = dfs(j) + 1
//    public static int lengthOfLIS(int[] nums) {
//        int len = nums.length;
//        int[] memo = new int[len];
//        int res = 0;
//        for (int i = 0; i < len; i++) { // 判断每一个位置的最长严格递增自序列的长度
//            res = Math.max(res, dfs(i, nums, memo));
//        }
//        return res;
//    }
//    private static int dfs(int i, int[] nums, int[] memo) {
//        if (memo[i] > 0) return memo[i];
//        for (int j = 0; j < i; j++) {
//            if (nums[j] < nums[i])
//                // dfs(j) + 1 表示num[j]结尾的最长递增子序列长度 + 1 (自身)
//                // 枚举完所有的memo[i]的可能性
//                memo[i] = Math.max(memo[i], dfs(j, nums, memo));
//        }
//        return ++memo[i]; // 加上nums[i]本身
//    }
//

    // 递归 -> 递推
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] f = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // f(j) + 1 表示num[j]结尾的最长递增子序列长度 + 1 (自身)
                if (nums[j] < nums[i])
                    f[i] = Math.max(f[i], f[j]);
            }
            res = Math.max(res, ++ f[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }
}
