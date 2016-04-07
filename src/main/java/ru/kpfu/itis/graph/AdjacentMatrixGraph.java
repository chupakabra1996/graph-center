package ru.kpfu.itis.graph;

import java.util.ArrayList;
import java.util.Collections;
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

        if (vertexCount > vertex) {

            ArrayList<Integer> result = new ArrayList<>();

            for (int i = 0; i < vertexCount; i++) {

                if (Double.compare(adjacentMatrix[vertex][i], Double.POSITIVE_INFINITY) != 0) {
                    result.add(i);
                }
            }
            return result;
        }

        return new ArrayList<>(0);
    }

    @Override
    public List<Edge> getIncidenceEdges(int vertex) {

        if (vertexCount > vertex) {

            ArrayList<Edge> result = new ArrayList<>();

            for (int i = 0; i < vertexCount; i++) {

                if (Double.compare(adjacentMatrix[vertex][i], Double.POSITIVE_INFINITY) != 0) {
                    result.add(new Edge(vertex, i, adjacentMatrix[vertex][i]));
                }
            }

            return result;
        }

        return new ArrayList<>(0);
    }

    @Override
    public List<Edge> getSortedIncidenceEdges(int vertex) {

        List<Edge> result = getIncidenceEdges(vertex);

        Collections.sort(result, Edge.compareByWeight());

        return result;
    }


    @Override
    public boolean addEdge(int s, int e, Double weight) {
        return false;
    }

}
