package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.GraphType;

public interface GraphGenerator {

    /**
     * Generates a graph
     *
     * @return Graph
     */
    AdjacentMatrixGraph generateGraph();

    void setGraphType(GraphType graphType);

}
