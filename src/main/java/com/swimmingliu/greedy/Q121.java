package com.swimmingliu.greedy;


/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class Q121 {
    // 暴力解法会超时
    // 枚举price[i] -> 其对应的最大利润为 price[i] - minPrice;
    // 核心思路：去看每一个位置对应的最大利润计算方式
    public static int maxProfit(int[] prices) {
        int res = 0;
        int minPrice = prices[0];
        for (int i = 0; i < prices.length; i ++){
            res = Math.max(res, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        int[] nums = {7,6,4,3,1};
        System.out.println(maxProfit(nums));
    }
}
