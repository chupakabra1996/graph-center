package ru.kpfu.itis;

import ru.kpfu.itis.generators.GraphGenerator;
import ru.kpfu.itis.generators.TestGraphGenerator;
import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.GraphType;
import ru.kpfu.itis.utils.GraphUtils;

import java.io.File;

public class Main {


    private static File getFileToGraph(int capacity, GraphType graphType) {
        String extension = ".ser";
        String type = graphType == GraphType.DENSE ? "" : "_sparse";

        String fileName = "graph" + capacity + type + extension;
        return GraphUtils.getFile(fileName);
    }


    public static void main(String[] args) {

        GraphGenerator graphGenerator = new TestGraphGenerator(5000, GraphType.SPARSE);

        AdjacentMatrixGraph g = graphGenerator.generateGraph();

        GraphUtils.writeGraph(getFileToGraph(g.getCapacity(),GraphType.SPARSE),g);
    }
}
