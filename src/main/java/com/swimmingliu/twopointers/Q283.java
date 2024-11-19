package com.swimmingliu.twopointers;

import java.util.Arrays;

/**
 * 移动零
 */
public class Q283 {

    // 辅助数组第一版
//    public static void moveZeroes(int[] nums) {
//        // 特判
//        if (nums.length == 0 || nums.length == 1) return;
//        int[] subnums = new int[nums.length];
//        int r = 0, start = 0;
//        for (int l = 0; l < nums.length; l++) {
//            if (nums[l] == 0) subnums[r++] = 0;
//        }
//        for (int l = 0; l < nums.length; l++) {
//            if (nums[l] != 0) {
//                if (start == 0) start = r;
//                subnums[r++] = nums[l];
//            }
//        }
//        // 判断是否为非零序列
//        if (start == 1) return;
//        for (int i = 0; i < nums.length - start; i++) {
//            nums[i] = subnums[i + start];
//        }
//        for (int i = nums.length - start; i < nums.length; i++) {
//            nums[i] = 0;
//        }
//    }

    // 双指针记录非零个数
    public static void moveZeroes(int[] nums) {
        // 特判
        if (nums.length == 0 || nums.length == 1) return;
        int j = 0;
        // 将非零的均移到前方
        for (int i = 0; i < nums.length; i ++){
            if (nums[i] != 0) nums[j++] = nums[i];
        }
        // 剩下的全部补为0
        for (int i = j; i < nums.length; i ++) nums[i] = 0;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,2,12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

    }
}
