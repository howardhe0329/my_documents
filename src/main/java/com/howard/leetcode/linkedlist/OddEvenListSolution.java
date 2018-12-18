package com.howard.leetcode.linkedlist;

/**
 * 奇偶链表
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * @author howard he
 * @create 2018/11/8 16:36
 */
public class OddEvenListSolution {

    public ListNode oddEvenList(ListNode head) {
        // 当head 为null或者只有一个节点时返回head
        if (head == null || head.next == null) {
            return head;
        }
        // 慢指针
        ListNode slow = head.next;
        // 快指针
        ListNode fast = head.next.next;
        while (fast != null) {
            ListNode temp = fast;
            // 慢指针的next指向快指针的next
            slow.next = fast.next;
            // 快指针的next指向slow
            fast.next = slow;
//            head.next = fast;

            System.out.println("fast: " + fast);
            System.out.println("slow: " + slow);
            // slow 移动一步
            slow = slow.next;
            // fast 移动两步
            fast = fast.next.next;
        }
        return head;
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
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        head.next = n1;
        ListNode n2 = new ListNode(3);
        n1.next = n2;
        ListNode n3 = new ListNode(4);
        n2.next = n3;
        ListNode n4 = new ListNode(5);
        n3.next = n4;
//        ListNode n5 = new ListNode(6);
//        n4.next = n5;
//        ListNode n6 = new ListNode(7);
//        n5.next = n6;

        OddEvenListSolution solution = new OddEvenListSolution();
        System.out.println(head);
        ListNode result = solution.oddEvenList(head);
        System.out.println(result);
    }
}
