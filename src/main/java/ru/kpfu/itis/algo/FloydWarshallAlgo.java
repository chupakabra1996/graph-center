package ru.kpfu.itis.algo;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public class FloydWarshallAlgo implements Algorithm {

    private Double[][] distanceMatrix;
    private AdjacentMatrixGraph graph;


    public FloydWarshallAlgo(AdjacentMatrixGraph graph) {
        this.graph = graph;
        distanceMatrix = graph.getAdjacentMatrix();
    }

    @Override
    public Integer getGraphCenter(AdjacentMatrixGraph graph) {
        return null;
    }
}
