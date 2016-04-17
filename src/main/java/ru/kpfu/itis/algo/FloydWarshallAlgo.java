package ru.kpfu.itis.algo;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public class FloydWarshallAlgo implements Algorithm {

    private Double[][] d;

    public FloydWarshallAlgo(AdjacentMatrixGraph graph) {
        d = graph.getAdjacentMatrix();
    }

    @Override
    public Integer getGraphCenter(AdjacentMatrixGraph graph) {

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


    private Integer getIndexOfMinValueAmongMaxValues(Double[][] values) {

        Integer vertex = 0;
        Double minPathCost = Double.POSITIVE_INFINITY;

        for (int i = 0; i < values.length; i++) {
            Double rowMax = getMaxValue(values[i]);
            if (Double.compare(minPathCost, rowMax) > 0) {
                minPathCost = rowMax;
                vertex = i;
            }
        }

        return vertex;
    }


    private Double getMaxValue(Double[] values) {
        Double res = -1d;

        for (Double value : values) {
            res = Math.max(res, value);
        }

        return res;
    }
}
