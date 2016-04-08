package ru.kpfu.itis.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacentMatrixGraph implements Graph {

    private int capacity;

    private int vertexCount;
    private int edgeCount;

    private boolean isEmpty;

    private Double[][] adjacentMatrix;

    public AdjacentMatrixGraph(int capacity) {

        this.capacity = capacity;
        adjacentMatrix = new Double[capacity][capacity];

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                adjacentMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        vertexCount = 0;
        edgeCount = 0;

        isEmpty = true;
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {

        if (capacity > vertex) {

            ArrayList<Integer> result = new ArrayList<>();

            for (int i = 0; i < capacity; i++) {

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

        if (capacity > vertex) {

            ArrayList<Edge> result = new ArrayList<>();

            for (int i = 0; i < capacity; i++) {

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


    private boolean contains(int vertex) {
        for (int i = 0; i < capacity; i++) {
            if (adjacentMatrix[vertex][i] != Double.POSITIVE_INFINITY) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                if (adjacentMatrix[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("\tINF\t");
                    continue;
                }
                System.out.print("\t" + adjacentMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }


    @Override
    public boolean addEdge(int s, int e, Double weight) {

        if (s >= capacity || e >= capacity){
            return false;
        }

        if (weight == Double.POSITIVE_INFINITY) {
            return false;
        }

        if (s == e) {
            return false;
        }

        if (adjacentMatrix[s][e] == Double.POSITIVE_INFINITY) {

            if (isEmpty) {
                isEmpty = false;

                adjacentMatrix[s][e] = weight;
                adjacentMatrix[e][s] = weight;

                edgeCount++;
                vertexCount += 2;

                return true;
            }

            boolean containsS = contains(s);
            boolean containsE = contains(e);

            if (containsS || containsE) {

                if (!containsS || !containsE){
                    vertexCount++;
                }

                adjacentMatrix[s][e] = weight;
                adjacentMatrix[e][s] = weight;

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


}
