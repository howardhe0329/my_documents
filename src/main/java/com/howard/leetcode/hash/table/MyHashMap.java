package com.howard.leetcode.hash.table;

/**
 * 设计哈希映射
 *
 * @author howard he
 * @create 2018/11/5 16:38
 */
public class MyHashMap {

    private Node<Integer, Integer>[] table;
    private int size;
    private int capacity;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        this.capacity = 1000;
        table = new Node[capacity];
    }

    /**
     * value will always be positive.
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        int index = hash(key);
        Node<Integer, Integer> h = table[index];
        if (h == null) {
            h = new Node<>(index, key, value,null);
            table[index] = h;
            size++;
        } else {
            Node<Integer, Integer> n;
            if (h.key == key) {
                n = h;
            } else {
                for (;;) {
                    if ((n = h.next) == null) {
                        h.next = new Node<>(index, key, value, null);
                        break;
                    }
                    if (n.key == key) {
                        break;
                    }
                    h = n;
                }
            }
            if (n != null) {
                n.value = value;
            } else {
                size++;
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     *
     * @param key
     * @return
     */
    public int get(int key) {
        int index = hash(key);
        Node<Integer, Integer> h = table[index];
        while (h != null) {
            if (h.key == key) {
                return h.value;
            }
            h = h.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     *
     * @param key
     */
    public void remove(int key) {
        int index = hash(key);
        Node<Integer, Integer> h = table[index];
        Node<Integer, Integer> node = null;
        Node<Integer, Integer> prev = null;
        while (h != null) {
            if (h.key == key) {
                node = h;
                break;
            }
            prev = h;
            h = h.next;
        }
        if (node != null) {
            if (prev == null) {
                table[index] = node.next;
            } else {
                prev.next = node.next;
            }
            size--;
        }
    }

    private int hash(int key) {
        return key % capacity;
    }

    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node next;

        public Node(int hash, K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.remove(2);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        // 返回 1
        System.out.println(hashMap.get(1));
        // 返回 -1 (未找到)
        System.out.println(hashMap.get(3));
        // 更新已有的值
        hashMap.put(2, 1);
        // 返回 1
        System.out.println(hashMap.get(2));
        // 删除键为2的数据
        hashMap.remove(2);
        // 返回 -1 (未找到)
        System.out.println(hashMap.get(2));
    }
}
