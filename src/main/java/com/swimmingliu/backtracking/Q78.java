package com.swimmingliu.backtracking;

import java.util.*;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class Q78 {

//    // 选不选：是否选取某个元素
//    public static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        Deque<Integer> subset = new ArrayDeque<>();
//        dfs(nums, 0, subset, res);
//        return res;
//    }
//    // 1. 从前往后看，是否选取某个元素, 先看选哪个
//    private static void dfs(int[] nums, int index,  Deque<Integer> subset, List<List<Integer>> res) {
//        if (nums.length == index) {
//            res.add(new ArrayList<>(subset));
//            return;
//        }
//        // 选nums[index]
//        subset.add(nums[index]);
//        dfs(nums,index + 1, subset, res);
//        subset.removeLast(); // 恢复现场
//        // 不选nums[index]
//        dfs(nums, index + 1, subset, res);
//    }
//    // 2. 从前往后看，是否选取某个元素，先看不选哪个
//    private static void dfs(int[] nums, int index,  Deque<Integer> subset, List<List<Integer>> res) {
//        if (nums.length == index) {
//            res.add(new ArrayList<>(subset));
//            return;
//        }
//        // 不选nums[index]
//        dfs(nums, index + 1, subset, res);
//        // 选nums[index]
//        subset.add(nums[index]);
//        dfs(nums,index + 1, subset, res);
//        subset.removeLast(); // 恢复现场
//    }


//    // 二进制枚举法：枚举所有的情况
//    public static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        int len = nums.length;
//        for (int i = 0; i < 1 << len; i++) { // [1,2,3] -> 1 1 1 (三个位置，选或不选)
//            ArrayList<Integer> subset = new ArrayList<>();
//            for (int j = 0; j < len; j++) {
//                // 判断[1, 2, 3]在指定情况下(假如为1 0 1)，哪些位置为1，说明选择了该位置的数
//                // 将i右移j位 & 1，能够判断是否为1
//                if ((i >> j & 1) == 1)
//                    subset.add(nums[j]);
//            }
//            res.add(subset);
//        }
//        return res;
//    }


    private static final List<List<Integer>> res = new ArrayList<>();
    private static final Deque<Integer> subset = new ArrayDeque<>();
    // 枚举每一个要选择的数字
    public static List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private static void dfs(int[] nums, int index) {
        res.add(new ArrayList<>(subset));
        for (int k = index; k < nums.length; k++) {
            subset.add(nums[k]);
            dfs(nums, k + 1); // 枚举下一个
            subset.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets(nums));
    }
}
