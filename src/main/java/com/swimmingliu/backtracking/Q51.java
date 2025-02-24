package com.swimmingliu.backtracking;

import java.util.*;

/**
 * 51. N 皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class Q51 {
    // DFS + 污染格子(同行同列+斜线)
//    private static final List<List<String>> res = new ArrayList<>();
//    private static final Deque<String> path = new ArrayDeque<>();
//    private static int[][] st;
//    private static final int EMPTY = 0;
//    private static final int QUEEN = -1;
//    public static List<List<String>> solveNQueens(int n) {
//        st = new int[n][n];
//        dfs(0, n);
//        return res;
//    }
//    private static void dfs(int row, int n) {
//        if (row == n) {
//            res.add(path.stream().toList());
//            return;
//        }
//        for (int col = 0; col < n; col++) { // 每一行选一个，污染对应的格子
//            if (st[row][col] != EMPTY) continue;
//            pollute(row, col, n);
//            StringBuilder str = new StringBuilder("Q");
//            for (int i = 0; i < col; i++) str.insert(0, ".");
//            str.append(".".repeat(Math.max(0, n - (col + 1))));
//            path.add(String.valueOf(str));
//            dfs(row + 1, n);
//            recover(row, col, n);
//            path.removeLast();
//        }
//    }
//    // 污染其他格子
//    private static void pollute(int r, int c, int n) {
//        st[r][c] = QUEEN;
//        // 污染同一行或同一列
//        for (int i = 0; i < n; i++) {
//            if (st[i][c] != QUEEN) st[i][c]++;
//            if (st[r][i] != QUEEN) st[r][i]++;
//        }
//        // 判断斜线是否有QUEEN
//        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) { // 斜左上
//            if (st[i][j] != QUEEN) st[i][j]++;
//        }
//        for (int i = r, j = c; i < n && j < n; i++, j++) { // 斜右下
//            if (st[i][j] != QUEEN) st[i][j]++;
//        }
//        for (int i = r, j = c; i >= 0 && j < n; i--, j++) { // 斜右上
//            if (st[i][j] != QUEEN) st[i][j]++;
//        }
//        for (int i = r, j = c; i < n && j >= 0; i++, j--) { // 斜左下
//            if (st[i][j] != QUEEN) st[i][j]++;
//        }
//    }
//    // 恢复现场
//    private static void recover(int r, int c, int n) {
//        st[r][c] = EMPTY;
//        // 污染同一行或同一列
//        for (int i = 0; i < n; i++) {
//            if (st[i][c] > EMPTY) st[i][c]--;
//            if (st[r][i] > EMPTY) st[r][i]--;
//        }
//        // 判断斜线是否有QUEEN
//        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) { // 斜左上
//            if (st[i][j] > EMPTY) st[i][j]--;
//        }
//        for (int i = r, j = c; i < n && j < n; i++, j++) { // 斜右下
//            if (st[i][j] > EMPTY) st[i][j]--;
//        }
//        for (int i = r, j = c; i >= 0 && j < n; i--, j++) { // 斜右上
//            if (st[i][j] > EMPTY) st[i][j]--;
//        }
//        for (int i = r, j = c; i < n && j >= 0; i++, j--) { // 斜左下
//            if (st[i][j] > EMPTY) st[i][j]--;
//        }
//    }

    // DFS + 斜线判断
    private static final List<List<String>> res = new ArrayList<>();
    private static boolean[] diagonalLeft; // 放置皇后的行号加列号
    private static boolean[] diagonalRight; //放置皇后的行号减列号
    private static int[] queens;
    private static boolean[] used; // 判断列是否被占用

    public static List<List<String>> solveNQueens(int n) {
        queens = new int[n];
        used = new boolean[n];
        diagonalLeft = new boolean[2 * n - 1];
        diagonalRight = new boolean[2 * n - 1];
        dfs(0, n);
        return res;
    }

    private static void dfs(int row, int n) {
        if (row == n) {
            List<String> path = new ArrayList<>();
            for (int i : queens) {
                char[] str = new char[n];
                Arrays.fill(str, '.');
                str[i] = 'Q';
                path.add(new String(str));
            }
            res.add(path);
            return;
        }
        for (int col = 0; col < n; col++) {
            // 由于我们保证了每行每列恰好放一个皇后，所以只需检查斜方向。
            // 对于 ↗ 方向的格子，行号加列号是不变的。 (0, 3),(1, 2), (2,1),(3, 0)
            // 对于 ↖ 方向的格子，行号减列号是不变的。 (0,0), (1,1),(2,2)
            // 如果两个皇后，行号加列号相同，或者行号减列号相同，那么这两个皇后互相攻击。
            int leftUp = row + col;
            int rightUp = row - col + n - 1;
            if (used[col] || diagonalLeft[leftUp] || diagonalRight[rightUp]) continue;
            used[col] = true;
            diagonalLeft[leftUp] = true;
            diagonalRight[rightUp] = true;
            queens[row] = col; // 第row行，col列可以放
            dfs(row + 1, n);
            used[col] = false;
            diagonalLeft[leftUp] = false;
            diagonalRight[rightUp] = false;

        }
    }


    public static void main(String[] args) {
        int n = 1;
        System.out.println(solveNQueens(n));
    }
}
