package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentListGraph;
import ru.kpfu.itis.graph.Graph;

public class DenseGraphGenerator implements GraphGenerator {

    private final static double MIN_DENSE = 0.8;
    private Graph graph;


    public DenseGraphGenerator(int vertexCount) {
        graph = new AdjacentListGraph(vertexCount);
    }

    @Override
    public Graph generateGraph(int vertexCount) {
        return null;
    }

}
