package ru.kpfu.itis;

import ru.kpfu.itis.generators.GraphGenerator;
import ru.kpfu.itis.generators.SimpleGraphGenerator;
import ru.kpfu.itis.graph.AdjacentMatrixGraph;

public class Main {

    public static void main(String[] args) {


        GraphGenerator graphGenerator = new SimpleGraphGenerator(5, SimpleGraphGenerator.GraphType.SPARSE);

        AdjacentMatrixGraph g = graphGenerator.generateGraph();

//        GraphUtils.writeGraph(GraphUtils.getFile("graph10_sparse.ser"),g);

        g.print();

//        //считываем граф из файла sparse и смотрим инфу о нем
//        AdjacentMatrixGraph graph = GraphUtils.readGraph(GraphUtils.getFile("graph10_sparse.ser"));
//
//        System.out.println(graph.getEdgeCount());
//
//        graph.print();

    }
}
