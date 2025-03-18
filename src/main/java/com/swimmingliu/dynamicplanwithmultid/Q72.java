package com.swimmingliu.dynamicplanwithmultid;


import java.util.Arrays;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class Q72 {
    // 记忆化搜索 -> 递归
    // 假设word1和word2对应的char数组是s和t
    // dfs(i, j)表示的是s的前i个元素(范围为[0, i])和t的前j个元素(范围为[0, j])当中把s变成t最少需要操作几次
    // 删除s[i] -> dfs(i - 1, j), 替换s[i] -> dfs(i - 1, j - 1) + 1
    // 插入s[i] 相当于删除 t[i] -> dfs(i, j - 1), 比如s为"abc", t为"abcd", s当中插入"d", 相当于t当中删除"d"
    // 如果s[i] == t[j], dfs(i, j) = dfs(i - 1, j - 1) 不做任何操作
    // 如果s[i] != t[j], dfs(i, j) = min(dfs(i - 1, j), dfs(i, j -1), dfs(i - 1, j - 1))  + 1
    // 初始化 dfs(-1, j) = j + 1 (相当于s当中没有元素可以操作了，s需要操作j + 1次插入)
    // dfs(i, -1) = i + 1 (需要操作i + 1次)
//    public static int minDistance(String word1, String word2) {
//        int n = word1.length();
//        int m = word2.length();
//        char[] s = word1.toCharArray();
//        char[] t = word2.toCharArray();
//        int[][] memo = new int[n + 1][m + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dfs(n - 1, m - 1, memo, s, t);
//    }
//
//    private static int dfs(int i, int j, int[][] memo, char[] s, char[] t) {
//        if (i < 0) return j + 1;
//        if (j < 0) return i + 1;
//        if (memo[i][j] != -1) return memo[i][j];
//        int pre = dfs(i - 1, j - 1, memo, s, t);
//        if (s[i] == t[j]) return memo[i][j] = pre;
//        else return memo[i][j] = Math.min(Math.min(dfs(i - 1, j, memo, s, t),
//                dfs(i, j - 1, memo, s, t)), pre) + 1;
//    }
        // 递归 -> 递推
    // f(i, j)表示的是s的前i个元素(范围为[0, i])和t的前j个元素(范围为[0, j])当中把s变成t最少需要操作几次
    // 删除s[i] -> f(i - 1, j), 替换s[i] -> f(i - 1, j - 1) + 1
    // 插入s[i] 相当于删除 t[i] -> f(i, j - 1), 比如s为"abc", t为"abcd", s当中插入"d", 相当于t当中删除"d"
    // 如果s[i] == t[j], f(i, j) = f(i - 1, j - 1) 不做任何操作
    // 如果s[i] != t[j], f(i, j) = min(f(i - 1, j), f(i, j -1), f(i - 1, j - 1))  + 1
    // 初始化 f(-1, j) = j + 1 (相当于s当中没有元素可以操作了，s需要操作j + 1次插入)
    // f(i, -1) = i + 1 (需要操作i + 1次)
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int[][] f = new int[n + 1][m + 1];
        for (int j = 1; j <= m; j++) {
            f[0][j] = j;
        }
        for (int i = 0; i < n; i++) {
            f[i + 1][0] = i + 1;
            for (int j = 0; j < m; j ++){
                if (s[i] == t[j]) f[i + 1][j + 1] = f[i][j];
                else f[i + 1][j + 1] = Math.min(Math.min(f[i][j + 1], f[i + 1][j]), f[i][j]) + 1;
            }
        }
        return f[n][m];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(minDistance(text1, text2));
    }
}
