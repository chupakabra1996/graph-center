package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.GraphType;
import ru.kpfu.itis.utils.RandomUtil;

import java.util.ArrayList;

import static ru.kpfu.itis.utils.GraphUtils.*;

public class TestGraphGenerator implements GraphGenerator {

    private AdjacentMatrixGraph graph;
    private GraphType graphType;
    private int capacity;

    private RandomUtil randomUtil;

    private ArrayList<Integer> vertexes;

    public TestGraphGenerator(int capacity, GraphType graphType) {

        graph = new AdjacentMatrixGraph(capacity);

        vertexes = new ArrayList<>(capacity);

        for (Integer i = 0; i < capacity; i++) {
            vertexes.add(i);
        }

        this.graphType = graphType;
        randomUtil = new RandomUtil();
        this.capacity = capacity;
    }


    @Override
    public AdjacentMatrixGraph generateGraph() {

        double dense;

        double desiredDense = generateDense(randomUtil.nextInt(Integer.MAX_VALUE), graphType);

        System.out.println("desired dense = " + desiredDense);

        dense = makeCascade();

        System.out.println("after preparing graph vertex count is : " + graph.getVertexCount());
        System.out.println("after preparing graph edge count is : " + graph.getEdgeCount());
        System.out.println("after preparing graph it's dense is : " + dense);

        System.out.println("\n");

        System.out.println("generating...");

        while (dense <= desiredDense || (graph.getVertexCount() != graph.getCapacity())) {

            if (graph.addEdge(randomUtil.nextInt(capacity), randomUtil.nextInt(capacity),
                    randomUtil.nextDouble(MAX_EDGE_WEIGHT))) {

                dense = computeDense(graph.getCapacity(), graph.getEdgeCount());
            }
        }

        System.out.println("\n");

        System.out.println("real dense = " + dense);

        System.out.println("edge count = " + graph.getEdgeCount());

        System.out.println("vertex count = " + graph.getVertexCount());

        return graph;
    }


    private double makeCascade() {

        System.out.println("starting making cascade...");

        double dense = 0;

        int firstVertex = getVertex();

        while (!vertexes.isEmpty()) {

            int s = getVertex();

            if (graph.addSimpleEdge(firstVertex, s, randomUtil.nextDouble(MAX_EDGE_WEIGHT))) {
                dense = computeDense(graph.getCapacity(), graph.getEdgeCount());
                firstVertex = s;
            }
        }

        System.out.println("making cascade has finished!");

        return dense;
    }

    private Integer getVertex() {

        int vertex = randomUtil.nextInt(vertexes.size());
        Integer value = vertexes.get(vertex);

        vertexes.remove(vertex);

        return value;
    }

    @Override
    public void setGraphType(GraphType graphType) {
        this.graphType = graphType;
    }
}
