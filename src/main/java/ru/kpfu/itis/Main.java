package ru.kpfu.itis;

import ru.kpfu.itis.generators.GraphGenerator;
import ru.kpfu.itis.generators.SimpleGraphGenerator;
import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.utils.GraphUtils;

public class Main {

    public static void main(String[] args) {

        //создаем генератор
        GraphGenerator graphGenerator = new SimpleGraphGenerator(10, SimpleGraphGenerator.GraphType.SPARSE);

        //создаем граф пока null
        AdjacentMatrixGraph graph = null;

        //генерация
        while (graph == null){
           //generate graph while it is not correct
           graph = graphGenerator.generateGraph();
        }

        //запись в файл
        GraphUtils.writeGraph(GraphUtils.getFile("graph10_sparse.ser"),graph);


        //обнуляем
        graph = null;
        //меняем тип графа
        graphGenerator.setGraphType(SimpleGraphGenerator.GraphType.DENSE);

        //генерируем
        while (graph == null){
            graph = graphGenerator.generateGraph();
        }

        //записываем в другой файлй
        GraphUtils.writeGraph(GraphUtils.getFile("graph10.ser"),graph);


        //как считывать графы
        graph = GraphUtils.readGraph(GraphUtils.getFile("graph10.ser"));

        //выводим какую-нибудь инфу о графе
        System.out.println(graph.getEdgeCount());

        //считываем граф из файла sparse и смотрим инфу о нем
        graph = GraphUtils.readGraph(GraphUtils.getFile("graph10_sparse.ser"));

        System.out.println(graph.getEdgeCount());

    }
}
