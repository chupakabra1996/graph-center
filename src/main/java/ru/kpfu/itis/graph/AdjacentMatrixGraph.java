package ru.kpfu.itis.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacentMatrixGraph implements Serializable{

    private static final long serialVersionUID = 2064868641859661123L;  //для сериалиации, чтобы сравнивать версии объектов, забейте!

    private int capacity;   //размер графа, например 10 на 10

    private int vertexCount; //реальное кол-во вершин в графе, нужно чтобы генерировать граф
    private int edgeCount;  //кол-во ребер в графе

    private Double[][] adjacentMatrix;  //матрица смежности

    public AdjacentMatrixGraph(int capacity) {

        this.capacity = capacity;
        adjacentMatrix = new Double[capacity][capacity];

        //инициализируем бесконечностями
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                adjacentMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        vertexCount = 0;
        edgeCount = 0;
    }

    //вернет вам то, что нужно
    public Double[][] getAdjacentMatrix(){
        return adjacentMatrix;
    }

    //вернет список смежных с вршиной vertex вершин
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


    //вернет список инцидентных вершине vertex ребер
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


    //вернет то же , что и выше, но в отсортированном порядке
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

    //печаталка =)
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

                if (adjacentMatrix[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.printf("%10s", "inf");
                    continue;
                }

                System.out.printf("%10.0f", adjacentMatrix[i][j]);
            }
            System.out.println();
        }
    }


    //добаляет ребро в граф, причем чудесным образом
    public boolean addEdge(int s, int e, Double weight) {

        if (s >= capacity || e >= capacity) {
            return false;
        }

        if (weight == Double.POSITIVE_INFINITY) {
            return false;
        }

        if (s == e) {
            return false;
        }

        if (adjacentMatrix[s][e] == Double.POSITIVE_INFINITY) {

            if (vertexCount == 0 && edgeCount == 0) {

                adjacentMatrix[s][e] = weight;
                adjacentMatrix[e][s] = weight;

                edgeCount++;
                vertexCount += 2;

                return true;
            }

            boolean containsS = contains(s);
            boolean containsE = contains(e);

            if (containsS || containsE) {

                if (!containsS || !containsE) {
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

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public int getCapacity() {
        return capacity;
    }


}
