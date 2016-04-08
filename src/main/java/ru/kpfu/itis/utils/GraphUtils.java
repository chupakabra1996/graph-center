package ru.kpfu.itis.utils;

import ru.kpfu.itis.generators.DenseGraphGenerator;
import ru.kpfu.itis.graph.Graph;
import ru.kpfu.itis.generators.GraphGenerator;

public class GraphUtils {


    /**
     * Generates a graph
     * @param graphGenerator - Graph generator
     * @return graph
     */
    public static Graph generateGraph(GraphGenerator graphGenerator) {

        return graphGenerator.generateGraph();
    }


    public static void main(String[] args) {

        GraphGenerator graphGenerator = new DenseGraphGenerator(5);
        Graph g = GraphUtils.generateGraph(graphGenerator);

        g.print();
    }
}
