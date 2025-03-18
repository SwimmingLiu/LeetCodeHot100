package com.swimmingliu.dynamicplanwithmultid;


import java.util.Arrays;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：
 * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class Q1143 {
    // 记忆化搜索 + 递归
    // 假设text1和text2对应的char数组分别为s和t
    // dfs(i, j) 表示s的前i个元素中(范围是[0, i])和t的前j个元素中(范围是[0, j])的最长公共子序列的个数
    // 计算dfs(i, j)，需要观察s[i]和t[j]的关系
    // 如果s[i] = t[j], dfs(i, j) = max(dfs(i - 1, j), dfs(i, j - 1), dfs(i - 1, j - 1) + 1)
    // 如果 s[i] != t[j], dfs(i, j) = max(dfs(i - 1, j), dfs(i, j - 1), dfs(i - 1, j - 1))
    // 整合一下 dfs(i, j) = = max(dfs(i - 1, j), dfs(i, j - 1), dfs(i - 1, j - 1) + (s[i] == t[j]))
    // 初始化：dfs(-1, 0) = dfs(0, -1) = 0
//    public static int longestCommonSubsequence(String text1, String text2) {
//        char[] s = text1.toCharArray();
//        char[] t = text2.toCharArray();
//        int n = s.length;
//        int m = t.length;
//        int[][] memo = new int[n + 1][m + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dfs(n - 1, m - 1, memo, s, t);
//    }
//    private static int dfs(int i, int j, int[][] memo, char[] s, char[] t) {
//        if (i < 0 || j < 0) return 0;
//        if (memo[i][j] != -1) return memo[i][j];
//        int pre = dfs(i - 1, j - 1, memo, s, t);
//        int preI = dfs(i - 1, j, memo, s, t);
//        int preJ = dfs(i, j - 1, memo, s, t);
//        if (s[i] == t[j])
//            return memo[i][j] = Math.max(Math.max(preI, preJ), pre + 1);
//        return memo[i][j] = Math.max(Math.max(preI, preJ), pre);
//    }


    // 递归 -> 递推
    // f(i, j) 表示s的前i个元素中(范围是[0, i])和t的前j个元素中(范围是[0, j])的最长公共子序列的个数
    // 计算f(i, j)，需要观察s[i]和t[j]的关系
    // 如果s[i] = t[j], f(i, j) = max(f(i - 1, j),f(i, j - 1), f(i - 1, j - 1) + 1)
    // 如果 s[i] != t[j], f(i, j) = max(f(i - 1, j), f(i, j - 1), f(i - 1, j - 1))
    // 整合一下 f(i, j) = max(f(i - 1, j), f(i, j - 1), f(i - 1, j - 1) + (s[i] == t[j]))
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        int n = s.length;
        int m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j ++){
                int max = Math.max(f[i][j + 1], f[i + 1][j]);
                if (s[i] == t[j])
                    f[i + 1][j + 1] = Math.max(max, f[i][j] + 1);
                else f[i + 1][j + 1] = Math.max(max, f[i][j]);
            }
        }
        return f[n][m];
    }


    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
