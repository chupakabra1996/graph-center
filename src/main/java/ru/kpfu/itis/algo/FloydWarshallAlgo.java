package ru.kpfu.itis.algo;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public class FloydWarshallAlgo implements Algorithm {

    private double[][] d;

    public FloydWarshallAlgo(AdjacentMatrixGraph graph) {
        d = graph.getAdjacentMatrix();
    }

    @Override
    public int getGraphCenter(AdjacentMatrixGraph graph) {

        int n = d.length;

        for (int k = 0; k < n; ++k) {

            for (int i = 0; i < n; ++i) {

                for (int j = 0; j < n; ++j) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        return getIndexOfMinValueAmongMaxValues(d);
    }


    private int getIndexOfMinValueAmongMaxValues(double[][] values) {

        int vertex = 0;
        double minPathCost = Double.POSITIVE_INFINITY;

        for (int i = 0; i < values.length; i++) {
            double rowMax = getMaxValue(values[i]);
            if (Double.compare(minPathCost, rowMax) > 0) {
                minPathCost = rowMax;
                vertex = i;
            }
        }

        return vertex;
    }


    private double getMaxValue(double[] values) {
        double res = -1d;

        for (double value : values) {
            res = Math.max(res, value);
        }

        return res;
    }
}
