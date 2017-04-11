package com.pattern.generator.runner;

public interface Runner {
    void start();
    void addVertex(int mvertex);
    void addEdge(int medgeFrom, int medgeTo);
    void stop();
}
