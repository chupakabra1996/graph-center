package ru.kpfu.itis.utils.generator;

import ru.kpfu.itis.graph.Graph;

public class SimpleGraphGenerator implements GraphGenerator {

    private static final double DEFAULT_MAX_DENSITY = 0.7;
    private static final int DEFAULT_MAX_DATA_SIZE = 100;

    private int edgeCount;

    private RandomUtil randomUtil;


    public SimpleGraphGenerator() {
        edgeCount = 0;
        randomUtil = new RandomUtil(DEFAULT_MAX_DATA_SIZE);
    }

    public static void main(String[] args) {
        GraphGenerator g = new SimpleGraphGenerator();
        g.generateDenseGraph(5);
        System.out.println("++++++++++++++++++");
        g.generateDenseGraph(5);
        System.out.println("++++++++++++++++++");
        g.generateDenseGraph(5);
        System.out.println("++++++++++++++++++");
        g.generateDenseGraph(5);
    }

    private boolean isDensyGraph(int vertexCount) {
        double d = 2 * edgeCount / (vertexCount * (vertexCount - 1));
        return Double.compare(d, DEFAULT_MAX_DENSITY) > 0;
    }

    @Override
    public Graph generateDenseGraph(int vertexCount) {
        int[][] matrix = new int[vertexCount][vertexCount];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {

                matrix[i][j] = randomUtil.nextInt();
                matrix[j][i] = matrix[i][j];

                if (matrix[i][j] != 0) {
                    edgeCount++;
                }
            }
        }


        for (int[] i : matrix) {
            for (int k : i) {
                System.out.print(k + ", ");
            }
            System.out.println();
        }
        System.out.println(edgeCount);
        edgeCount = 0;
        return null;
    }

    @Override
    public Graph generateSparseGraph(int vertexCount) {
        return null;
    }
}
