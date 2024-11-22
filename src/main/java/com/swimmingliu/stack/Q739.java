package com.swimmingliu.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 每日温度
 */
public class Q739 {
    // 单调栈 -> Stack
//    public static int[] dailyTemperatures(int[] temperatures) {
//        if (temperatures.length == 1) return new int[]{0};
//        int[] result = new int[temperatures.length];
//        Stack<Integer> stack = new Stack<>();
//        stack.push(0); // 存放下标
//        for (int i = 0; i < temperatures.length; i ++){
//            Integer top = stack.peek();
//            if (temperatures[i] <= temperatures[top])
//                stack.push(i);
//            else {
//                while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
//                    Integer pop = stack.pop();
//                    result[pop] = i - pop;
//                }
//                stack.push(i);
//            }
//        }
//        return result;
//    }

    // 单调栈 -> 数组
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) return new int[]{0};
        int[] result = new int[temperatures.length];
        int[] stack = new int[temperatures.length];
        int top = 0;
        stack[top++] = 0; // 存放下标
        for (int i = 0; i < temperatures.length; i++) {
            int stackTop = stack[top - 1];
            if (temperatures[i] <= temperatures[stackTop])
                stack[top++] = i;
            else {
                while (top != 0 && temperatures[i] > temperatures[stack[top - 1]]) {
                    int pop = stack[--top];
                    result[pop] = i - pop;
                }
                stack[top++] = i;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(nums);
        System.out.println(Arrays.toString(result));
    }
}
