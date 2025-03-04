package com.swimmingliu.dynamicplan;


import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
public class Q322 {
    // 递归 -> dfs(i, j) 表示i前面的数对应的coins[i]加起来和为j的数所需要的硬币个数
    // coins[i] > j, dfs(i, j) = dfs(i - 1, j)
    // coins[i] <= j, dfs(i, j - coins[i]) = min(dfs(i - 1, j), dfs(i, j - i) + 1)
//    private static int[][] memo = new int[13][10000 + 10];
//    static {
//        for (int i = 0; i < memo.length; i++) {
//            Arrays.fill(memo[i], -1);
//        }
//    }
//    public static int coinChange(int[] coins, int amount) {
//        int res = dfs(coins.length - 1, amount, coins);
//        return res < Integer.MAX_VALUE / 2 ? res : -1;
//    }
//    private static int dfs(int i, int j, int[] coins) {
//        if (i < 0 && j == 0) return 0; // 当i=0的时候，也是可以的
//        else if (i < 0) return Integer.MAX_VALUE / 2;
//        if (memo[i][j] != -1) return memo[i][j];
//        if (coins[i] > j)
//            return memo[i][j] = dfs(i - 1, j, coins);
//        else
//            return memo[i][j] = Math.min(dfs(i - 1, j, coins),
//                    dfs(i, j - coins[i], coins) + 1);
//    }

    // 递推 -> f(i, j) 表示i前面的数对应的coins[i]加起来和为j的数所需要的硬币个数
    // coins[i] > j, f(i, j) = f(i - 1, j)
    // coins[i] <= j, f(i, j - coins[i]) = min(f(i - 1, j), f(i, j - i) + 1)
    // 初始化f[0][0] = 0, f[0][k] = ∞
//    public static int coinChange(int[] coins, int amount) {
//        int[][] f = new int[coins.length + 1][amount + 1];
//        for (int i = 0; i < f.length; i++) Arrays.fill(f[0], Integer.MAX_VALUE / 2);
//        f[0][0] = 0;
//        for (int i = 1; i <= coins.length; i++)
//            for (int j = 0; j <= amount; j++) {
//                if (coins[i - 1] > j) f[i][j] = f[i - 1][j];
//                else f[i][j] = Math.min(f[i - 1][j], f[i][j - coins[i - 1]] + 1);
//            }
//        return f[coins.length][amount] < Integer.MAX_VALUE / 2 ?
//                f[coins.length][amount] : -1;
//    }

    // 递推 -> 空间优化
    public static int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int x : coins)
            for (int j = x; j <= amount; j++) {
                f[j] = Math.min(f[j], f[j - x] + 1);
            }
        return f[amount] < Integer.MAX_VALUE / 2 ?
                f[amount] : -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 3));
    }
}
