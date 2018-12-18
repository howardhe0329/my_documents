package com.howard.leetcode.linkedlist;

/**
 * 反转单链表
 *
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author howard he
 * @create 2018/11/7 14:49
 */
public class ReverseSinglyLinkedListSolution {

    public ListNode reverseList(ListNode head) {
        // prev指针
        ListNode prev = null;
        // 当前指针指向head
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            // 保存curr的next指针
            next = curr.next;
            // 将curr的next指针指向prev
            curr.next = prev;
            // 将prev指针指向curr指针指向的节点
            prev = curr;
            // 将curr指针指向next指针指向的节点
            curr = next;
        }
        head = prev;
        return head;
    }

    public ListNode reverseList1(ListNode head) {
        return recursive(head);
    }

    /**
     * 递归
     * 基线条件
     * 1. 当链表为null, 返null
     * 2. 当链表只有一个节点，返回链表head
     *
     * 递归条件
     *  当链表长度大于一个节点，则递归
     *
     *
     *
     * @param head
     * @return
     */
    public ListNode recursive(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode prev = recursive(head.next);
        head.next.next = head;
        head.next = null;
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
        ListNode result = solution.reverseList1(n1);
        System.out.println(result);
    }
}
