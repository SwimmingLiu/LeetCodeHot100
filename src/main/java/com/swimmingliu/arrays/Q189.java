package com.swimmingliu.arrays;

import java.util.Arrays;

/**
 * 轮转数组
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class Q189 {
    // 辅助数组
//    public static void rotate(int[] nums, int k) {
//        int length = nums.length;
//        k = k % length;
//        if (k == 0) return;
//        int p = length - k;
//        int[] temp = new int[length];
//        int j = 0;
//        for (int i = p; i < length; i ++){
//            temp[j++] = nums[i];
//        }
//        for (int i = 0; i < p; i ++){
//            temp[j++] = nums[i];
//        }
//        for (int i = 0; i < length; i ++){
//            nums[i] = temp[i];
//        }
//    }
    // 原地逆序
    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (k == 0) return;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);
    }
    public static void reverse(int[] nums, int left, int right){
        while (left < right){
            int temp = nums[left];
            nums[left ++] = nums[right];
            nums[right --] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
