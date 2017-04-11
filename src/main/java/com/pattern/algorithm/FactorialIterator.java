package com.pattern.algorithm;

import java.util.*;

public final class FactorialIterator implements Iterator<Integer> {

    private int num;
    private int product;
    private int currNum;
    private boolean hasNext;
    private boolean desc;

    FactorialIterator(int num){
        if (num > 0) {
            hasNext = true;
            product = 1;
            currNum = 0;
        }

        this.num = num;
    }

    FactorialIterator(int num, boolean desc){
        if (num > 0) {
            hasNext = true;
            product = 1;
            currNum = desc ? num + 1 : 0;
        }

        this.desc = desc;
        this.num = num;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public Integer next() {
        if (!hasNext)
            throw new NoSuchElementException();

        currNum = desc ? --currNum : ++currNum;
        product *= currNum;
        hasNext = desc ? !(currNum == 1) : !(currNum == num);

        return product;
    }
}

