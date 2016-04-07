package ru.kpfu.itis.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacentMatrixGraph implements Graph {

    private int vertexCount;

    private Double[][] adjacentMatrix;

    public AdjacentMatrixGraph(int vertexCount) {

        this.vertexCount = vertexCount;
        adjacentMatrix = new Double[vertexCount][vertexCount];
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {

        
        return new ArrayList<>(0);
    }

    @Override
    public List<Edge> getIncidenceEdges(int vertex) {
        return null;
    }

    @Override
    public List<Edge> getSortedIncidenceEdges(int vertex) {
        return null;
    }
}
