package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public interface GraphGenerator {

    /**
     * Generates a graph
     *
     * @return Graph
     */
    AdjacentMatrixGraph generateGraph();

    void setGraphType(SimpleGraphGenerator.GraphType graphType);

}
