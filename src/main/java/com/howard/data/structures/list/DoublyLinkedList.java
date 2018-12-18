package com.howard.data.structures.list;

/**
 * 双向链表
 * <p>
 * 双向链表的节点包含两个指针：一个是prev指针（就是节点的前驱）；一个是next（就是节点的后继）。这样的话可以双向遍历链表。
 * 双向链表和单向链表之间的优劣：
 * 1. 双向链表可以双向遍历，每个节点都它的前驱和后继，这样可以双向遍历。单向链表只记录节点的后继节点。如果想知道某个节点的前驱节点，
 * 那么单向链表需要重新遍历链表找到其前驱节点。这样时间复杂度就变成了O(n)，而双向链表的时间复杂度为O(1)
 * 2. 由于双向链表记录前驱指针和后继指针那么存储空间相对于单向链表增加了一倍。这就是空间换时间的思想。
 *
 * @author howard he
 * @create 2018/10/9 15:50
 */
public class DoublyLinkedList<E> {

    /**
     * 头节点指针
     * 不变： (head == null && tail == null) || (head.prev == null && head.item != null)
     */
    private Node<E> head;
    /**
     * 尾节点指针
     * 不变：(head == null && tail == null) || (tail.next == null && tail.item != null)
     */
    private Node<E> tail;
    /**
     * 链表长度
     */
    private int size;

    /**
     * 构造空链表
     */
    public DoublyLinkedList() {
    }

    /**
     * 返回指定的索引节点的数据
     *
     * @param index 索引
     * @return E 如果index无效则返回null, 否则返回数据
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return node(index).item;
    }

    /**
     * 在链表的头部插入一个新元素，head节点指针指向新的节点。
     * 需要注意的是：
     * 1. 如果head为null，说明这是个空链表。那么需要把head和tail节点都指向新节点(newNode)
     * 2. 如果head不为null, 原来的head节点的prev指针要指向新节点
     * <p>
     * 时间复杂度为O(1)
     *
     * @param e 元素
     */
    public void addAtHead(E e) {
        Node h = head;
        // 创建一个新节点，其中prev为null, next指向head
        Node newNode = new Node(null, e, h);
        // 将head指向新节点
        head = newNode;
        if (h == null) {
            // 注意：如果head为null，则把tail也指向新节点。这时head和tail同时指向newNode。
            tail = newNode;
        } else {
            // 如果head不为null, 则把head的prev指向新节点。
            h.prev = newNode;
        }
        size++;
    }

    /**
     * 在链表的尾部插入一个新元素，tail指向新节点。
     * 需要注意的是：
     * 1. 如果tail是null, 则说明是个空链表。那么需要把head和tail都指向新节点(newNode)；
     * 2. 如果tail不为null, 则把tail节点的next指针指向新节点(newNode)。
     * <p>
     * 时间复杂度为 O(1)
     *
     * @param e
     */
    public void addAtTail(E e) {
        Node t = tail;
        // 创建一个新节点，其中prev指向tail, next为null
        Node newNode = new Node(t, e, null);
        // 将tail指向新节点
        tail = newNode;
        if (t == null) {
            // 如果tail为null，则把head指向新节点
            head = newNode;
        } else {
            // 如果tail不为null, 则把tail的next指向新节点
            t.next = newNode;
        }
        size++;
    }

