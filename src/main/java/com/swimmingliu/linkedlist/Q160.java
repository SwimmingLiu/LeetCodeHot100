package com.swimmingliu.linkedlist;

import java.util.Arrays;

public class Q160 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 暴力解
//    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        ListNode listNode1 = headA;
//        ListNode listNode2 = headB;
//        while (listNode1 != null) {
//            while (listNode2 != null){
//                if (listNode1 == listNode2)
//                    return listNode1;
//                listNode2 = listNode2.next;
//            }
//            listNode1 = listNode1.next;
//            listNode2 = headB;
//        }
//        return null;
//    }

    // 双指针
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode listNodeA = headA;
        ListNode listNodeB = headB;
        while (listNodeA != listNodeB){
            listNodeA = listNodeA != null ? listNodeA.next : headB;
            listNodeB = listNodeB != null ? listNodeB.next : headA;
        }
        return listNodeA;
    }

    public static void main(String[] args) {
        int[] list1 = {4, 1, 8, 4, 5};
        int[] list2 = {5, 6, 1};
        ListNode listNode1 = new ListNode(list1[0]);
        ListNode listHead1 = listNode1;
        ListNode sameHeadNode = new ListNode(8);
        for (int i = 1; i < list1.length; i++) {
            ListNode node = new ListNode(list1[i]);
            listNode1.next = node;
            listNode1 = node;
            if (list1[i] == 8)
                sameHeadNode = node;
        }
        ListNode listNode2 = new ListNode(list2[0]);
        ListNode listHead2 = listNode2;
        for (int i = 1; i < list2.length; i++) {
            ListNode node = new ListNode(list2[i]);
            listNode2.next = node;
            listNode2 = node;
        }
        listNode2.next = sameHeadNode;
        ListNode result = getIntersectionNode(listHead1, listHead2);
        if (result != null)
            System.out.println(result.val);
        else
            System.out.println(result);
    }
}
