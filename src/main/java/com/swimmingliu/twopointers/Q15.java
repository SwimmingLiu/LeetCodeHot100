package com.swimmingliu.twopointers;

import java.util.*;

/**
 * 三数之和
 * <p>
 * 给你一个整数数组 nums ，
 * 判断是否存在三元组 [nums[i], nums[j], nums[k]]
 * 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 * <p>
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * <p>
 * -10^5 <= nums[i] <= 10^5
 */
public class Q15 {
    // 双指针 + 辅助数组 + 去重
//    public static List<List<Integer>> threeSum(int[] nums) {
//        int count = 0, bias = (int)1e5;
//        int[] remindNums = new int[(int) 3e5 + 10];
//        List<List<Integer>> lists = new ArrayList<>();
//        for (int num:nums) {
//            remindNums[bias + num]++;
//            if (num == 0) count++;
//        }
//        // 全零
//        if (count == nums.length) {
//            List<List<Integer>> zeroLists = new ArrayList<>();
//            zeroLists.add(Arrays.asList(0, 0, 0));
//            return zeroLists;
//        }
//        // 排序
//        Arrays.sort(nums);
//        int i = 0, j = 1;
//        while (i < j && j < nums.length - 1 && nums[i] <= 0){
//            if(i > 0 && nums[i] == nums[i-1]) {
//                i ++ ;
//                j = i + 1;
//                continue;// 去重
//            }
//            while (j < nums.length - 1){
//                int remind = -(nums[i] + nums[j]);
//                int remindNumCount = remindNums[remind + bias];
//                if (remindNumCount == 1 && remind != nums[i] && remind != nums[j])
//                    lists.add(Arrays.asList(nums[i],nums[j], remind));
//                else if (remindNumCount == 2 && (remind != nums[i] || nums[i] != nums[j]))
//                    lists.add(Arrays.asList(nums[i],nums[j], remind));
//                else  if (remindNumCount > 2)
//                    lists.add(Arrays.asList(nums[i],nums[j], remind));
//
//                j ++;
//            }
//            i ++;
//            j = i + 1;
//        }
//        for (List list:lists){
//            Collections.sort(list);
//        }
//        HashSet<List<Integer>> listHashSet = new HashSet<>(lists);
//        lists = new ArrayList<>();
//        for (List list:listHashSet){
//            lists.add(list);
//
//        }
//        return lists;
//    }

    // 双指针 + 去重
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        // a + b + c = 0
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            // 对a去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int result = nums[i] + nums[l] + nums[r];
                // 三数之和过大
                if (result > 0) r--;
                    // 三数之和过小
                else if (result < 0) l++;
                else {
                    lists.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 去重逻辑应该放在找到一个三元组之后，对b 和 c去重
                    // 对b去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    // 对c去重
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    // 更新指针
                    l++;
                    r--;
                }

            }
        }
        return lists;
    }


    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);
        for (List list : lists)
            System.out.println(list.toString());
    }
}
