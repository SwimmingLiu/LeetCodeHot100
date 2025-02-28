package com.swimmingliu.greedy;


import java.util.Arrays;

/**
 * 55. 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 */
public class Q55 {
    // 从后向前看：每次判断是否有格子能跳到最后一格，不断缩减范围，直到缩减到第一格为止
    // 判断是否能跳到最后一格：num[i] >= end - i (最后一格和当前格的距离)
//    public static boolean canJump(int[] nums) {
//        return canJump(nums, 0, nums.length - 1);
//    }
//    public static boolean canJump(int[] nums, int start, int end) {
//        if (start == end) return true;
//        for (int i = end - 1; i >= start; i --) {
//            if (nums[i] >= end - i)
//                return canJump(nums, 0, i);
//        }
//        return false;
//    }

    // 挨个跳，看能跳多远： 如果当前格子能往前跳3步，可以看一下前三步，最大能跳多远。
    // 然后一直往前跳，如果最后跳不到终点，就g了
    public static boolean canJump(int[] nums) {
        int step = 0; // 最远能走的距离
        for (int i = 0; i < nums.length; i ++){
            if (i > step) return false; // 当前格子比能走的最远路要远
            step = Math.max(step, i + nums[i]); // 更新最远能跳的步数 = Max(step, 当前格子 + 它能跳的距离)
            if (step >= nums.length - 1) return true;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(canJump(nums));
    }
}
