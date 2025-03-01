package com.swimmingliu.hash;

import java.util.*;

/**
 * 最长连续序列
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class Q128 {

    // HashSet去重 + 找连续序列起点
//    public static int longestConsecutive(int[] nums) {
//        // 特判
//        if (nums.length == 0) return 0;
//        else if (nums.length == 1) return 1;
//        Set<Integer> numsSet = new HashSet<>();
//        int result = 0;
//        for (int num:nums) numsSet.add(num);
//        for (int num:numsSet){
//            // 如果numsSet包含他前面的数，则说明其不为连续序列起点
//            if (numsSet.contains(num - 1)) continue;
//            int numChainEnd = num;
//            int numChainResult = 1;
//            while (numsSet.contains(numChainEnd + 1)){
//                numChainResult ++;
//                numChainEnd ++;
//            }
//            result = Math.max(result, numChainResult);
//        }
//        return result;
//    }
    // HashMap + 右边界
    public static int longestConsecutive(int[] nums) {
        // 特判
        if (nums.length == 0) return 0;
        else if (nums.length == 1) return 1;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, num);
        for (int num : nums) {
            if (map.containsKey(num - 1)) continue; // 找每一组数据的开始位置
            int right = map.get(num);
            while (map.containsKey(right + 1)){
                right = map.get(right + 1);
            }
            map.put(num, right);
            result = Math.max(result, right - num + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = longestConsecutive(nums);
        System.out.println(result);

    }
}
