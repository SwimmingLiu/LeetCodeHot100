package com.swimmingliu.dynamicplan;


import java.util.Arrays;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。 偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class Q198 {

    // 最后一个元素选和不选：不选, dfs(i - 1)， 选, dfs(i - 2) + nums[i]
//    public static int rob(int[] nums) {
//        return dfs(nums.length - 1, nums);
//    }
//    private static int dfs(int i, int[] nums) {
//        if (i < 0) return 0;
//        return Math.max(dfs(i - 1, nums), dfs(i - 2, nums) + nums[i]);
//    }
    // 最后一个元素选和不选：不选, dfs(i - 1)， 选, dfs(i - 2) + nums[i]
    // 增加memo数组进行优化，记录所有计算过的位置, memo[i] 表示第i个位置，能够偷窃到的最高金额
//    public static int rob(int[] nums) {
//        int[] memo = new int[nums.length];
//        Arrays.fill(memo, -1);
//        return dfs(nums.length - 1, nums, memo);
//    }
//    private static int dfs(int i, int[] nums, int[] memo) {
//        if (i < 0) return 0; // 递归到没有房子位置
//        if (memo[i] != -1) return memo[i];
//        return memo[i] = Math.max(dfs(i - 1, nums, memo), dfs(i - 2, nums, memo) + nums[i]);
//    }

    // 递归变递推
    // f[i] = max(f[i - 1], f[i - 2] + nums[i])
//    public static int rob(int[] nums) {
//        int[] f = new int[nums.length + 2];
//        for (int i = 0; i < nums.length; i++) {
//            f[i + 2] = Math.max(f[i + 1], f[i] + nums[i]);
//        }
//        return f[(nums.length - 1) + 2];
//    }
    // 递归变递推 -> 常量优化
    public static int rob(int[] nums) {
        int f0 = 0, f1 = 0; // f1表示的是最后一个位置对应能偷到多少米
        for (int i = 0; i < nums.length; i++) {
            int newF = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1};
        System.out.println(rob(nums));
    }
}