    /**
     * 在指定的索引的节点前插入新元素，如果index等于size，那么相当于是在链表的尾部插入新元素。如index大于size，那就不插入
     * 需要注意的是：
     * 指定索引的节点为n，prev就是n的prev指针所指向的前驱节点。
     * 1. 需要将n的prev指针指向新节点；
     * 2. 如果prev为null，则说明是head节点，那么就需要把head指针指向新的节点；
     * 3. 如果prev不为null, 则需要把n的前驱节点prev的next指针指向新节点。
     * <p>
     * n->prev->next = newNode
     * n->prev = newNode
     * 其中，n为索引指定的节点；newNode是新创建的节点
     * <p>
     * 时间复杂度为 O(n)
     * 解析：因为在指定的索引前插入新元素，那么就需要查找该索引的节点。
     * 查找索引的节点时间复杂度为 O(n/2) = O(n)
     * 插入新元素的时间复杂度为 O(1)
     * 所以 O(n * 1) = O(n)
     *
     * @param index 索引
     * @param e     元素
     */
    public void addAtIndex(int index, E e) {
        if (index < 0 || index > size) {
            return;
        } else if (index == size) {
            addAtTail(e);
        } else {
            // 获取Node
            Node n = node(index);
            // 索引Node的前驱节点
            Node prev = n.prev;
            // 创建新节点，其中prev为n的prev，next为n
            Node newNode = new Node(prev, e, n);
            // n的prev指向新节点
            n.prev = newNode;
            if (prev == null) {
                // 如果n的prev为null, 则head指向新节点
                head = newNode;
            } else {
                // 如果n的prev不为null, 则n的prev的next指向新节点
                prev.next = newNode;
            }
            size++;
        }
    }

    /**
     * 指定索引删除元素
     * 需要注意的是：
     * 指定索引的节点为n，prev就是n的prev指针所指向的前驱节点。next就是n的next指针所指向的后继节点。
     * 我们需要把n删除，那么就是把n的前驱节点的next指针指向n的后继节点，把n的后继节点的prev指针指向n的前驱节点。（真的很绕）
     * 1. 如果n的后继节点为null, 则说明该节点是尾节点，那么要把tail指针指向n的前驱节点；
     * 2. 如果n的后继节点不为null, 则把n的后继节点的prev指针指向n的prev节点；
     * 3. 如果n的前驱节点为null, 则说明该节点是头节点，那么要把head指针指向n的后继节点；
     * 4. 如果n的前驱节点不为null, 则把n的前驱节点的next指针指向n的后继节点。
     * <p>
     * n->prev->next = n->next
     * n->next->prev = n->prev
     * 其中，n为索引指定的节点
     * <p>
     * 时间复杂度为 O(n)
     *
     * @param index 索引
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node n = node(index);
        // n的后继节点
        Node next = n.next;
        // n的前驱节点
        Node prev = n.prev;
        if (next == null) {
            // 如果n的后继节点为null, 则说明该节点是尾节点，那么要把tail指针指向n的前驱节点
            tail = prev;
        } else {
            // 如果n的后继节点不为null, 则把n的后继节点的prev指针指向n的prev节点
            next.prev = prev;
            // 为了gc
            n.next = null;
        }
        if (prev == null) {
            // 如果n的前驱节点为null, 则说明该节点是头节点，那么要把head指针指向n的后继节点
            head = next;
        } else {
            // 如果n的前驱节点不为null, 则把n的前驱节点的next指针指向n的后继节点
            prev.next = next;
            // 为了gc
            n.prev = null;
        }
        // 为了gc
        n.item = null;
        size--;
    }

    /**
     * 根据索引查找节点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        // 从左向向遍历
        if (index <= (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else { // 从右向左遍历
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        Node n = head;
        while (n != null) {
            sb.append(n.item);
            n = n.next;
            if (n != null) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    /**
     * 链表的节点
     *
     * @param <E>
     */
    private static class Node<E> {
        /**
         * 前驱指针，指向前一个节点
         * 如果Node为head则prev为null
         */
        private Node<E> prev;
        /**
         * 节点中的数据项
         */
        private E item;
        /**
         * 后续指针，指向后一个节点
         * 如果Node为tail则next为null
         */
        private Node<E> next;

