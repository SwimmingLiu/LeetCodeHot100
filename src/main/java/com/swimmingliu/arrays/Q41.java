package com.swimmingliu.arrays;

/**
 * 缺失的第一个正数
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * 示例 3：
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 * <p>
 * 1 <= nums.length <= 10e5
 * -2e31 <= nums[i] <= 2e31 - 1
 */
public class Q41 {
    // 辅助数组
//    public static int firstMissingPositive(int[] nums) {
//        int length = nums.length;
//        int[] count = new int[length + 1];
//        int res = 1;
//        for (int num : nums) {
//            if (num > 0 && num <= length)
//                count[num]++;
//        }
//        for (int i = 1; i < count.length && count[i] != 0; i++) {
//            res = i + 1;
//        }
//        return res;
//    }
    // 当做Hash表 原地Swap
    public static int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i ++){
            while (nums[i] > 0 && nums[i] < length && nums[nums[i] - 1] != nums[i]){
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < length; i ++)
            if (nums[i] != i + 1)
                return i + 1;
        return length + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1};
        int result = firstMissingPositive(nums);
        System.out.println(result);
    }
}
