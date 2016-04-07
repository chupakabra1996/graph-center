package ru.kpfu.itis.utils.generator;

import ru.kpfu.itis.graph.Graph;

public interface GraphGenerator {

    /**
     * Generates a dense graph
     *
     * @return Graph
     */
    Graph generateDenseGraph(int vertexCount);


    /**
     * Generates a sparse graph
     *
     * @return Graph
     */
    Graph generateSparseGraph(int vertexCount);

}
