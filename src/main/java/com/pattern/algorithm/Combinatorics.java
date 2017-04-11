package com.pattern.algorithm;

public final class Combinatorics {

    public static FactorialIterator factorial(int num) {
        return new FactorialIterator(num);
    }

    public static FactorialIterator factorial(int num, boolean desc) {
        return new FactorialIterator(num, desc);
    }

    public static PermutationIterator permutation(int n, int k) {
        return new PermutationIterator(n, k);
    }

    public static CombinationIterator combination(int n, int k) {
        return new CombinationIterator(n, k);
    }
}


