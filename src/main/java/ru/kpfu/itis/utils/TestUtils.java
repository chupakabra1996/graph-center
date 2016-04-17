package ru.kpfu.itis.utils;

import ru.kpfu.itis.algo.Algorithm;
import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public class TestUtils {


    public static double getTimeInMillis(Algorithm algorithm, AdjacentMatrixGraph graph, int iterations){

        long start = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++) {
            algorithm.getGraphCenter(graph);
        }

        long finish = System.currentTimeMillis();

        return (finish - start);
    }

}
