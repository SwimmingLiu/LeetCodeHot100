package com.swimmingliu.arrays;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 *
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
public class Q238 {
    // 转成二维思维计算, 分成两部分，先计算左下三角，后计算右上三角
    /**
     * ans[0] =   1  * A[1] * A[2] * A[3]
     * ans[1] = A[0] *  1   * A[2] * A[3]
     * ans[2] = A[0] * A[1] *   1  * A[3]
     * ans[3] = A[0] * A[1] * A[2] *  1
     */
    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] ans = new int[length];
        int temp = 1;
        ans[0] = 1;
        // 左下三角
        for (int i = 1; i < length; i ++){
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        // 右上三角
        for (int i = length - 2; i >= 0; i --){
            temp = temp * nums[i + 1];
            ans[i] *= temp;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{-1,1,0,-3,3};
        int[] res = productExceptSelf(nums);
        System.out.println(Arrays.toString(res));
    }
}
