package ru.kpfu.itis.algo;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public interface Algorithm {

    /**
     * Implement this method
     *
     * @param graph - the undirected connected graph
     * @return the index of the center or the one of the centers of the graph
     */
    Integer getGraphCenter(AdjacentMatrixGraph graph);



}
