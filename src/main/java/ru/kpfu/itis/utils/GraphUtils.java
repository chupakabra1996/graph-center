package ru.kpfu.itis.utils;

import ru.kpfu.itis.graph.Graph;
import ru.kpfu.itis.generators.GraphGenerator;

public class GraphUtils {


    /**
     * Generates a graph
     * @param graphGenerator - Graph generator
     * @param vertexCount - count of graph's vertices
     * @return graph
     */
    public static Graph generateGraph(GraphGenerator graphGenerator, int vertexCount) {

        return graphGenerator.generateGraph(vertexCount);
    }
}
