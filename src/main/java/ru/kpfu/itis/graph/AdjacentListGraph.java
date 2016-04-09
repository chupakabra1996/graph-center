package ru.kpfu.itis.graph;

import java.util.*;

public class AdjacentListGraph implements Graph {

    private static final long serialVersionUID = -2258097606728923075L;

    private Map<Integer, List<Vertex>> adjacentList;

    private int capacity;
    private int vertexCount;
    private int edgeCount;
    private transient boolean isEmpty;

    private Map<Integer, List<Edge>> edgesWeightList;

    public AdjacentListGraph(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Graph must contain at least 1 vertex");
        }
        this.capacity = capacity;
        adjacentList = new HashMap<>(capacity);
        edgesWeightList = new HashMap<>(capacity);

        vertexCount = 0;
        edgeCount = 0;

        isEmpty = true;
    }


    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        if (capacity > vertex) {
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
        if (capacity > vertex) {
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

        if (s >= capacity || e >= capacity) {
            return false;
        }

        if (weight == Double.POSITIVE_INFINITY || weight <= 0) {
            return false;
        }

        if (s == e) {
            return false;
        }

        if (isEmpty) {

            adjacentList.put(s, new LinkedList<>());
            adjacentList.put(e, new LinkedList<>());

            adjacentList.get(s).add(new Vertex(e));
            adjacentList.get(e).add(new Vertex(s));


            edgesWeightList.put(e, new LinkedList<>());
            edgesWeightList.put(s, new LinkedList<>());

            edgesWeightList.get(s).add(new Edge(s, e, weight));
            edgesWeightList.get(e).add(new Edge(s, e, weight));

            edgeCount++;
            vertexCount += 2;

            isEmpty = false;

            return true;
        }


        boolean containS = adjacentList.containsKey(s);
        boolean containE = adjacentList.containsKey(e);

        if (containS || containE) {

            if (adjacentList.get(s) == null) {
                edgesWeightList.put(s, new LinkedList<>());
                adjacentList.put(s, new LinkedList<>());
            }


            if (adjacentList.get(e) == null) {
                edgesWeightList.put(e, new LinkedList<>());
                adjacentList.put(e, new LinkedList<>());
            }


            if (!adjacentList.get(s).contains(new Vertex(e))) {

                if (!containS || !containE) {
                    vertexCount++;
                }

                adjacentList.get(s).add(new Vertex(e));
                adjacentList.get(e).add(new Vertex(s));

                edgesWeightList.get(s).add(new Edge(s, e, weight));
                edgesWeightList.get(e).add(new Edge(s, e, weight));

                edgeCount++;

                return true;
            }

        }

        return false;
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void print() {

        for (int i = 0; i < capacity; i++) {

            if (i == 0) {
                System.out.printf("%3s","");
                for (int j = 0; j < capacity; j++) {

                    System.out.printf("%10s", j);
                }
                System.out.println("\n");

            }

            for (int j = 0; j < capacity; j++) {

                if (j == 0) {
                    System.out.printf("%3s", i);
                }
            }
            System.out.println();
        }
    }
}
