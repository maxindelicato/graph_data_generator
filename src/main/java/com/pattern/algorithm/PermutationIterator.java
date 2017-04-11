package com.pattern.algorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * This is mostly snatched from
 * https://github.com/aisrael/jcombinatorics/blob/develop/src/main/java/jcombinatorics/permutations/SepaPnkIterator.java
 * because it's a darn good implementation of the SEPA algorithm. It's been augmented with some additional cleanup and
 * optimizations.
 */
public final class PermutationIterator implements Iterator<int[]> {

    private boolean hasNext;
    private final int n;
    private final int k;
    private final int[] a;
    private final int[] result;

    public PermutationIterator(final int n, final int k) {
        if (!((n < 1) && (k < 0 || k > n))) hasNext = true;

        this.n = n;
        this.k = k;
        a = firstSequence(n);
        result = new int[k];
    }

    public final boolean hasNext() {
        return hasNext;
    }

    public final int[] next() {
        if (!hasNext)
            throw new NoSuchElementException();

        System.arraycopy(a, 0, result, 0, k);
        computeNext();
        return result;
    }

    private void computeNext() {
        int i = k - 1;
        int j = k;

        for (;j < n && a[i] >= a[j]; ++j);

        if (j < n) {
            swap(i, j);
            return;
        }

        reverseRightOf(i);

        for (--i; i >= 0 && a[i] >= a[i + 1]; --i);

        if (i < 0) {
            hasNext = false;
            return;
        }

        for (--j; j > i && a[i] >= a[j]; --j);

        swap(i, j);
        reverseRightOf(i);
    }

    private void reverseRightOf(final int start) {
        int i = start + 1;
        int j = n - 1;
        while (i < j) {
            swap(i, j);
            ++i;
            --j;
        }
    }

    private void swap(final int x, final int y) {
        final int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    private int[] firstSequence(final int n) {
        final int[] s = new int[n];
        for (int i = s.length - 1; i >= 0; --i)
            s[i] = i;
        return s;
    }
}

