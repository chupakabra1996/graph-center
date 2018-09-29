package ru.kpfu.itis;

import ru.kpfu.itis.generators.TestGraphGenerator;
import ru.kpfu.itis.graph.GraphType;
import ru.kpfu.itis.utils.GraphUtils;

import java.io.File;

public class Main {


    private static File graphToFile(int capacity, GraphType graphType) {
        String extension = ".ser";
        String type = graphType == GraphType.DENSE ? "" : "_sparse";

        String fileName = "graph" + capacity + type + extension;
        return GraphUtils.getFile(fileName);
    }


    public static void main(String[] args) {


        TestGraphGenerator graphGenerator = new TestGraphGenerator(1500, GraphType.DENSE);

        long sTime = System.currentTimeMillis();

        graphGenerator.generateGraph();

        long fTime = System.currentTimeMillis();

        System.out.println((fTime - sTime) / 1000);

    }
}
