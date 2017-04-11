package com.pattern.algorithm;

import java.util.*;

public final class CombinationIterator implements Iterator<int[]> {

//    private int[] arr;
//    private int[] ind;
//    private boolean hasNext;

    private boolean hasNext;
    private final int n;
    private final int k;
    private final int[] a;
    private final int[] result;

//    private int[] output;

    CombinationIterator(final int n, final int k){
        if (!((n < 1) && (k < 0 || k > n))) hasNext = true;

        this.n = n;
        this.k = k;
        a = new int[k];
        result = new int[k];
    }

    public boolean hasNext() {
        return hasNext;
    }

    public int[] next() {
        if (!hasNext)
            throw new NoSuchElementException();

        System.arraycopy(a, 0, result, 0, k);
        computeNext();
        return result;
    }

    private void computeNext() {
        if (a.length == 0) {
            for (int i = 0; (a[i] = i) < k - 1; i++);
            return;
        }

        int i;
        for (i = k - 1; i >= 0 && a[i] == n - k + i; i--);

        if (i < 0) {
            hasNext = false;
            return;
        }

        a[i]++;
        for (++i; i < k; i++)
            a[i] = a[i - 1] + 1;
    }
}

