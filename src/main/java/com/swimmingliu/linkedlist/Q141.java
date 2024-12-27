package com.swimmingliu.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表
 * <p>
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 */
public class Q141 {
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

    // HashSet
//    public static boolean hasCycle(ListNode head) {
//        ListNode node = head;
//        Set<ListNode> listNodes = new HashSet<>();
//        while (node != null) {
//            if (listNodes.contains(node))
//                return true;
//            listNodes.add(node);
//            node = node.next;
//        }
//        return false;
//    }

    // 快慢指针
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slowNode = head;
        ListNode fastNode = head.next;
        while (slowNode != null && fastNode != null) {
            if (fastNode == slowNode)
                return true;
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode != null)
                fastNode = fastNode.next;
        }
        return false;
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
        boolean result = hasCycle(listHead);
        System.out.println(result);
    }
}
