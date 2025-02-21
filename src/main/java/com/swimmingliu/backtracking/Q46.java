package com.swimmingliu.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Q46 {

    // 1. 遍历nums所有元素进行DFS搜索，以深度来判断临界位置
    // 2. dfs之前，修改元素的used状态为true，同时将元素放入path栈中
    // 3. dfs之后，恢复元素的used状态为false，同时将元素从path栈移除 (恢复现场)
    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, used, path, res);
        return res;
    }

    private static void dfs(int[] nums, int depth, boolean[] used, Stack<Integer> path, List<List<Integer>> res) {
        if (nums.length == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < used.length; i ++){
            if (used[i]) continue;
            path.push(nums[i]);
            used[i] = true;
            dfs(nums, depth + 1, used, path, res);
            // 恢复现场
            path.pop();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(permute(nums));
    }
}
