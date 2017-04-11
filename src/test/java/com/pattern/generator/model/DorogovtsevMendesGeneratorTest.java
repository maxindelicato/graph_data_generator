package com.pattern.generator.model;

import com.pattern.generator.runner.Neo4jRunner;
import com.pattern.util.EnvironmentLoader;
import org.junit.Test;

public class DorogovtsevMendesGeneratorTest {

    @Test
    public void dorogovtsevMendesGenerator_shouldOutputExpectedGraph() {
        EnvironmentLoader env = new EnvironmentLoader();

        int n = 100;
        int seed = 38498;

        DorogovtsevMendesGenerator.generate(n, seed,
                new Neo4jRunner(env.getDatabaseUrl(), env.getDatabaseUsername(), env.getDatabasePassword()));

        //TODO: make this an interable generation algorithm
    }
}