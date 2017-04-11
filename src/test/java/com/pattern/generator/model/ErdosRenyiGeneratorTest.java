package com.pattern.generator.model;

import com.pattern.generator.runner.Neo4jRunner;
import com.pattern.util.EnvironmentLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErdosRenyiGeneratorTest {

    @Test
    public void erdosRenyiGenerator_shouldOutputExpectedGraph() {
        EnvironmentLoader env = new EnvironmentLoader();

        int n = 100;
        double p = 0.01;
        int seed = 38498;
        boolean directed = true;

        ErdosRenyiGenerator.generate(n, p, seed, directed,
                new Neo4jRunner(env.getDatabaseUrl(), env.getDatabaseUsername(), env.getDatabasePassword()));

        //TODO: make this an interable generation algorithm
    }
}