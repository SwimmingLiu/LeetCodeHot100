package com.swimmingliu.dynamicplan;


/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class Q70 {

    // 1. 先写回溯dfs算法
//    public static int climbStairs(int n) {
//        return dfs(n);
//    }
//    private static int dfs(int i) {
//        if (i <= 1) return 1;
//       return dfs(i - 1) + dfs(i - 2);
//    }

    // 2. memo数组记录已经计算过的第i阶不同方法的情况
//    public static int climbStairs(int n) {
//        int[] memo = new int[n + 1];
//        return dfs(n, memo);
//    }
//    private static int dfs(int i, int[] memo) {
//        if (i <= 1) return 1;
//        if (memo[i] != 0) return memo[i]; // 如果第i阶的不同方法已经计算过了，直接返回
//        return memo[i] = dfs(i - 1, memo) + dfs(i - 2, memo);
//    }
    // 3. 递归改成递推
    // f[i] 的定义和 dfs(i) 的定义是一样的，都表示从 0 爬到 i 有多少种不同的方法
//    public static int climbStairs(int n) {
//        int[] f = new int[n + 1];
//        // 从0爬到0是一种，从0爬到1也是一种
//        f[0] = f[1] = 1;
//        for (int i = 2; i <= n; i ++) {
//            f[i] = f[i - 1] + f[i - 2];
//        }
//        return f[n];
//    }

    // 4. 递推优化
    // 其实只需要一直传递f0和f1
    public static int climbStairs(int n) {
        int f0 = 1, f1 = 1;
        int newF = f1;
        for (int i = 2; i <= n; i ++) {
            newF = f0 + f1;
            f0 = f1;
            f1 = newF;
        }
        return newF;
    }


    public static void main(String[] args) {
        System.out.println(climbStairs(2));
    }
}
