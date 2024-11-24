package com.swimmingliu.substring;


import java.util.Arrays;
import java.util.HashMap;

/**
 * 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 1 <= nums.length <= 2 * 10e4
 * -1000 <= nums[i] <= 1000
 * -10e7 <= k <= 10e7
 */
public class Q560 {
    // 前缀和
//    public static int subarraySum(int[] nums, int k) {
//        int len = nums.length;
//        if (len == 1) return nums[0] == k ? 1 : 0;
//        int res = 0;
//        int[] s = new int[len + 10];
//        s[0] = nums[0];
//        for (int i = 1; i < len; i ++)
//            s[i] = s[i - 1] + nums[i];
//        for (int l = 0; l < len; l ++){
//            for (int r = l; r < len; r ++)
//                if (s[r] - s[l] + nums[l] == k)
//                    res ++;
//        }
//        return res;
//    }
    // 前缀和 + HashMap
    public static int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) return nums[0] == k ? 1 : 0;
        int res = 0;
        int[] s = new int[len + 10];
        // 前缀和
        for (int i = 0; i < len; i ++)
            s[i + 1] = s[i] + nums[i];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= len; i ++){
            int subCount = map.getOrDefault(s[i] - k, 0);
            res += subCount;
            map.put(s[i], map.getOrDefault(s[i], 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,-1,0};
        int k = 0;
        int result = subarraySum(nums, k);
        System.out.println(result);
    }
}
