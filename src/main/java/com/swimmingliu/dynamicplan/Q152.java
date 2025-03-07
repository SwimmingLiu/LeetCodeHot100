package com.swimmingliu.dynamicplan;


import java.util.Arrays;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 */
public class Q152 {
    // 假设当前nums为[2, 3, -2, 4], f_max[i] 表示以nums[i]为子数组的最后一个数，该子数组的最大乘积
    // f_max[3]可以分为两种情况讨论
    // 1. num[3] = 4 单独作为子数组
    // 2. 让nums[3]作为子数组的最后一个数，其对应的最大乘积为nums[3] * f_max[2]
    // 再次假设当前nums为[2, 3, -2, -4]，f_max[3]可以分为两种情况讨论
    // 1. num[3] = -4 单独作为子数组
    // 2. 让nums[3]作为子数组的最后一个数，其对应的最大乘积为nums[3] * f_min[2] (负负得正)、
    // 假设 nums[i] = x
    // f_max[i] = max(f_max[i - 1] * x, f_min[i - 1] * x, x), 其中f_min[i - 1] * x是x为负数的情况
    // f_min[i] = min(f_max[i - 1] * x, f_min[i - 1] * x, x), 其中f_min[i - 1] * x是x为正数的情况
//    public static int maxProduct(int[] nums) {
//        int len = nums.length;
//        int[] fMax = new int[len];
//        int[] fMin = new int[len];
//        fMin[0] = fMax[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            int x = nums[i];
//            fMax[i] = Math.max(Math.max(fMax[i - 1] * x, fMin[i - 1] * x), x);
//            fMin[i] = Math.min(Math.min(fMax[i - 1] * x, fMin[i - 1] * x), x);
//        }
//        return Arrays.stream(fMax).max().getAsInt();
//    }

    // 空间优化
    public static int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int fMax = 1;
        int fMin = 1;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int fMaxCopy = fMax;
            fMax = Math.max(Math.max(fMax * x, fMin * x), x);
            fMin = Math.min(Math.min(fMaxCopy * x, fMin * x), x);
            ans = Math.max(ans, fMax);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {-4,-3,-2};
        System.out.println(maxProduct(nums));
    }
}
