package com.pattern.algorithm;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FactorialTest {

    @Test
    public void factorialIterator5_shouldOutputExpectedFactorials() {
        FactorialIterator iter = Combinatorics.factorial(5);

        int[] expected = {1, 2, 6 , 24 , 120};
        for (int i = 0; iter.hasNext(); i++)
            assertEquals(expected[i], iter.next().intValue());
    }

    @Test
    public void factorialIterator5Desc_shouldOutputExpectedFactorials() {
        FactorialIterator iter = Combinatorics.factorial(5, true);

        int[] expected = {5, 20, 60 , 120 , 120};
        for (int i = 0; iter.hasNext(); i++)
            assertEquals(expected[i], iter.next().intValue());
    }

    @Test
    public void factorialIterator5Asc_shouldOutputExpectedFactorials() {
        FactorialIterator iter = Combinatorics.factorial(5, false);

        int[] expected = {1, 2, 6 , 24 , 120};
        for (int i = 0; iter.hasNext(); i++)
            assertEquals(expected[i], iter.next().intValue());
    }
}