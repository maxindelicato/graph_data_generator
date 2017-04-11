package com.pattern.algorithm;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CombinationTest {

    @Test
    public void combinationIterator5n3k_shouldOutputExpectedPermutations() {
        int[][] expected = { {0, 0, 0}, {0, 0, 1}, {0, 0, 2}, {0, 0, 3}, {0, 0, 4}, {0, 1, 2}, {0, 1, 3}, {0, 1, 4},
                             {0, 2, 3}, {0, 2, 4}, {0, 3, 4}, {1, 2, 3}, {1, 2, 4}, {1, 3, 4}, {2, 3, 4} };

        CombinationIterator iter = Combinatorics.combination(5, 3);

        for (int i = 0; iter.hasNext(); i++)
            assertArrayEquals(expected[i], iter.next());
    }

    @Test
    public void combinationIterator5n3k_shouldOutputExpectedCount() {
        int expected = 15;

        CombinationIterator iter = Combinatorics.combination(5, 3);

        int count = 0;
        for (; iter.hasNext(); iter.next()) ++count;

        assertEquals(expected, count);
    }
}