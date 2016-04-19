package ru.kpfu.itis.graph;

import java.io.Serializable;

public class AdjacentMatrixGraph implements Serializable {

    private static final long serialVersionUID = 2064868641859661123L;  //для сериалиации, чтобы сравнивать версии объектов, забейте!

    private int capacity;   //размер графа, например 10 на 10

    private int vertexCount; //реальное кол-во вершин в графе, нужно чтобы генерировать граф
    private int edgeCount;  //кол-во ребер в графе

    private double[][] adjacentMatrix;  //матрица смежности

    public AdjacentMatrixGraph(int capacity) {

        this.capacity = capacity;
        adjacentMatrix = new double[capacity][capacity];

        //инициализируем бесконечностями
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                if (i == j) {
                    adjacentMatrix[i][j] = 0d;
                } else {
                    adjacentMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        vertexCount = 0;
        edgeCount = 0;
    }

    public double[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }


    private boolean contains(int vertex) {
        for (int i = 0; i < capacity; i++) {
            if (Double.compare(adjacentMatrix[vertex][i], Double.POSITIVE_INFINITY) != 0
                    && i != vertex) {
                return true;
            }
        }
        return false;
    }

    //печаталка =)
    public void print() {

        for (int i = 0; i < capacity; i++) {

            if (i == 0) {
                System.out.printf("%3s", "");
                for (int j = 0; j < capacity; j++) {

                    System.out.printf("%10s", j);
                }
                System.out.println("\n");

            }

            for (int j = 0; j < capacity; j++) {

                if (j == 0) {
                    System.out.printf("%3s", i);
                }

                if (Double.compare(adjacentMatrix[i][j], Double.POSITIVE_INFINITY) == 0) {
                    System.out.printf("%10s", "inf");
                    continue;
                }

                System.out.printf("%10.2f", adjacentMatrix[i][j]);
            }
            System.out.println("\n");
        }
    }


    private boolean containsEdge(int s, int e) {
        return adjacentMatrix[s][e] != Double.POSITIVE_INFINITY;
    }


    //добаляет ребро в граф, который уже полностью связен
    public boolean addEdge(int s, int e, double weight) {

        if (s == e || containsEdge(s, e)) {
            return false;
        }

        adjacentMatrix[s][e] = weight;
        adjacentMatrix[e][s] = weight;

        edgeCount++;

        return true;

    }


    //для создания каркаса для графа, то есть делаем его полностью свзяным
    public boolean addSimpleEdge(int s, int e, double weight) {

        if (s == e || containsEdge(s, e)) {
            return false;
        }

        if (vertexCount == 0 && edgeCount == 0) {

            adjacentMatrix[s][e] = weight;
            adjacentMatrix[e][s] = weight;

            vertexCount += 2;
            edgeCount++;

        } else {
            adjacentMatrix[s][e] = weight;
            adjacentMatrix[e][s] = weight;

            vertexCount++;
            edgeCount++;
        }

        return true;
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
