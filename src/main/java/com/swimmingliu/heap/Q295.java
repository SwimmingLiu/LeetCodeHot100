package com.swimmingliu.heap;


import java.util.*;

/**
 * 295. 数据流的中位数
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * MedianFinder() 初始化 MedianFinder 对象。
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 */
public class Q295 {
    // 小根堆(递增) + 大根堆(递减) -> 中位数：小根堆堆顶 (奇数) or (小根堆堆顶 + 大根堆堆顶) / 2 (偶数)
    // 1. arr=[1,2,3,4,5]的中位数是3， arr=[1,2,3,4,5,6]的中位数是(3 + 4) / 2 = 3.5
    // 2. 为了o(1)找到中位数，可以把arr分为left和right, left表示小根堆(递增)，right表示大根堆(递减)
    // 3. 怎么分left和right? 首先假设arr如果为奇数个元素，left比right多一个元素
    // 3.1 假设当前left和right个数相同。
    // 3.1.1 如果num比较大，则应该放到右边，但是就不符合条件了。所以，只能先放入right，再取出right最小的放入left。
    // 3.1.2 如果num比较小，可以直接放入left。
    // 3.1.3 合并上面的两种情况，不管num大小，直接放入right，再把right最小的，放入left
    // 3.2 假设当前left和right个数不同，规则只能是left多一个，right少。
    // 3.2.1 如果num比较大，应该放右边。
    // 3.2.2 如果num比较小，应该放左边，但是不符合条件了，多了一个数。所以先放左边，然后poll出来，在放右边。
    // 3.2.3 合并上面两种情况，不管num大小，直接放left，然后再poll出来，放right
    // 4.2. 中位数：如果left比right个数多，则把left最后一个数peek出来。
    // 4.1如果left和right一样多，则peek两个queue的最后一个元素，然后求平均值

    static class MedianFinder {
        private Queue<Integer> left = new PriorityQueue<>((a, b) -> a - b);
        private Queue<Integer> right = new PriorityQueue<>((a, b) -> b - a);

        public MedianFinder() {
        }

        public void addNum(int num) {
            if (left.size() == right.size()){ // 相等的话，先放入right，再放入left
                right.offer(num);
                left.offer(right.poll());
            }else{ // left > right -> 想放入left，再放入right
                left.offer(num);
                right.offer(left.poll());
            }
        }

        public double findMedian() {
           if (left.size() == right.size())
               return (left.peek() + right.peek()) / 2.0;
           else return left.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
    }
}
