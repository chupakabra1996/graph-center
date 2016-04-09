package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.Graph;

public interface GraphGenerator {

    /**
     * Generates a graph
     *
     * @return Graph
     */
    Graph generateGraph();


    void setGraphType(SimpleGraphGenerator.GraphType graphType);

}
