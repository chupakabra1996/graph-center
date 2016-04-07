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

        for (int i = 0; i < vertexCount; i++) {
            adjacentList.put(i,new ArrayList<>());
            edgesWeightList.put(i, new ArrayList<>());
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        if (vertexCount > vertex) {
            List<Vertex> tmp = adjacentList.get(vertex);
            if (tmp == null){
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

        if (adjacentList.size() == 0){
            adjacentList.get(e).add(new Vertex(s));

            edgesWeightList.get(s).add(new Edge(s, e, weight));

            return true;
        }

        if (adjacentList.containsKey(s) || adjacentList.containsKey(e)) {

            if (!adjacentList.get(s).contains(new Vertex(e))) {

                adjacentList.get(s).add(new Vertex(e));
                adjacentList.get(e).add(new Vertex(s));

                edgesWeightList.get(s).add(new Edge(s, e, weight));

                return true;
            }

        }

        return false;
    }


    public static void main(String[] args) {

        Graph graph = new AdjacentListGraph(5);

        graph.addEdge(0,4,2.1);
        graph.addEdge(0,3,10.3);
        graph.addEdge(0,2,11.1);
        graph.addEdge(0,1,102.1);

        graph.addEdge(1,2,102.1);
        graph.addEdge(1,4,102.1);

        graph.addEdge(2,3,102.1);
        graph.addEdge(2,4,102.1);


        System.out.println(graph.getAdjacentVertices(0));

        System.out.println(graph.getAdjacentVertices(1));

        System.out.println(graph.getAdjacentVertices(2));

        System.out.println(graph.getAdjacentVertices(3));

        System.out.println(graph.getAdjacentVertices(4));

    }

}