        /**
         * 构造函数
         *
         * @param prev
         * @param item
         * @param next
         */
        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("prev=");
            if (prev != null) {
                sb.append(prev.item);
            } else {
                sb.append("null");
            }
            sb.append(", val=").append(item);
            sb.append(", next=");
            if (next != null) {
                sb.append(next.item);
            } else {
                sb.append("null");
            }
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList();
        linkedList.addAtHead(8);
        linkedList.get(1);
        linkedList.addAtTail(81);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(26);
        linkedList.deleteAtIndex(2);
        linkedList.get(1);
        linkedList.addAtTail(24);
        linkedList.addAtHead(15);
        linkedList.addAtTail(0);
        linkedList.addAtTail(13);
        linkedList.addAtTail(1);
        System.out.println(linkedList);
        linkedList.addAtIndex(6, 33);
        linkedList.get(6);
        linkedList.addAtIndex(2, 91);
        linkedList.addAtHead(82);
        linkedList.deleteAtIndex(6);
        linkedList.addAtIndex(4, 11);
        linkedList.addAtHead(3);
        linkedList.addAtIndex(7, 14);
        linkedList.deleteAtIndex(1);
        linkedList.get(6);
        System.out.println(linkedList);
        linkedList.addAtTail(99);
        linkedList.deleteAtIndex(11);
        linkedList.deleteAtIndex(7);
        linkedList.addAtTail(5);
        linkedList.addAtTail(92);
        linkedList.addAtIndex(7, 92);
        linkedList.addAtHead(57);
        linkedList.get(2);
        linkedList.get(6);
        linkedList.addAtTail(39);
        linkedList.addAtTail(51);
        linkedList.addAtTail(3);
        linkedList.addAtTail(22);
        linkedList.addAtIndex(5, 26);
        System.out.println(linkedList);
        linkedList.addAtIndex(9, 52);
        linkedList.addAtHead(69);
        linkedList.addAtIndex(5, 58);
        linkedList.addAtTail(79);
        linkedList.addAtHead(7);
        linkedList.addAtHead(41);
        linkedList.addAtHead(33);
        linkedList.addAtHead(88);
        linkedList.addAtHead(44);
        linkedList.addAtHead(8);
        linkedList.addAtTail(72);
        linkedList.addAtHead(93);
        linkedList.deleteAtIndex(18);
        linkedList.addAtHead(1);
        linkedList.get(9);
        linkedList.addAtHead(46);
        linkedList.get(9);
        linkedList.addAtHead(92);
        linkedList.addAtHead(71);
        linkedList.addAtHead(69);
        linkedList.addAtIndex(11, 54);
        linkedList.deleteAtIndex(27);
        linkedList.addAtTail(83);
        linkedList.deleteAtIndex(12);
        linkedList.get(20);
        linkedList.addAtIndex(19, 97);
        linkedList.addAtHead(77);
        linkedList.addAtTail(36);
        linkedList.deleteAtIndex(3);
        linkedList.addAtHead(35);
        linkedList.addAtIndex(16, 68);
        linkedList.deleteAtIndex(22);
        linkedList.deleteAtIndex(36);
        linkedList.deleteAtIndex(17);
        linkedList.addAtHead(62);
        linkedList.addAtTail(89);
        linkedList.addAtTail(61);
        linkedList.addAtHead(6);
        linkedList.addAtTail(92);
        linkedList.addAtIndex(28, 69);
        linkedList.deleteAtIndex(23);
        linkedList.deleteAtIndex(28);
        linkedList.addAtIndex(7, 4);
        linkedList.addAtHead(0);
        linkedList.addAtHead(24);
        linkedList.addAtTail(52);
        linkedList.get(1);
        linkedList.addAtIndex(23, 3);
        linkedList.get(7);
        linkedList.addAtHead(6);
        linkedList.addAtHead(68);
        linkedList.addAtHead(79);
        linkedList.addAtIndex(45, 90);
        linkedList.addAtIndex(41, 52);
        linkedList.get(28);
        linkedList.addAtHead(25);
        linkedList.get(9);
        linkedList.get(32);
        linkedList.addAtTail(11);
        linkedList.addAtHead(90);
        linkedList.addAtHead(24);
        linkedList.addAtTail(98);
        linkedList.addAtTail(36);
        linkedList.get(34);
        linkedList.addAtTail(26);
    }
}
