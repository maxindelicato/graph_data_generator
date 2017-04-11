package com.pattern.generator.model;

import java.util.*;

import com.pattern.generator.ModelEdgeComparator;
import com.pattern.generator.runner.Runner;
import com.pattern.generator.ModelEdge;
import it.unimi.dsi.fastutil.ints.*;

public final class WattsStrogatzGenerator {

    private WattsStrogatzGenerator() {}

    public static void generate(int n, int k, double p, int seed, Runner runner) {
        if (n < 1 || k < 1 || k >= n || p < 0) return;

        Random random = new Random(seed);

        runner.start();

        IntList vertices = IntIterators.pour(IntIterators.fromTo(0, n));
        // We use a ModelEdge because primitive arrays (e.g. int[]) done't allow HashCode overriding, resulting in
        // duplicates within a Set
        Set<ModelEdge> edges = new TreeSet<>(new ModelEdgeComparator());

        for (IntListIterator iter = IntIterators.fromTo(1, k / 2 + 1); iter.hasNext();) {
            int j = iter.next();

            // subList(...) returns a reference that changes under us if we don't copy into a new list
            IntList targets = new IntArrayList(vertices.subList(j, vertices.size()).toIntArray());
            targets.addAll(new IntArrayList(vertices.subList(0, j).toIntArray()));

            for (int i = 0; i < vertices.size(); i++)
                edges.add(new ModelEdge(vertices.getInt(i), targets.getInt(i)));
        }

        for (IntListIterator iter = IntIterators.fromTo(1, k / 2 + 1); iter.hasNext();) {
            int j = iter.next();
            IntList targets = new IntArrayList(vertices.subList(j, vertices.size()).toIntArray());
            targets.addAll(new IntArrayList(vertices.subList(0, j).toIntArray()));

            for (int i = 0; i < vertices.size(); i++) {
                edges.add(new ModelEdge(vertices.getInt(i), targets.getInt(i)));
                int u = vertices.getInt(i);
                int v = targets.getInt(i);

                if (random.nextDouble() < p) {
                    int w = vertices.get(random.nextInt(vertices.size()));
                    boolean rewire = true;
                    while (w == u || edges.contains(new ModelEdge(u, w))) {
                        w = vertices.get(random.nextInt(vertices.size()));
                        if (degree(u, edges) >= n - 1) {
                            rewire = false;
                            break;
                        }
                    }

                    if (rewire) {
                        edges.remove(new ModelEdge(u, v));
                        edges.add(new ModelEdge(u, w));
                    }
                }
            }
        }


        // We avoid (un)boxing by not replacing the below with, e.g. vertices.forEach(runner::addVertex);
        for (int vertex : vertices)
            runner.addVertex(vertex);

        edges.forEach(e -> runner.addEdge(e.fromVertex, e.toVertex));

        runner.stop();
    }

    private static long degree(int node, Set<ModelEdge> edges) {
//        return edges.stream().filter(e -> e.fromVertex == node).count();
        long degree = 0;
        for (ModelEdge edge : edges)
            if (edge.fromVertex == node) ++degree;

        return degree;
    }
}

