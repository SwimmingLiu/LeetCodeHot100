package com.swimmingliu.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class Q739 {
    // 单调栈 -> Stack
    // 单调栈一定是和答案顺序相反，答案要求递增 -> 单调栈就递减
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) return new int[]{0};
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 存放下标
        for (int i = 0; i < temperatures.length; i ++){
            Integer top = stack.peek();
            if (temperatures[i] <= temperatures[top]) // 如果比栈顶元素小，就入栈
                stack.push(i);
            else { // 如果比栈顶元素大，就把栈里面所有比当前数小的，出栈
                while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                    Integer pop = stack.pop();
                    result[pop] = i - pop;
                }
                stack.push(i);
            }
        }
        return result;
    }

    // 单调栈 -> 数组
//    public static int[] dailyTemperatures(int[] temperatures) {
//        if (temperatures.length == 1) return new int[]{0};
//        int[] result = new int[temperatures.length];
//        int[] stack = new int[temperatures.length];
//        int top = 0;
//        stack[top++] = 0; // 存放下标
//        for (int i = 0; i < temperatures.length; i++) {
//            int stackTop = stack[top - 1];
//            if (temperatures[i] <= temperatures[stackTop])
//                stack[top++] = i;
//            else {
//                while (top != 0 && temperatures[i] > temperatures[stack[top - 1]]) {
//                    int pop = stack[--top];
//                    result[pop] = i - pop;
//                }
//                stack[top++] = i;
//            }
//        }
//        return result;
//    }


    public static void main(String[] args) {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(nums);
        System.out.println(Arrays.toString(result));
    }
}
