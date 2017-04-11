package com.pattern.generator;

import java.util.Comparator;

public class ModelEdgeComparator implements Comparator<ModelEdge> {

    @Override
    public int compare(ModelEdge e1, ModelEdge e2) {
        if (e1.fromVertex == e2.fromVertex) {
            if (e1.toVertex == e2.toVertex)
                return 0;
            if (e1.toVertex > e2.toVertex)
                return 1;
            if (e1.toVertex < e2.toVertex)
                return -1;
        } else if (e1.fromVertex > e2.fromVertex)
            return 1;
        else if (e1.fromVertex < e2.fromVertex)
            return -1;

        // Never reached
        return 0;
    }
}
