package com.swimmingliu.linkedlist;

/**
 * 234. 回文链表
 */
public class Q234 {
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
            return null;
        ListNode node = head.next;
        ListNode nodeHead = head;
        while (node != null) {
            ListNode nodeNext = node.next;
            node.next = nodeHead;
            nodeHead = node;
            node = nodeNext;
        }
        head.next = null;
        return nodeHead;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode node = head;
        ListNode midNode = head;
        while (node != null && node.next != null) {
            midNode = midNode.next;
            node = node.next.next;
        }
        node = head;
        midNode = reverseList(midNode);
        while (midNode != null) {
            if (node.val != midNode.val)
                return false;
            midNode = midNode.next;
            node = node.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] list = {1};
        ListNode listNode = new ListNode(list[0]);
        ListNode listHead = listNode;
        for (int i = 1; i < list.length; i++) {
            ListNode node = new ListNode(list[i]);
            listNode.next = node;
            listNode = node;
        }
        boolean result = isPalindrome(listHead);
        System.out.println(result);
    }
}
