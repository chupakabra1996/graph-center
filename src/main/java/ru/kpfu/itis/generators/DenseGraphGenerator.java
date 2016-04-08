package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.Graph;
import ru.kpfu.itis.utils.RandomUtil;

import java.util.Random;

public class DenseGraphGenerator implements GraphGenerator {

    private final static double MIN_DENSE = 0.8;
    private final static double MAX_EDGE_WEIGHT = 36.6;

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

        Random random = new Random(randomUtil.nextInt(Integer.MAX_VALUE));

        //dense between MIN_DENSE and 1
        double d = random.nextDouble() * (1 - MIN_DENSE) + MIN_DENSE;

        System.out.println(d);

        while (dense <= d) {

            int s = randomUtil.nextInt(vertexCount);
            int e = randomUtil.nextInt(vertexCount);

            double weight = randomUtil.nextDouble(MAX_EDGE_WEIGHT);

            if (graph.addEdge(s, e, weight)) {
                dense = computeDense(graph.getCapacity(), graph.getEdgeCount());
            }

        }

        return graph;
    }


    private double computeDense(int vertices, int edges) {
        return (2 * edges) / (double)(vertices * (vertices - 1));
    }

}
