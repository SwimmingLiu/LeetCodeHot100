package com.swimmingliu.linkedlist;

public class Q206 {
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

    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode nodeHead = head;
        ListNode node = head.next;
        while (node != null) {
            ListNode nodeNext = node.next;
            node.next = nodeHead;
            nodeHead = node;
            node = nodeNext;
        }
        head.next = null;
        return nodeHead;
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
        ListNode result = reverseList(listHead);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
