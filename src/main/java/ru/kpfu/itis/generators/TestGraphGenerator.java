package ru.kpfu.itis.generators;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.GraphType;
import ru.kpfu.itis.utils.RandomUtil;

import java.util.ArrayList;

import static ru.kpfu.itis.utils.GraphUtils.*;

public class TestGraphGenerator {

    private AdjacentMatrixGraph graph;
    private GraphType graphType;
    private int capacity;

    private RandomUtil randomUtil;

    private ArrayList<Integer> usedVertexes;
    private ArrayList<Integer> notUsedVertexes;

    public TestGraphGenerator(int capacity, GraphType graphType) {

        graph = new AdjacentMatrixGraph(capacity);

        usedVertexes = new ArrayList<>(capacity);
        notUsedVertexes = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; i++) {
            notUsedVertexes.add(i);
        }

        this.graphType = graphType;
        randomUtil = new RandomUtil();
        this.capacity = capacity;
    }


    public AdjacentMatrixGraph generateGraph() {

        double currentDense;

        double desiredDense = generateDense(randomUtil.nextInt(Integer.MAX_VALUE), graphType);

        System.out.println("desired dense = " + desiredDense);

        currentDense = makeCascade();

        //for GC
        usedVertexes = null;
        notUsedVertexes = null;

        System.out.println("after preparing graph vertex count is : " + graph.getVertexCount());
        System.out.println("edge count is : " + graph.getEdgeCount());
        System.out.println("dense is : " + currentDense);

        System.out.println("==========================================================\n");

        System.out.println("generating...");

        while (currentDense <= desiredDense) {

            if (graph.addEdge(randomUtil.nextInt(capacity), randomUtil.nextInt(capacity),
                    randomUtil.nextDouble(MAX_EDGE_WEIGHT))) {

                currentDense = computeDense(capacity, graph.getEdgeCount());
            }
        }

        System.out.println("==========================================================\n");

        System.out.println("real dense = " + currentDense);

        System.out.println("edge count = " + graph.getEdgeCount());

        System.out.println("vertex count = " + graph.getVertexCount());

        System.out.println("==========================================================\n");

        return graph;
    }



    private double makeCascade() {

        System.out.println("starting making cascade...");

        double dense;

        int s = getVertexFromNotUsed();
        int e = getVertexFromNotUsed();

        //at first add at least one edge
        graph.addSimpleEdge(s, e, randomUtil.nextDouble(MAX_EDGE_WEIGHT));
        dense = computeDense(capacity, 1);

        while (!notUsedVertexes.isEmpty()) {

            s = getVertexFromUsed();
            e = getVertexFromNotUsed();

            if (graph.addSimpleEdge(s, e, randomUtil.nextDouble(MAX_EDGE_WEIGHT))) {
                dense = computeDense(capacity, graph.getEdgeCount());
            }
        }

        System.out.println("making cascade has finished!");

        return dense;
    }

    //gets vertex from used vertexes
    private Integer getVertexFromUsed() {

        return usedVertexes.get(randomUtil.nextInt(usedVertexes.size()));
    }


    //gets vertex from not used vertexes
    private Integer getVertexFromNotUsed() {

        int vertex = randomUtil.nextInt(notUsedVertexes.size());
        Integer value = notUsedVertexes.get(vertex);

        notUsedVertexes.remove(vertex);

        usedVertexes.add(value);

        return value;
    }

    public void setGraphType(GraphType graphType) {
        this.graphType = graphType;
    }
}
