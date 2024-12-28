package com.swimmingliu.linkedlist;

/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class Q24 {
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
//    public static ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null)
//            return head;
//        ListNode node = head;
//        ListNode ans = head.next;
//        ListNode conNode = null;
//        while (node != null && node.next != null){
//            ListNode nextIterNode = node.next.next;
//            ListNode nextNode = node.next;
//            nextNode.next = node;
//            node.next = nextIterNode;
//            if (conNode != null){
//                conNode.next = nextNode;
//            }
//            conNode = node;
//            node = nextIterNode;
//        }
//        return ans;
//    }
    // 递归
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode nextNode = head.next;
        head.next = swapPairs(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4};
        ListNode listNode = new ListNode(list[0]);
        ListNode listHead = listNode;
        for (int i = 1; i < list.length; i++) {
            ListNode node = new ListNode(list[i]);
            listNode.next = node;
            listNode = node;
        }
        ListNode result = swapPairs(listHead);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
