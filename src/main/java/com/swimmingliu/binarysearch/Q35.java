package com.swimmingliu.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class Q35 {
    // 二分查找 -> 递归版本
//    public static int searchInsert(int[] nums, int target) {
//        return binarySearch(0, nums.length - 1, nums, target);
//    }
//
//    private static int binarySearch(int left, int right, int[] nums, int target){
//        if (left > right) return left;
//        int mid = (left + right) / 2;
//        if (nums[mid] == target) return mid;
//        if (nums[mid] < target) return binarySearch(left + 1, right, nums, target);
//        else return binarySearch(left, right - 1, nums, target);
//    }

    // 二分查找 -> 循环版本
    public static int searchInsert(int[] nums, int target) {
       int left = 0, right = nums.length - 1;
       while (left <= right){
           int mid = (left + right) / 2;
           if (nums[mid] == target) return mid;
           else if (nums[mid] < target) left ++;
           else right --;
       }
       return left;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        System.out.println(searchInsert(nums, target));
    }
}
