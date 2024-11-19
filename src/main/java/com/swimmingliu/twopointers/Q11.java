package com.swimmingliu.twopointers;

/**
 * 盛最多水的容器
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 */
public class Q11 {

    // 双指针：短板向中间移动 -> 可能增大 / 长板向中间移动 -> 一定变小
//    public static int maxArea(int[] height) {
//        // 特判
//        if (height.length == 0) return 0;
//        int l = 0, r = height.length - 1;
//        int result = Math.min(height[l], height[r]) * (r - l);
//        while (l < r){
//            if (height[r] > height[l]) l ++;
//            else r --;
//            result = Math.max(Math.min(height[l], height[r]) * (r - l), result);
//        }
//        return result;
//    }

    // 双指针 优化版
    public static int maxArea(int[] height) {
        // 特判
        if (height.length == 0) return 0;
        int l = 0, r = height.length - 1;
        int result = 0;
        while (l < r){
            int minHeight = Math.min(height[l], height[r]);
            int area = Math.min(height[l], height[r]) * (r - l);
            while(height[l] <= minHeight && l < r) l ++;
            while(height[r] <= minHeight && l < r) r --;
            result = Math.max(result, area);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,8,6,2,5,4,8,3,7};
        int result = maxArea(nums);
        System.out.println(result);

    }
}
