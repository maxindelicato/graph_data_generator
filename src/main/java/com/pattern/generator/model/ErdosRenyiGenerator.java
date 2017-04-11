package com.pattern.generator.model;

import java.util.*;
import com.pattern.algorithm.Combinatorics;
import com.pattern.generator.runner.Runner;
import it.unimi.dsi.fastutil.ints.IntIterators;

public final class ErdosRenyiGenerator {

    private ErdosRenyiGenerator() {}

    public static void generate(int n, double p, int seed, boolean directed, Runner runner) {
        if (n < 1 || p < 0) return;

        runner.start();

        IntIterators.pour(IntIterators.fromTo(0, n)).forEach(runner::addVertex);

        Random random = new Random(seed);

        Iterator<int[]> iter = directed ? Combinatorics.permutation(n, 2) : Combinatorics.combination(n, 2);
        while (iter.hasNext()) {
            int[] e = iter.next();
            if (p >= 1 || random.nextDouble() < p)
                runner.addEdge(e[0], e[1]);
        }

        runner.stop();
    }
}
