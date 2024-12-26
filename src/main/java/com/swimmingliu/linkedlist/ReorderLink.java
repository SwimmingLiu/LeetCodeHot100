package com.swimmingliu.linkedlist;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class ReorderLink {

    public static ListNode reverseList(ListNode head){
        ListNode nodeHead = head;
        ListNode node = head.next;
        while(node != null){
            ListNode nodeNext = node.next;
            node.next = nodeHead;
            nodeHead = node;
            node = nodeNext;
        }
        head.next = null;
        return nodeHead;
    }

    public static void reorderList(ListNode head){
        int listSize = 0;
        ListNode node = head;
        // 1. 获取链表大小
        while(node != null){
            listSize ++;
            node = node.next;
        }
        if (listSize == 0 || listSize == 1)
            return;
        // 2. 获取链表中间的节点
        node = head;
        for (int i = 1; i < (listSize + 1) / 2; i ++){
            node = node.next;
        }
        ListNode midNode = node.next;
        node.next = null;
        // 3. 翻转链表
        midNode = reverseList(midNode);
        // 4.连接两个链表
        node = head;
        while (node != null && midNode != null){
            ListNode nodeNext = node.next;
            ListNode midNodeNext = midNode.next;
            node.next = midNode;
            midNode.next = nodeNext;
            node = nodeNext;
            midNode = midNodeNext;
        }
    }

    public static void main(String[] args) {
        int[] list = {1};
        ListNode node = new ListNode(list[0]);
        ListNode head =  node;
        for (int i = 1; i < list.length; i ++) {
            ListNode newNode = new ListNode(list[i]);
            node.next = newNode;
            node = newNode;
        }
        reorderList(head);
        node = head;
        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
