package com.pattern.generator.model;

import java.util.*;

import com.pattern.generator.ModelEdge;
import com.pattern.generator.runner.Runner;
import it.unimi.dsi.fastutil.ints.*;

public final class DorogovtsevMendesGenerator {

    private DorogovtsevMendesGenerator() {}

    public static void generate(int n, long seed, Runner runner) {
        if (n < 1) return;

        Random random = new Random(seed);
        IntSet vertices = new IntArraySet();
        Set<ModelEdge> edges = new HashSet<>();
        int initNodeNum = 3;

        runner.start();

        for (int i = 0; i < initNodeNum; i++)
            vertices.add(i);

        for (int i = 0; i < vertices.size(); i++) {
            int target = (i < vertices.size() - 1) ? i : 0;
            edges.add(new ModelEdge(i, target));
        }

        for (int i = vertices.size(); i < n; i++) {
            vertices.add(i);

            ModelEdge e = edges.toArray(new ModelEdge[edges.size()])[random.nextInt(i)];
            edges.add(new ModelEdge(e.fromVertex, i));
            edges.add(new ModelEdge(e.toVertex, i));
        }

        vertices.stream().forEach(runner::addVertex);
        edges.stream().forEach(e -> runner.addEdge(e.fromVertex, e.toVertex));

        runner.stop();
    }
}
