package com.swimmingliu.dynamicplan;


import java.util.Arrays;
import java.util.Map;

/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；
 * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
public class Q279 {
    // 递归 -> dfs(i, j) 表示前i个数里面，完全平方数的和为j，至少需要选几个完全平方数
    // 对于第i个位置，如果j < i^2，则 dfs(i, j) = dfs(i - 1, j)，不能选第i个数
    // 如果i^2 <= j, dfs(i, j) = min(dfs(i - 1, j), dfs(i,j - i^2) + 1)
    // dfs(i,j - i^2) 表示表示前i个数里面，完全平方数的和为 j-i^2 的选法 (因为i在前面也可以被选)。
    // 然后再加上1，最后选上i了
    // memo[n^0.5][n] 存储所有的记录
//    private static int[][] memo = new int[110][10010];
//    static {
//        for (int[] row : memo) {
//            Arrays.fill(row, -1);
//        }
//    }
//    private static int dfs(int i, int j){
//        // dfs(0, 0) 表示已经没数可以选了
//        if (i == 0 && j == 0) return 0;
//        // 表示该方式找不到，前i个数里面，完全平方数的和为j的选法
//        else if (i == 0) return Integer.MAX_VALUE;
//        if (memo[i][j] != -1) return memo[i][j];
//        if (j < i * i) return memo[i][j] = dfs(i - 1, j);
//        return memo[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - i * i) + 1);
//    }
//    public static int numSquares(int n) {
//        return dfs((int)Math.sqrt(n), n);
//    }

    // 递归 -> 递推
    // 对于第i个位置，如果j < i^2，则 f(i, j) = f(i - 1, j)，不能选第i个数
    // 如果i^2 <= j, f(i, j) = min(f(i - 1, j), f(i,j - i^2) + 1)
    // f(i,j - i^2) 表示表示前i个数里面，完全平方数的和为 j-i^2 的选法 (因为i在前面也可以被选)。
    // 初始化 f[0][0] = 0, f[0][k] = ∞， k = 1,....,n - 1
//    public static int numSquares(int n) {
//        int sqrt = (int) Math.sqrt(n);
//        int[][] f = new int[sqrt + 1][n + 1];
//        Arrays.fill(f[0], Integer.MAX_VALUE);
//        f[0][0] = 0;
//        for (int i = 1; i <= sqrt; i++) {
//            for (int j = 0; j <= n; j++) {
//                if (j < i * i) f[i][j] = f[i - 1][j];
//                else f[i][j] = Math.min(f[i - 1][j], f[i][j - i * i] + 1);
//            }
//        }
//        return f[sqrt][n];
//    }
//
    // 递推 -> 空间优化
    public static int numSquares(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1);
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
