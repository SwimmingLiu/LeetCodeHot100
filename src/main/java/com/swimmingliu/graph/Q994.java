package com.swimmingliu.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. 腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class Q994 {
    private static final int empty = 0;
    private static final int fresh = 1;
    private static final int rot = 2;

    // 最小分钟数 -> 最短路径问题 -> BFS (因为如果用dfs，不能同时计算多个rot水果，但是BFS可以按层来计算)
    public static int orangesRotting(int[][] grid) {
        int depth = 0;
        int count = 0; // fresh水果个数
        Queue<int[]> queue = new LinkedList<>(); // 所有rot水果坐标
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == fresh) count++;
                else if (grid[i][j] == rot) queue.add(new int[]{i, j});
            }
        while (count > 0 && !queue.isEmpty()) {
            depth ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] coord = queue.poll();
                int row = coord[0], col = coord[1];
                boolean up = row - 1 >= 0 && grid[row - 1][col] == fresh;
                boolean down = row + 1 < grid.length && grid[row + 1][col] == fresh;
                boolean left = col - 1 >= 0 && grid[row][col - 1] == fresh;
                boolean right = col + 1 < grid[0].length && grid[row][col + 1] == fresh;
                if (up) {
                    grid[row - 1][col] = rot;
                    count--;
                    queue.add(new int[]{row - 1, col});
                }
                if (down) {
                    grid[row + 1][col] = rot;
                    count --;
                    queue.add(new int[]{row + 1, col});
                }
                if (left) {
                    grid[row][col - 1] = rot;
                    count --;
                    queue.add(new int[]{row, col - 1});
                }
                if (right){
                    grid[row][col + 1] = rot;
                    count --;
                    queue.add(new int[]{row, col + 1});
                }
            }
        }
        if (count > 0) return -1; // 仍然有新鲜水果
        return depth;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(orangesRotting(grid));
    }
}
