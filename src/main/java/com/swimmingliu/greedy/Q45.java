package com.swimmingliu.greedy;

/**
 * 45. 跳跃游戏 II
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 1. 0 <= j <= nums[i]
 * 2. i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 */
public class Q45 {
    // 1. 把跳跃的过程，当成是在搭桥 -> 桥有左右两个端点 left 和 right，分别表示其起始位置和结束位置
    // 2. 如果右端点 > length - 1 说明，它能够到达最后一个元素了
    // 3. 搭桥的过程中，根据当前的左端点，去找能走的最远的元素作为新的左端点，
    // 4. 新的左端点能走的距离加上新左端点的位置就是新有断电
    public static int jump(int[] nums) {
        int left = 0, right = 0; // 最开始的左右端点都是0
        int step = 0;
        while (right < nums.length - 1){
            int l = left, r = right;
            for (int i = l; i <= r; i ++){ // 寻找新的左右端点
                if (nums[i] + i > right){
                    left = i;
                    right = nums[i] + i;
                }
            }
            step ++;
        }
        return step;
    }

    public static void main(String[] args) {
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        System.out.println(jump(nums));
    }
}
