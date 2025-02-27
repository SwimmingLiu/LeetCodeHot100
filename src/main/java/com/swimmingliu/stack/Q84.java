package com.swimmingliu.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Q84 {
    // 1. 最大矩形面积的高度，一个存在于height数组中
    // 2. 对于每个height[i]，计算其对应最大面积的公式：(right - left - 1) * height[i]
    // 3. 其中，left和right分别是height[i]对应的左边最近的较小值和右边最近的较小值
    // 4. 用单调递增栈存放所有height对应的index
    // 5. 如果遇到不符合递增顺序的元素，就把其他元素踢出栈，从头开始
    // 6. 分别求出height数组中，每个元素对应的left和right，最后求最大值就行了
    // 7. 如果不存在对应的left，就用-1表示 (最左侧元素)，如果不存在right，就用len表示(最右侧元素)
//    public static int largestRectangleArea(int[] heights) {
//        Deque<Integer> stack = new ArrayDeque<>();
//        int len = heights.length;
//        int[] left = new int[len];
//        int[] right = new int[len];
//        // 计算height[i]对应的left
//        for (int i = 0; i < len; i ++) {
//            int h = heights[i];
//            while (!stack.isEmpty() && h <= heights[stack.peek()]) { // 剔除未来不符合递增原则的栈内元素
//                stack.pop();
//            }
//            left[i] = stack.isEmpty() ? -1 : stack.peek();
//            stack.push(i); // 存放对应的index
//        }
//        stack.clear(); // 恢复stack
//        // 计算height[i]对应的right，从尾向前
//        for (int i = len - 1; i >= 0; i --) {
//            int h = heights[i];
//            while (!stack.isEmpty() && h <= heights[stack.peek()]) { // 剔除未来不符合递增原则的栈内元素
//                stack.pop();
//            }
//            right[i] = stack.isEmpty() ? len : stack.peek();
//            stack.push(i); // 存放对应的index
//        }
//        int res = 0;
//        for (int i = 0; i < len; i ++){
//            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
//        }
//        return res;
//    }


    // 优化版本 -> 直接计算答案
    // 当遇见不符合递增的元素时，该元素就是右侧元素
        public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] heightCopy = heights;
        heights = new int[heightCopy.length + 2];
        System.arraycopy(heightCopy, 0, heights, 1, heightCopy.length);
        int res = 0;
        for (int i = 0; i < heights.length; i ++) {
            int h = heights[i];
            // 当遇见不符合递增的元素时，该元素就是right，左侧元素则是left
            while (!stack.isEmpty() && h <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ?  i - (-1) - 1: i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i); // 存放对应的index
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
    }
}
