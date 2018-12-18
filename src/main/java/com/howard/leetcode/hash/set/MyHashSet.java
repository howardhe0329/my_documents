package com.howard.leetcode.hash.set;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计哈希集合
 *
 * @author howard he
 * @create 2018/11/5 16:06
 */
public class MyHashSet {

    private List<Integer>[] items;
    private int capacity;

    public MyHashSet() {
        this.capacity = 1000;
        items = new List[capacity];
    }

    private int hash(int key) {
        return key % this.capacity;
    }

    public void add(int key) {
        int index = hash(key);
        List<Integer> bucket = items[index];
        if (bucket == null) {
            bucket = new ArrayList<>();
            bucket.add(key);
            items[index] = bucket;
        } else if (!bucket.contains(key)) {
            bucket.add(key);
        }
    }

    public void remove(int key) {
        int index = hash(key);
        List<Integer> bucket = items[index];
        if (bucket != null) {
            bucket.remove(Integer.valueOf(key));
        }
    }

    public boolean contains(int key) {
        int index = hash(key);
        List<Integer> bucket = items[index];
        return bucket != null && bucket.contains(key);
    }

    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));    // 返回 true
        System.out.println(hashSet.contains(3));    // 返回 false (未找到)
        hashSet.add(2);
        System.out.println(hashSet.contains(2));    // 返回 true
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));    // 返回  false (已经被删除)
    }

}
