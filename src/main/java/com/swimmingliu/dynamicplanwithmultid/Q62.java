package com.swimmingliu.dynamicplanwithmultid;


import java.util.Arrays;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
public class Q62 {
    // 记忆搜索 -> dfs(i, j) 表示从[0, 0]按照规则到[i, j]位置的路径数
    // 计算 dfs(i, j)
    // 假设 dfs(i, j) = 0
    // 如果 dfs(i - 1, j) != 0, dfs(i, j) += dfs(i - 1, j) + 1
    // 如果 dfs(i, j - 1) != 0, dfs(i, j) += dfs(i, j - 1) + 1
    // dfs(0, 0) = 1 -> 从[0,0]到[0,0]有本身一条路径
//    private static int row;
//    private static int col;
//    public static int uniquePaths(int m, int n) {
//        int[][] memo = new int[m + 1][n + 1];
//        row = m;
//        col = n;
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dfs(m - 1, n - 1, memo);
//    }
//    private static int dfs(int i, int j, int[][] memo) {
//        if (!inArea(i, j)) return -1;
//        if (i == 0 && j == 0) return 1;
//        if (memo[i][j] != -1) return memo[i][j];
//        int up = dfs(i - 1, j, memo);
//        int left = dfs(i, j - 1, memo);
//        return memo[i][j] = Math.max(up, 0)+ Math.max(left, 0);
//    }
//    private static boolean inArea(int i, int j) {
//        return 0 <= i && i < row && 0 <= j && j < col;
//    }

    // 递推 -> f(i, j) 表示从[0, 0]按照规则到[i, j]位置的路径数
    // 计算 f(i, j)
    // 假设 f(i, j) = 0
    // 如果 f(i - 1, j) != 0, f(i, j) += f(i - 1, j) + 1
    // 如果 f(i, j - 1) != 0, f(i, j) += f(i, j - 1) + 1
    // f(0, 0) = 1, f(0, k) = -1
    public static int uniquePaths(int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        f[1][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                int up = f[i - 1][j];
                int left = f[i][j - 1];
                f[i][j] = Math.max(up , 0) + Math.max(left, 0);
            }
        }
        return f[m][n];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }
}
