package com.swimmingliu.stack;

import java.util.*;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class Q155 {
    // 用一个数组来存储每个val和其对应的最小值 -> [val, min], 其中min为[..., val]中的最小值
    static class MinStack {
        private final Deque<int[]> st = new ArrayDeque<>();
        public MinStack() {
            st.push(new int[]{0, Integer.MAX_VALUE}); // 前面表示本身的值，后面表示从头到这的最小值
        }

        public void push(int val) {
            st.push(new int[]{val, Math.min(getMin(), val)});
        }

        public void pop() {
            st.pop();
        }

        public int top() {
            return st.peek()[0];
        }

        public int getMin() {
            return st.peek()[1];
        }
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(-3);
        obj.push(1);
        System.out.println(obj.getMin());
    }
}
