package ru.kpfu.itis.graph;

import java.io.Serializable;
import java.util.List;

public interface Graph extends Serializable {

    /**
     * Adjacent vertices to vertex
     * @param vertex - one of the graph's vertex
     * @return list of adjacent vertices
     */
    List<Integer> getAdjacentVertices(int vertex);


    /**
     * Incidence edges of vertex
     * @param vertex - one of the graph's vertex
     * @return list of edges
     */
    List<Edge> getIncidenceEdges(int vertex);


    /**
     * Incidence edges of vertex in sorted state
     * @param vertex - one of the graph's vertex
     * @return sorted list of edges
     */
    List<Edge> getSortedIncidenceEdges(int vertex);


    /**
     * Adds edge if exists first vertex or/and second vertex
     * @param s - first vertex
     * @param e - second vertex
     * @param weight - double weight of edge
     * @return true if adding edge is successful else false
     */
    boolean addEdge(int s, int e, Double weight);


    /**
     * Current count of vertices in graph
     * @return vertex count
     */
    int getVertexCount();


    /**
     * Current count of edges in graph
     * @return edges count
     */
    int getEdgeCount();


    /**
     * Capacity of graph / max count of vertices
     * @return capacity of graph
     */
    int getCapacity();


    /**
     * Prints the graph
     */
    void print();

}
