package ru.kpfu.itis.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacentMatrixGraph implements Graph {

    private int vertexCount;

    private boolean isEmpty;

    private Double[][] adjacentMatrix;

    public AdjacentMatrixGraph(int vertexCount) {

        this.vertexCount = vertexCount;
        adjacentMatrix = new Double[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                adjacentMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        isEmpty = true;
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


    private boolean contains(int s, int e){
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjacentMatrix[e][i] != Double.POSITIVE_INFINITY
                        || adjacentMatrix[s][i] != Double.POSITIVE_INFINITY){
                    return true;
                }
            }
        }
        return false;
    }

    public void  print(){
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                System.out.print(adjacentMatrix[i][j]+", ");
            }
            System.out.println();
        }
    }


    @Override
    public boolean addEdge(int s, int e, Double weight) {

        if (weight == Double.POSITIVE_INFINITY) {
            return false;
        }

        if (s == e){
            return false;
        }

        if (adjacentMatrix[s][e] == Double.POSITIVE_INFINITY) {

            if (isEmpty){
                isEmpty = false;
                adjacentMatrix[s][e] = weight;
                adjacentMatrix[e][s] = weight;
                return true;
            }

            if (contains(s,e)){

                adjacentMatrix[s][e] = weight;
                adjacentMatrix[e][s] = weight;

                return true;
            }
        }

        return false;
    }

}
