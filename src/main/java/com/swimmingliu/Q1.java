package com.swimmingliu;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 */
public class Q1 {
    // 暴力解
//    static int[] twoSum(int[] nums, int target) {
//        int[] result = new int[2];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (i == j) continue;
//                if (nums[i] + nums[j] == target) {
//                    result[0] = i;
//                    result[1] = j;
//                    return result;
//                }
//            }
//        }
//        return null;
//    }
    // 快排 + 双指针
//    static int[] twoSum(int[] nums, int target) {
//        int[] nums_copy = nums.clone();
//        Arrays.sort(nums);
//        int i = 0, j = nums.length - 1;
//        while (i != j) {
//            if (nums[i] + nums[j] > target) j--;
//            if (nums[i] + nums[j] < target) i++;
//            if (nums[i] + nums[j] == target) break;
//        }
//        for (int k = 0; k < nums_copy.length; k ++)
//            if (nums[i] == nums_copy[k]){
//                i = k;
//                break;
//            }
//        for (int k = 0; k < nums_copy.length; k ++){
//            if (i != k && nums[j] == nums_copy[k]){
//                j = k;
//                break;
//            }
//        }
//        return new int[]{i, j};
//    }
    // HashMap双指针
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int l = 0, r = nums.length - 1;
        while (l <= r){
             if (map.containsKey(target - nums[l])){
                 return new int[]{l, map.get(target - nums[l])};
             }
             map.put(nums[l], l++);
             if (map.containsKey(target - nums[r])){
                 return new int[]{r, map.get(target - nums[r])};
             }
             map.put(nums[r], r--);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 5 ,9};
        int target = 6;
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
