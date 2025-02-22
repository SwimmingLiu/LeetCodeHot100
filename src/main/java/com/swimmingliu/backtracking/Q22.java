package com.swimmingliu.backtracking;

import java.util.*;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class Q22 {

    // 先选 "("，再选 ")"。选")"的是否直接覆盖 ==> 等价于恢复现场
    private static final List<String> res = new ArrayList<>();
    private static char[] path;
    public static List<String> generateParenthesis(int n) {
        path = new char[n * 2];
        dfs(n, 0, 0, 0);
        return res;
    }
    private static void dfs(int n, int left, int right, int index){
        if (left == n && right == n){
            res.add(new String(path));
            return;
        }
        if (left > n || right > n || left < right) return;

        path[index] = '(';
        dfs(n, left + 1, right, index + 1); // 选 "("

        path[index] = ')'; // 覆盖
        dfs(n, left, right + 1, index + 1); // 选 ")"
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(generateParenthesis(n));
    }
}
