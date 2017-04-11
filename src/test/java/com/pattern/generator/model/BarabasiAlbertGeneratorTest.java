package com.pattern.generator.model;

import com.pattern.generator.runner.Neo4jRunner;
import com.pattern.util.EnvironmentLoader;
import org.junit.Test;

public class BarabasiAlbertGeneratorTest {

    @Test
    public void barabasiAlbertGenerator_shouldOutputExpectedGraph() {
        EnvironmentLoader env = new EnvironmentLoader();

        int n = 50;
        int m = 1;
        int seed = 38498;

        BarabasiAlbertGenerator.generate(n, m, seed,
                new Neo4jRunner(env.getDatabaseUrl(), env.getDatabaseUsername(), env.getDatabasePassword()));

        //TODO: make this an interable generation algorithm
    }
}