package ru.kpfu.itis.graph;

import java.util.*;

public class AdjacentListGraph implements Graph {

    private int vertexCount;
    private boolean isEmpty;

    private Map<Integer, List<Vertex>> adjacentList;
    private Map<Integer, List<Edge>> edgesWeightList;

    public AdjacentListGraph(int vertexCount) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Graph must contain at least 1 vertex");
        }
        this.vertexCount = vertexCount;
        adjacentList = new HashMap<>(vertexCount);
        edgesWeightList = new HashMap<>(vertexCount);

        isEmpty = true;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        if (vertexCount > vertex) {
            List<Vertex> tmp = adjacentList.get(vertex);
            if (tmp == null) {
                return new ArrayList<>(0);
            }
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
            List<Edge> result = edgesWeightList.get(vertex);
            return result == null ? new ArrayList<>(0) : result;
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<Edge> getSortedIncidenceEdges(int vertex) {
        List<Edge> edges = getIncidenceEdges(vertex);
        Collections.sort(edges, Edge.compareByWeight());
        return edges;
    }

    @Override
    public boolean addEdge(int s, int e, Double weight) {
        if (weight == Double.POSITIVE_INFINITY || weight <= 0) {
            return false;
        }

        if (s == e) {
            return false;
        }

        if (isEmpty) {

            adjacentList.put(s, new ArrayList<>());
            adjacentList.put(e, new ArrayList<>());

            adjacentList.get(s).add(new Vertex(e));
            adjacentList.get(e).add(new Vertex(s));


            edgesWeightList.put(e, new ArrayList<>());
            edgesWeightList.put(s, new ArrayList<>());

            edgesWeightList.get(s).add(new Edge(s, e, weight));
            edgesWeightList.get(e).add(new Edge(s, e, weight));

            isEmpty = false;

            return true;
        }

        if (adjacentList.containsKey(s) || adjacentList.containsKey(e)) {

            if (adjacentList.get(s) == null) {
                edgesWeightList.put(s, new ArrayList<>());
                adjacentList.put(s, new ArrayList<>());
            }


            if (adjacentList.get(e) == null) {
                edgesWeightList.put(e, new ArrayList<>());
                adjacentList.put(e, new ArrayList<>());
            }

            if (!adjacentList.get(s).contains(new Vertex(e))) {

                adjacentList.get(s).add(new Vertex(e));
                adjacentList.get(e).add(new Vertex(s));

                edgesWeightList.get(s).add(new Edge(s, e, weight));
                edgesWeightList.get(e).add(new Edge(s, e, weight));

                return true;
            }

        }

        return false;
    }

}
