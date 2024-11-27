package com.swimmingliu.arrays;

import java.util.Arrays;

/**
 * 最大子数组和
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class Q53 {
    // 前缀和 -> 最大区间 = 最大前缀和 - 最小前缀和
//    public static int maxSubArray(int[] nums) {
//        int length = nums.length;
//        if (length == 1) return nums[0];
//        int[] s = new int[length];
//        int res = Integer.MIN_VALUE;
//        int minPreSum = 0;
//        // 构造前缀和
//        s[0] = nums[0];
//        for (int i = 1; i < length; i++) {
//            s[i] = s[i - 1] + nums[i];
//        }
//        for (int i = 0; i < length; i++) {
//            res = Math.max(s[i] - minPreSum, res);
//            minPreSum = Math.min(minPreSum, s[i]);
//        }
//        return res;
//    }
    // DP -> dp[i] 表示以nums[i]结尾的最大连续子序列的和
    public static int maxSubArray(int[] nums) {
        int length = nums.length;
        if (length == 1) return nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < length; i ++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, -1};
        int result = maxSubArray(nums);
        System.out.println(result);
    }
}
