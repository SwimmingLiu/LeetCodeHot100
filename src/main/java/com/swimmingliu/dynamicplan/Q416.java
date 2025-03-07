package com.swimmingliu.dynamicplan;


import java.util.Arrays;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class Q416 {
    // 假设nums的元素和为s, 其实本题的本质就是判断是否存在多个元素的和组成为 s / 2
    // 如果s为奇数，那么直接判定不可能存在。 如果s为偶数，则需要判断
    // dfs(i, j) 表示在[0, i] 当中能选出和恰好为j的子集
    // dfs(i, j) 的计算方法
    // 如果 i > j, 那说明i不满足条件，不能放入该子集。 -> dfs(i, j) = dfs(i - 1, j)
    // 如果 i <= j, 说明i可以满足条件 -> dfs(i, j) = dfs(i - 1, j - nums[i])
    // dfs(i, j) = dfs(i - 1, j) || dfs(i - 1, j - nums[i])
    // 边界条件： 当i < 0, j = 0 -> 已经找到了, 当i < 0, j > 0, 还没有找到
    // 答案: dfs(len - 1, s / 2)
//    public static boolean canPartition(int[] nums) {
//        int s = Arrays.stream(nums).sum();
//        if (s % 2 != 0) return false;
//        int[][] memo = new int[nums.length][s / 2 + 1];
//        for (int i = 0; i < memo.length; i++) {
//            Arrays.fill(memo[i], -1);
//        }
//        return dfs(nums.length - 1, s / 2, nums, memo);
//    }
//    private static boolean dfs(int i, int j, int[] nums, int[][] memo) {
//        if (i < 0) return j == 0; // 判断j是否被减完了
//        if (memo[i][j] != -1) return memo[i][j] == 1;
//        boolean res = j >= nums[i] && dfs(i - 1, j - nums[i], nums, memo) || dfs(i - 1, j, nums, memo);
//        memo[i][j] = res ? 1 : 0;
//        return res;
//    }

    // 递归 -> 递推
    // 当 j >= nums[i], f[i][j] = f[i - 1][j - nums[i]], 选i
    // 当 j < nums[i], f[i][j] = f[i - 1][j], 不选i
    // 初始化, f[0][0] = true, f[0][k] = false
    public static boolean canPartition(int[] nums) {
        int s = Arrays.stream(nums).sum();
        if (s % 2 != 0) return false;
        boolean[][] f = new boolean[nums.length + 1][s / 2 + 1];
        for (int i = 0; i < f.length; i++) {
            Arrays.fill(f[i], false);
        }
        f[0][0] = true;
        for (int i = 0; i < nums.length; i ++){
            for (int j = 0; j < s / 2 + 1; j ++){
               f[i + 1][j] = j >= nums[i] && f[i][j - nums[i]] || f[i][j];
            }
        }
        return f[nums.length][s / 2];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }
}
