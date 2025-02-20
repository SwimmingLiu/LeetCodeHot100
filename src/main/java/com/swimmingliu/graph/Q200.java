package com.swimmingliu.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class Q200 {
    // DFS遍历上下左右，标记已遍历过的点，循环dfs每一个元素
    private static char flag = '2'; // flag表示已经边遍历过，且本身为陆地
    private static char water = '0';
    private static char land = '1';
    public static int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == land) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        return res;
    }
    private static void dfs(char[][] grid, int r, int c){
        if (!inArea(grid, r, c)) return; // 不在正常区域内
        if (grid[r][c] != land) return;
        grid[r][c] = flag;
        // 上下左右
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    private static boolean inArea(char[][] grid, int r, int c){
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }
}
