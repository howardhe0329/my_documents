package com.howard.algorithm.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SortTest {

    private Sort sort;
    private int[] test = new int[] {3, 5, 9, 1, 0, 2, 4, 6, 8, 7, 0};

    @Before
    public void setUp() throws Exception {
        sort = new Sort();
    }

    @Test
    public void insert() {
        int[] nums = test.clone();
        sort.insert(nums);
        assertSort(nums);
    }

    @Test
    public void bubble() {
        int[] nums = test.clone();
        sort.bubble(nums);
        assertSort(nums);
    }

    @Test
    public void select() {
        int[] nums = test.clone();
        sort.select(nums);
        assertSort(nums);
    }

    @Test
    public void mergeSort() {
        int[] nums = test.clone();
        sort.mergeSort(nums);
        assertSort(nums);
    }

    @Test
    public void quickSort() {
        int[] nums = test.clone();
        sort.quickSort(nums);
        assertSort(nums);
    }

    private void assertSort(int[] sorted) {
        int[] expert = test.clone();
        Arrays.sort(expert);
        for (int i = 0; i < expert.length; i++) {
            Assert.assertEquals(expert[i], sorted[i]);
        }
        System.out.println(Arrays.toString(sorted));
    }
}