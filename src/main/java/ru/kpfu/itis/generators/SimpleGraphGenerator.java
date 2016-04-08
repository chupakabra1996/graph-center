package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentListGraph;
import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.Graph;
import ru.kpfu.itis.utils.GraphUtils;
import ru.kpfu.itis.utils.RandomUtil;

import java.util.Random;

public class SimpleGraphGenerator implements GraphGenerator {

    private final static double MIN_DENSE = 0.85;
    private final static double MAX_SPARSE = 0.80;
    private final static double MIN_SPARSE = 0.5;

    private Graph graph;
    private int capacity;

    private RandomUtil randomUtil;

    private GraphType graphType;


    public SimpleGraphGenerator(int capacity, GraphType graphType) {

        if (graphType == GraphType.DENSE) {
            graph = new AdjacentMatrixGraph(capacity);
        } else {
            graph = new AdjacentListGraph(capacity);
        }

        randomUtil = new RandomUtil();
        this.capacity = capacity;
        this.graphType = graphType;
    }

    @Override
    public Graph generateGraph() {

        double dense = 0;

        Random random = new Random(randomUtil.nextInt(Integer.MAX_VALUE));

        double desiredDense = 0;

        if (graphType == GraphType.DENSE) {
            //dense between MIN_DENSE and 1
            desiredDense = random.nextDouble() * (1 - MIN_DENSE) + MIN_DENSE;
        } else {
            desiredDense = random.nextDouble() * (MAX_SPARSE - MIN_SPARSE) + MIN_SPARSE;
        }

        System.out.println(desiredDense);

        while (dense <= desiredDense) {

            int s = randomUtil.nextInt(capacity);
            int e = randomUtil.nextInt(capacity);

            double weight = randomUtil.nextDouble(GraphUtils.MAX_EDGE_WEIGHT);

            if (graph.addEdge(s, e, weight)) {
                dense = computeDense(graph.getCapacity(), graph.getEdgeCount());
            }

        }

        return graph;
    }

    private double computeDense(int vertices, int edges) {
        return (2 * edges) / (double) (vertices * (vertices - 1));
    }


    public enum GraphType {
        DENSE, SPARSE;
    }

}
