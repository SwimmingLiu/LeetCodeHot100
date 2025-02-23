package com.swimmingliu.backtracking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Q79 {

    // 对所有的单元格进行DFS搜索相邻单元格，如果搜索到符合条件的路径，则退出
//    private static boolean[][] used;
//    public static boolean exist(char[][] board, String word) {
//        for (int i = 0; i < board.length; i++)
//            for (int j = 0; j < board[0].length; j++) {
//                used = new boolean[board.length][board[0].length];
//                if (dfs(i, j, board, 0, word.toCharArray()))
//                    return true;
//            }
//        return false;
//    }
//    private static boolean dfs(int row, int col, char[][] board, int index, char[] word) {
//        if (index == word.length) return true;
//        if (!inArea(row, col, board)) return false;
//        if (word[index] != board[row][col] || used[row][col]) return false;
//        else used[row][col] = true;
//        boolean up = dfs(row - 1, col, board, index + 1, word);
//        boolean down = dfs(row + 1, col, board, index + 1, word);
//        boolean left = dfs(row, col - 1, board, index + 1, word);
//        boolean right = dfs(row, col + 1, board, index + 1, word);
//        used[row][col] = false; // 恢复现场
//        return up || down || left || right;
//    }
//    private static boolean inArea(int row, int col, char[][] board) {
//        return 0 <= row && row < board.length && 0 <= col && col < board[0].length;
//    }

    // 优化版本：统计出现次数 + 按次数决定从哪头开始
    public static boolean exist(char[][] board, String word) {
        char[] wordList = word.toCharArray();
        char[] count = new char[128]; // 记录单元格所有的字母
        char[] wordCount = new char[128]; // 记录word所有字母
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                count[board[i][j]] ++;
        // 优化一: 统计出现次数，如果单元格对应字母出现的次数 < word当中的次数，则不可能符合规则
        for (char c : wordList)
             if (++wordCount[c] > count[c]) return false;
        // 优化二: 按次数决定从哪头开始，从出现次数小的开始，更容易找到
        if (count[wordList[0]] > count[wordList[wordList.length - 1]])
            wordList = new StringBuffer(word).reverse().toString().toCharArray();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(i, j, board, 0, wordList))
                    return true;
            }
        return false;
    }
    private static boolean dfs(int row, int col, char[][] board, int index, char[] word) {
        if (index == word.length) return true;
        if (!inArea(row, col, board)) return false;
        if (word[index] != board[row][col] || board[row][col] == 0) return false;
        else board[row][col] = 0;
        boolean up = dfs(row - 1, col, board, index + 1, word);
        boolean down = dfs(row + 1, col, board, index + 1, word);
        boolean left = dfs(row, col - 1, board, index + 1, word);
        boolean right = dfs(row, col + 1, board, index + 1, word);
        board[row][col] = word[index]; // 恢复现场
        return up || down || left || right;
    }
    private static boolean inArea(int row, int col, char[][] board) {
        return 0 <= row && row < board.length && 0 <= col && col < board[0].length;
    }



    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCESEEEFS";
        System.out.println(exist(board, word));
    }
}
