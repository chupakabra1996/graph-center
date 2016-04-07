package ru.kpfu.itis.graph;

import java.util.*;

public class AdjacentListGraph implements Graph {

    private int vertexCount;

    private Map<Integer, List<Vertex>> adjacentList;
    private Map<Integer, List<Edge>> edgesWeightList;

    public AdjacentListGraph(int vertexCount) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Graph must contain at least 1 vertex");
        }
        this.vertexCount = vertexCount;
        adjacentList = new HashMap<>(vertexCount);
        edgesWeightList = new HashMap<>(vertexCount);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        if (vertexCount > vertex) {
            List<Vertex> tmp = adjacentList.get(vertex);
            ArrayList<Integer> result = new ArrayList<>(tmp.size());
            for (Vertex current : tmp) {
                result.add(current.getId());
            }
            return result;
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<Edge> getIncidenceEdges(int vertex) {
        if (vertexCount > vertex) {
            return edgesWeightList.get(vertex);
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<Edge> getSortedIncidenceEdges(int vertex) {
        List<Edge> edges = getIncidenceEdges(vertex);
        Collections.sort(edges, Edge.compareByWeight());
        return edges;
    }
}
