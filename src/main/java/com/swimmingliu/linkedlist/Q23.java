package com.swimmingliu.linkedlist;

import java.util.*;

/**
 * 23. 合并 K 个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Q23 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 循环双指针 暴力
//    public static ListNode mergeKLists(ListNode[] lists) {
//        if (lists.length == 0)
//            return null;
//        lists =  Arrays.stream(lists)
//                .filter(node -> node != null)
//                .toArray(ListNode[]::new);
//        if (lists.length == 0)
//            return null;
//        else if (lists.length == 1)
//            return lists[0];
//        Arrays.sort(lists, Comparator.comparingInt(a -> a.val));
//        ListNode dummy = new ListNode();
//        ListNode pre = lists[0];
//        for (int i = 1; i < lists.length; i++) {
//            ListNode node = dummy;
//            ListNode tail = lists[i];
//            while (pre != null && tail != null) {
//                if (pre.val <= tail.val) {
//                    node.next = pre;
//                    pre = pre.next;
//                } else {
//                    node.next = tail;
//                    tail = tail.next;
//                }
//                node = node.next;
//            }
//            node.next = pre != null ? pre : tail;
//            pre = lists[0];
//        }
//        return dummy.next;
//    }

    // 分组合并
    public static ListNode mergeTwoList(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                node.next = l1;
                l1 = l1.next;
            }else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
    // 分组合并
    public static ListNode mergeKLists(ListNode[] lists) {
        // 特殊情况 len: 0, 1, 2
        int len = lists.length;
        if (len == 0) return null;
        else if (len == 1) return lists[0];
        else if (len == 2) return mergeTwoList(lists[0], lists[1]);
        // len > 2 -> 分组
        int mid = len / 2;
        ListNode[] sub1 = new ListNode[mid];
        for (int i = 0; i < mid; i ++) sub1[i] = lists[i];
        ListNode[] sub2 = new ListNode[len - mid];
        for (int i = mid; i < len; i ++) sub2[i - mid] = lists[i];
        // 递归合并sub1 和 sub2
        ListNode l1 = mergeKLists(sub1);
        ListNode l2 = mergeKLists(sub2);
        // 合并l1和l2
        return mergeTwoList(l1, l2);
    }

    public static ListNode initList(int[] list, ListNode listNode) {
        ListNode listHead = listNode;
        for (int i = 0; i < list.length; i++) {
            listNode.next = new ListNode(list[i]);
            listNode = listNode.next;
        }
        return listHead.next;
    }

    public static void main(String[] args) {
        int[][] lists = {{}, {1}, {1}};
        ListNode[] listNodes = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            ListNode listNode = new ListNode();
            listNodes[i] = initList(lists[i], listNode);
        }
        ListNode result = mergeKLists(listNodes);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
