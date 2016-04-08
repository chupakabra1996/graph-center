package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.Graph;
import ru.kpfu.itis.utils.RandomUtil;

public class DenseGraphGenerator implements GraphGenerator {

    private final static double MIN_DENSE = 0.8;

    private Graph graph;

    private RandomUtil randomUtil;

    private int vertexCount;

    public DenseGraphGenerator(int vertexCount) {
        graph = new AdjacentMatrixGraph(vertexCount);
        randomUtil = new RandomUtil();
        this.vertexCount = vertexCount;
    }

    @Override
    public Graph generateGraph() {

        double dense = 0;

        while (dense <= MIN_DENSE) {

            int s = randomUtil.nextInt(vertexCount);
            int e = randomUtil.nextInt(vertexCount);
            double weight = randomUtil.nextDouble(36.6);

            graph.addEdge(s, e, weight);

            dense = computeDense(graph.getCapacity(),graph.getEdgeCount());
        }

        return graph;
    }


    private double computeDense(int vertices, int edges) {
        if (vertices == 0 || vertices == 1){
            return 0;
        }
        return (2 * edges) / (vertices * (vertices - 1));
    }

}
