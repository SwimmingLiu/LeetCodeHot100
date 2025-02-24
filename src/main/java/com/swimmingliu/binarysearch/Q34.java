package com.swimmingliu.binarysearch;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
 * 请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Q34 {
    // 先二分找定位，然后二分找左边，再二分找右边 (二分, 二分， 还是**的二分)
//    public static int[] searchRange(int[] nums, int target) {
//        if (nums.length == 0) return new int[]{-1, -1};
//        // 1. 二分查询target在nums当中的位置
//        int index = binarySearch(nums, target, 0, nums.length - 1);
//        if (index >= nums.length || nums[index] != target) return new int[]{-1, -1};
//        int start = index, end = index, x = index;
//        // 2. 向左查询：nums的初始位置
//        while (x >= 0 && nums[x] == target) {
//            x = binarySearch(nums, target, 0, x);
//            if (nums[x] != target) break;
//            start = x;
//            x--;
//        }
//        x = index; // 恢复初始值
//        // 3. 向右查询: nums的结束位置
//        while (x < nums.length && nums[x] == target) {
//            x = binarySearch(nums, target, x, nums.length - 1);
//            if (nums[x] != target) break;
//            end = x;
//            x ++;
//        }
//        return new int[]{start, end};
//    }
//    private static int binarySearch(int[] nums, int target, int left, int right) {
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] < target) left = mid + 1;
//            else right = mid - 1;
//        }
//        return left;
//    }

    // 闭区间二分找target第一次出现的位置，然后找target + 1的位置再减一，就是target最右边的位置
    public static int[] searchRange(int[] nums, int target) {
        int start = binarySearch(nums, target,0, nums.length - 1);
        if (start == nums.length || nums[start] != target) return new int[]{-1, -1};
        // target 一定在target + 1的左边一个位置。如果target + 1不存在，也会返回其应该插入的位置，则左边还是target
        int end = binarySearch(nums, target + 1, start, nums.length - 1) - 1;
        return new int[]{start, end};
    }

    private static int binarySearch(int[] nums, int target, int left, int right){
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {2,2};
        int target = 2;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }
}
