package com.howard.test.leetcode.array;

import com.howard.leetcode.array.DominantIndexSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试
 *
 * @author howard he
 * @create 2018/10/11 10:05
 */
public class DominantIndexSolutionTest {

    private DominantIndexSolution solution;

    @Before
    public void setUp() {
        solution = new DominantIndexSolution();
    }

    @Test
    public void dominantIndex_test() {

        Assert.assertEquals(1, solution.dominantIndex(new int[] {3, 6, 1, 0}));

        Assert.assertEquals(-1, solution.dominantIndex(new int[] {1, 2, 3, 4}));
    }

}
