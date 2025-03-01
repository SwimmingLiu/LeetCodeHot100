package com.swimmingliu.trick;

/**
 * 136. 只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class Q136 {
    // 异或： 0 ^ a = a, a ^ a = 0, a ^ b ^ a = b
    // 举例: [4, 1, 2, 1, 2] -> 4 ^ 1 ^ 2 ^ 1 ^ 2 = 4 ^ (1 ^ 1) ^ (2 ^ 2) = 4 ^ 0 = 4
    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        System.out.println(singleNumber(nums));
    }
}
