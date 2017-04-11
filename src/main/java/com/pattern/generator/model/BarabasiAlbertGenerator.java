package com.pattern.generator.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.pattern.generator.ModelEdge;
import com.pattern.generator.runner.Runner;
import it.unimi.dsi.fastutil.ints.*;

public final class BarabasiAlbertGenerator<R> {

    private BarabasiAlbertGenerator() {}

    public static void generate(int n, int m, int seed, Runner runner) {
        if (n < 1 || m < 1 || m >=n) return;

        runner.start();

        IntStream.range(0, m).forEach(runner::addVertex);

        IntSet targets = new IntArraySet(IntIterators.pour(IntIterators.fromTo(0, m)));

        IntList repeatedVertices = new IntArrayList();

        int source = m;

        while (source < n) {
            Set<ModelEdge> zipEdges = new HashSet<>();

            // For example purposes, re: finality req's for closures on local variables
            final int fsource = source;
            targets.forEach(t -> zipEdges.add(new ModelEdge(fsource, t)));

            runner.addVertex(source);

            zipEdges.forEach(e -> runner.addEdge(e.fromVertex, e.toVertex));

            repeatedVertices.addAll(targets);
            repeatedVertices.addAll(Collections.nCopies(m, source).stream().collect(Collectors.toList()));

            targets = randomSubset(repeatedVertices, m, seed + 7);

            ++source;
        }

        runner.stop();
    }

    private static IntSet randomSubset(IntList seq, int m, long seed) {
        Random rand = new Random(seed);
        IntSet targets = new IntArraySet();

        while (targets.size() < m)
            targets.add(seq.get(rand.nextInt(seq.size())));

        return targets;
    }
}
