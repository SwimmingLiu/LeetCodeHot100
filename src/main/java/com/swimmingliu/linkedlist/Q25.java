package com.swimmingliu.linkedlist;

import java.util.List;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
 * 那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class Q25 {
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

//    // 暴力解
//    public static ListNode reverseList(ListNode head, ListNode end) {
//        ListNode node = head.next;
//        ListNode nodeHead = head;
//        ListNode endNext = end.next;
//        while (node != endNext) {
//            ListNode nextNode = node.next;
//            node.next = nodeHead;
//            nodeHead = node;
//            node = nextNode;
//        }
//        head.next = endNext;
//        return nodeHead;
//    }
//    // 暴力解
//    public static ListNode reverseKGroup(ListNode head, int k) {
//        if (head == null || head.next == null)
//            return head;
//        ListNode ans = new ListNode(0, head);
//        ListNode node = ans;
//        ListNode lastTail = ans;
//        ListNode groupHead = head;
//        ListNode groupTail = null;
//        int count = k;
//        while (node != null) {
//            if (count == 0) {
//                ListNode nodeNext = node.next;
//                groupTail = node;
//                lastTail.next = reverseList(groupHead, groupTail);
//                lastTail = groupHead;
//                count = k;
//                node = lastTail;
//                groupHead = nodeNext;
//            }
//            node = node.next;
//            count--;
//        }
//        return ans.next;
//    }

    // 优化
    public static ListNode reverseList(ListNode head) {
        ListNode node = head.next;
        ListNode nodeHead = head;
        while (node != null) {
            ListNode nodeNext = node.next;
            node.next = nodeHead;
            nodeHead = node;
            node = nodeNext;
        }
        return nodeHead;
    }

    // 优化
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseList(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(list[0]);
        ListNode listHead = listNode;
        for (int i = 1; i < list.length; i++) {
            ListNode node = new ListNode(list[i]);
            listNode.next = node;
            listNode = node;
        }
        ListNode result = reverseKGroup(listHead, 2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
