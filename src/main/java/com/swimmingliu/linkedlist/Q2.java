package com.swimmingliu.linkedlist;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class Q2 {
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


    // 暴力解
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Node = l1;
        ListNode l2Node = l2;
        ListNode resultHead = new ListNode();
        ListNode resultNode = resultHead;
        int inc = 0;
        while (l1Node != null && l2Node != null) {
            int sum = l1Node.val + l2Node.val + inc;
            resultNode.next = new ListNode(sum % 10);
            inc = sum / 10;
            resultNode = resultNode.next;
            l1Node = l1Node.next;
            l2Node = l2Node.next;
        }
        while (l1Node != null) {
            int sum = l1Node.val + inc;
            resultNode.next = new ListNode(sum % 10);
            resultNode = resultNode.next;
            l1Node = l1Node.next;
            inc = sum / 10;
        }
        while (l2Node != null) {
            int sum = l2Node.val + inc;
            resultNode.next = new ListNode(sum % 10);
            resultNode = resultNode.next;
            l2Node = l2Node.next;
            inc = sum / 10;
        }
        if (inc != 0){
            resultNode.next = new ListNode(inc);
        }
        return resultHead.next;
    }


    public static void main(String[] args) {
        int[] list1 = {3, 7};
        int[] list2 = {9, 2};
        ListNode listNode = new ListNode(list1[0]);
        ListNode listHead1 = listNode;
        for (int i = 1; i < list1.length; i++) {
            ListNode node = new ListNode(list1[i]);
            listNode.next = node;
            listNode = node;
        }
        listNode = new ListNode(list2[0]);
        ListNode listHead2 = listNode;
        for (int i = 1; i < list2.length; i++) {
            ListNode node = new ListNode(list2[i]);
            listNode.next = node;
            listNode = node;
        }
        ListNode result = addTwoNumbers(listHead1, listHead2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
