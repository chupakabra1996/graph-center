package ru.kpfu.itis.graph;

import java.util.List;

public interface Graph {

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

    List<Edge> getSortedIncidenceEdges(int vertex);

    boolean addEdge(int s, int e, Double weight);

}
