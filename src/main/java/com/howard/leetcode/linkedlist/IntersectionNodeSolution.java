package com.howard.leetcode.linkedlist;

/**
 * 相交链表
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 *
 *
 * 例如，下面的两个链表：
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * 在节点 c1 开始相交。
 *
 *
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * @author howard he
 * @create 2018/10/18 11:38
 */
public class IntersectionNodeSolution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界检查
        if (headA == null || headB == null) {
            return null;
        }
        // pA指针指向a链表的head节点
        ListNode pA = headA;
        // pB指什指向b链表的head节点
        ListNode pB = headB;
        // 假如pA == pB 证明链表a和b有交点
        while (pA != pB) {
            // 如果pA指针指向a链表的尾部，则指向链表b的头节点
            pA = pA == null ? headB : pA.next;
            // 如果pA指针指向b链表的尾部，则指向链表a的头节点
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode headB = new ListNode(-1);
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(3);
        ListNode b1 = new ListNode(-2);
        ListNode b2 = new ListNode(-3);
        ListNode b3 = new ListNode(-4);
        ListNode c1 = new ListNode(7);
        ListNode c2 = new ListNode(8);
        ListNode c3 = new ListNode(9);

        headA.next = a1;
//        a1.next = a2;
//        a2.next = c1;

        headB.next = b1;
        b1.next = b2;
//        b2.next = b3;
//        b3.next = c1;

//        c1.next = c2;
//        c2.next = c3;
        IntersectionNodeSolution solution = new IntersectionNodeSolution();
        ListNode n = solution.getIntersectionNode(headA, headB);
        System.out.println(n);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ListNode{");
            sb.append("val=").append(val);
            sb.append('}');
            return sb.toString();
        }
    }
}
