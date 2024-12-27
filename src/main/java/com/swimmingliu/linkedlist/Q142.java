package com.swimmingliu.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 */
public class Q142 {
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
//    public static ListNode detectCycle(ListNode head) {
//        ListNode node = head;
//        Set<ListNode> listNodes = new HashSet<>();
//        while (node != null) {
//            if (listNodes.contains(node))
//                return node;
//            listNodes.add(node);
//            node = node.next;
//        }
//        return null;
//    }
    // 双指针：相遇之处和链表头距离环入口距离相等
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (true){
            if (fastNode == null || fastNode.next == null)
                return null;
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            if (slowNode == fastNode)
                break;
        }
        fastNode = head;
        while (slowNode != fastNode){
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        return slowNode;
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
        ListNode result = detectCycle(listHead);
        System.out.println(result.val);
    }
}
