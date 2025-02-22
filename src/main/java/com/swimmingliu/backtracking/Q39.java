package com.swimmingliu.backtracking;

import java.util.*;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class Q39 {
//    // DFS所有符合条件的结果，最后对子list排序，然后去重
//    private static final Deque<Integer> path = new ArrayDeque<>();
//    private static final List<List<Integer>> res = new ArrayList<>();
//    private static boolean[] used = new boolean[50];
//    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
//        dfs(candidates, target);
//        for (int i = 0; i < res.size(); i ++){
//            Collections.sort(res.get(i));
//        }
//        HashSet<List<Integer>> hashSet = new HashSet<>(res);
//        return hashSet.stream().toList();
//    }
//    private static void dfs(int[] candidates, int target) {
//        int sum = path.stream().mapToInt(Integer::intValue).sum();
//        if (sum == target) {
//            res.add(new ArrayList<>(path));
//            used[path.getFirst()] = true;
//        }
//        if (sum >= target) return;
//        for (int i = 0; i < candidates.length; i++) {
//            if (used[candidates[i]]) continue;
//            path.add(candidates[i]);
//            dfs(candidates, target);
//            path.removeLast();
//        }
//    }

//    // 排序 + 剪枝
//    private static final Deque<Integer> path = new ArrayDeque<>();
//    private static final List<List<Integer>> res = new ArrayList<>();
//    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        dfs(0, candidates, target, 0);
//        return res;
//    }
//    private static void dfs(int index, int[] candidates, int target, int sum) {
//        if (sum == target) {
//            res.add(new ArrayList<>(path));
//        }
//        if (sum >= target) return;
//        for (int k = index; k < candidates.length; k++) { // 排序之后用for循环的起始位置进行剪枝
//            path.add(candidates[k]);
//            sum += candidates[k];
//            dfs(k, candidates, target, sum);
//            path.removeLast();
//            sum -= candidates[k];
//        }
//    }

    // 选或不选 => 先不选，再选，恢复现场
    private static final Deque<Integer> path = new ArrayDeque<>();
    private static final List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(0, candidates, target, 0);
        return res;
    }
    private static void dfs(int index, int[] candidates, int target, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
        }
        if (index == candidates.length || sum >= target) return;

        // 先不选
        dfs(index + 1, candidates, target, sum);

        // 选candidates[index]
        path.add(candidates[index]);
        sum += candidates[index];
        dfs(index, candidates, target, sum);
        sum -= candidates[index];
        path.removeLast();
    }

    public static void main(String[] args) {
        int[] candidates = {7,3,2};
        int target = 18;
        System.out.println(combinationSum(candidates, target));
    }
}
