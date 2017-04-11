package com.pattern.generator.model;

import com.pattern.generator.runner.Neo4jRunner;
import com.pattern.util.EnvironmentLoader;
import org.junit.Test;

public class WattsStrogatzGeneratorTest {

    @Test
    public void wattsStrogatzGenerator_shouldOutputExpectedGraph() {
        EnvironmentLoader env = new EnvironmentLoader();

        int n = 100;
        int k = 5;
        double p = 0.3;
        int seed = 38498;

        WattsStrogatzGenerator.generate(n, k, p, seed,
                new Neo4jRunner(env.getDatabaseUrl(), env.getDatabaseUsername(), env.getDatabasePassword()));

        //TODO: make this an interable generation algorithm
    }
}