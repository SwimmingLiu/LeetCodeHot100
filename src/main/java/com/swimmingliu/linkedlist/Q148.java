package com.swimmingliu.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按升序排列并返回排序后的链表 。
 */
public class Q148 {
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

    // 转换为list 然后快排
//    public static ListNode sortList(ListNode head) {
//        if (head == null || head.next == null)
//            return head;
//        List<Integer> list = new ArrayList<>();
//        ListNode node = head;
//        while (node != null){
//            list.add(node.val);
//            node = node.next;
//        }
//        Collections.sort(list);
//        ListNode dummy = new ListNode();
//        node = dummy;
//        for (Integer i : list) {
//            node.next = new ListNode(i);
//            node = node.next;
//        }
//        return dummy.next;
//    }

    // 归并排序
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next;
        ListNode slow = head;
        // 快慢指针找到中点, slow就是中点
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中点断开，分为两个链表
        ListNode anotherHead = slow.next;
        slow.next = null;
        // 递归找左边和右边
        ListNode left = sortList(head);
        ListNode right = sortList(anotherHead);
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (left != null && right != null){
            if (left.val <= right.val){
                node.next = left;
                left = left.next;
            }else{
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        node.next = left != null ? left : right;
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
        ListNode result = sortList(listHead);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
