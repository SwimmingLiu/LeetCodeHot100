package com.swimmingliu.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 接雨水
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Q42 {
    // 双指针
//    public static int trap(int[] height) {
//        int len = height.length;
//        if (len < 3) return 0;
//        int l = 0, r = len - 1;
//        int maxLeft = 0, maxRight = 0;
//        int res = 0;
//        while (l < r) {
//            maxLeft = Math.max(maxLeft, height[l]);
//            maxRight = Math.max(maxRight, height[r]);
//            if (height[l] < height[r]) {
//                res += maxLeft - height[l++];
//            } else {
//                res += maxRight - height[r--];
//            }
//        }
//        return res;
//    }

    // 单调栈
    public static int trap(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        int res = 0, top = 0;
        int[] stack = new int[len];
        stack[top++] = 0;
        for (int i = 1; i < len; i++) {
            if (height[i] <= height[stack[top - 1]])
                stack[top++] = i;
            else {
                while (top != 0 && height[i] > height[stack[top - 1]]) {
                    int mid = stack[--top];
                    if (top == 0) break;
                    int minEdge = Math.min(height[stack[top - 1]], height[i]);
                    res += (minEdge - height[mid]) * (i - stack[top - 1] - 1);
                }
                stack[top++] = i;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = trap(nums);
        System.out.println(result);
    }
}
