package com.swimmingliu.dynamicplanwithmultid;


import java.util.Arrays;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
public class Q64 {
    // 记忆搜索递归 -> dfs(i, j) 表示[0,0]到[i,j]的最短路径长度
    // 计算dfs(i, j)
    // up = dfs(i - 1, j), left = dfs(i, j - 1)
    // up != 0 && left != 0 -> dfs(i, j) = min(up, left) + grid[i][j]
    // up == -1, dfs(i, j) = left + grid[i][j]
    // left == -1, dfs(i, j) = up + grid[i][j]
    // dfs(0, 0) = grid[0][0]
//    public static int minPathSum(int[][] grid) {
//        int row = grid.length;
//        int col = grid[0].length;
//        int[][] memo = new int[row + 1][col + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dfs(row - 1, col - 1, grid, memo);
//    }
//    private static int dfs(int i, int j, int[][] grid, int[][] memo){
//        if (!inArea(i, j, grid)) return -1;
//        if (i == 0 && j == 0) return grid[i][j];
//        if (memo[i][j] != -1) return memo[i][j];
//        int up = dfs(i - 1, j, grid, memo);
//        int left = dfs(i, j - 1, grid, memo);
//        if (up == -1) return memo[i][j] = left + grid[i][j];
//        else if (left == -1) return memo[i][j] = up + grid[i][j];
//        else return memo[i][j] = Math.min(up, left) + grid[i][j];
//    }
//    private static boolean inArea(int i, int j, int[][] grid){
//        return 0 <= i && i < grid.length &&
//                0 <= j && j < grid[0].length;
//    }
    // 递推 -> f(i, j) 表示[0,0]到[i,j]的最短路径长度
    // 计算f(i, j)
    // up = f(i - 1, j), left = f(i, j - 1)
    // up != 0 && left != 0 -> f(i, j) = min(up, left) + grid[i][j]
    // up == -1, f(i, j) = left + grid[i][j]
    // left == -1, f(i, j) = up + grid[i][j]
    // f(1, 1) = grid[0][0]
    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] f = new int[row + 1][col + 1];
        for (int[] ints : f) {
            Arrays.fill(ints, -1);
        }
        f[1][1] = grid[0][0];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) continue;
                int up = f[i][j + 1];
                int left = f[i + 1][j];
                if (up == -1) f[i + 1][j + 1] = left + grid[i][j];
                else if (left == -1) f[i + 1][j + 1] = up + grid[i][j];
                else f[i + 1][j + 1] = Math.min(up, left) + grid[i][j];
            }
        return f[row][col];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }
}
