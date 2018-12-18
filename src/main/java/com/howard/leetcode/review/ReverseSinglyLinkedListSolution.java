package com.howard.leetcode.review;

/**
 * 反转链表
 *
 * @author howard he
 * @create 2018/11/12 10:40
 */
public class ReverseSinglyLinkedListSolution {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        // 遍历链表
        while (curr != null) {
            // next 指向下一个节点
            next = curr.next;
            // 将curr的next指针指向prev指针指向的节点
            curr.next = prev;
            // 将prev指针指向curr指针指向的节点
            prev = curr;
            // curr指针指向next指针指向的节点，继续向下遍历
            curr = next;
        }
        return prev;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            ListNode n = this.next;
            final StringBuilder sb = new StringBuilder();
            sb.append(val);
            while (n != null) {
                sb.append("->").append(n.val);
                n = n.next;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ReverseSinglyLinkedListSolution solution = new ReverseSinglyLinkedListSolution();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        ListNode n5 = new ListNode(5);
        n4.next = n5;
        System.out.println(n1);
        ListNode result = solution.reverseList(n1);
        System.out.println(result);
    }
}
