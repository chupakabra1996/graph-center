package ru.kpfu.itis;

import ru.kpfu.itis.generators.GraphGenerator;
import ru.kpfu.itis.generators.SimpleGraphGenerator;
import ru.kpfu.itis.graph.Graph;
import ru.kpfu.itis.utils.GraphUtils;

public class Main {

    public static void main(String[] args) {

        GraphGenerator graphGenerator = new SimpleGraphGenerator(10, SimpleGraphGenerator.GraphType.SPARSE);

        Graph graph = null;

        while (graph == null){
           //generate graph while it is not correct
           graph = graphGenerator.generateGraph();
        }

        GraphUtils.writeGraph(GraphUtils.getFile("graph10_sparse.ser"),graph);

        graph = null;
        graphGenerator.setGraphType(SimpleGraphGenerator.GraphType.DENSE);

        while (graph == null){
            graph = graphGenerator.generateGraph();
        }

        GraphUtils.writeGraph(GraphUtils.getFile("graph10.ser"),graph);


        //как считывать графы

        graph = GraphUtils.readGraph(GraphUtils.getFile("graph10.ser"));

        System.out.println(graph.getEdgeCount());

    }
}
