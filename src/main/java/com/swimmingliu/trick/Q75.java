package com.swimmingliu.trick;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */
public class Q75 {

//    // 用count记录每个元素出现次数，直接替换nums数组中的元素
//    public static void sortColors(int[] nums) {
//        int[] count = new int[3];
//        for (int num : nums) {
//            count[num] ++;
//        }
//        int index = 0;
//        for (int i = 0; i <= 2; i ++){
//            int j = index;
//            while (j < count[i] + index){
//                nums[j++] = i;
//            }
//            index = j;
//        }
//    }

    // 用三个指针zero, i, two 来进行划分区间
    // [0, zero) = 0, [zero, i) = 1, [two, len - 1] = 2
    // 初始条件要确保符合最原始的状态, zero = -1 -> [0, zero]无任何元素,
    // two = len, 确保(two, len - 1] 无任何元素
    // num[zero]表示第一个`1`， num[two] 第一个`2`
    // 终止条件：i >= two， 因为如果符合条件 i < two
    public static void sortColors(int[] nums) {
       int zero = 0, two = nums.length;
       int i = 0;
       while (i < two){
            if (nums[i] == 0) { // 当前元素应该属于[0, zero]区间
                swap(nums, zero, i);
                i ++;
                zero ++;
            } else if (nums[i] == 1) { // 如果当前元素为1，则向前遍历
                i ++;
            } else { // 当前元素为2，更新two指针的位置
                two --; // 指向前一个数
                swap(nums, i, two);
            }
       }
    }
    private static void swap(int[] num, int a, int b){
        int tmp = num[a];
        num[a] = num[b];
        num[b] = tmp;
    }




    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
