package com.swimmingliu.trick;

import java.util.Arrays;

/**
 *  169. 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。
 * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class Q169 {

//    // 排序之后，选最中间的数
//    public static int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length / 2];
//    }


    // 摩尔投票法：对每个元素进行投票, 最终众数肯定是多一个票的 (因为他的数量是一半以上)
    // x 用于记录当前的众数候选值， vote用于记录票数
    // 如果当前票数为0， 则更新当前值为众数候选 -> x = num
    // 判断众数候选值 x，是否和当前数相同，如果相同就投一票，如果不同，就减一票
    public static int majorityElement(int[] nums) {
        int vote = 0, x = 0;
        for (int num : nums) {
            if (vote == 0) x = num;
            vote += num == x ? 1 : -1;
        }
        return x;
    }



    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        System.out.println(majorityElement(nums));
    }
}
