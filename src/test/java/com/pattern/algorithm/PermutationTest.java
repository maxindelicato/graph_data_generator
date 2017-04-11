package com.pattern.algorithm;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PermutationTest {

    @Test
    public void permutationIterator5n3k_shouldOutputExpectedPermutations() {
        int[][] expected = { {0, 1, 2}, {0, 1, 3}, {0, 1, 4}, {0, 2, 1}, {0, 2, 3}, {0, 2, 4}, {0, 3, 1}, {0, 3, 2},
                             {0, 3, 4}, {0, 4, 1}, {0, 4, 2}, {0, 4, 3}, {1, 0, 2}, {1, 0, 3}, {1, 0, 4}, {1, 2, 0},
                             {1, 2, 3}, {1, 2, 4}, {1, 3, 0}, {1, 3, 2}, {1, 3, 4}, {1, 4, 0}, {1, 4, 2}, {1, 4, 3},
                             {2, 0, 1}, {2, 0, 3}, {2, 0, 4}, {2, 1, 0}, {2, 1, 3}, {2, 1, 4}, {2, 3, 0}, {2, 3, 1},
                             {2, 3, 4}, {2, 4, 0}, {2, 4, 1}, {2, 4, 3}, {3, 0, 1}, {3, 0, 2}, {3, 0, 4}, {3, 1, 0},
                             {3, 1, 2}, {3, 1, 4}, {3, 2, 0}, {3, 2, 1}, {3, 2, 4}, {3, 4, 0}, {3, 4, 1}, {3, 4, 2},
                             {4, 0, 1}, {4, 0, 2}, {4, 0, 3}, {4, 1, 0}, {4, 1, 2}, {4, 1, 3}, {4, 2, 0}, {4, 2, 1},
                             {4, 2, 3}, {4, 3, 0}, {4, 3, 1}, {4, 3, 2} };

        PermutationIterator iter = Combinatorics.permutation(5, 3);

        for (int i = 0; iter.hasNext(); i++)
            assertArrayEquals(expected[i], iter.next());
    }

    @Test
    public void permutationIterator5n3k_shouldOutputExpectedCount() {
        int expected = 60;

        PermutationIterator iter = Combinatorics.permutation(5, 3);

        int count = 0;
        for (; iter.hasNext(); iter.next()) ++count;

        assertEquals(expected, count);
    }
}