package ru.kpfu.itis.utils;

import ru.kpfu.itis.generators.SimpleGraphGenerator;
import ru.kpfu.itis.generators.GraphGenerator;
import ru.kpfu.itis.graph.Graph;

import java.io.*;
import java.net.URL;

public class GraphUtils {

    public static final double MAX_EDGE_WEIGHT = 126;

    /**
     * Generates a graph
     *
     * @param graphGenerator - Graph generator
     * @return graph
     */
    public static Graph generateGraph(GraphGenerator graphGenerator) {

        return graphGenerator.generateGraph();
    }

    public static void writeGraph(File file, Graph g) {
        if (file != null && file.exists() && file.canWrite() && g != null) {

            try (BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(file))) {

                try (ObjectOutputStream ous = new ObjectOutputStream(bOs)) {

                    ous.writeObject(g);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("Can't write to file!");
        }
    }


    public static Graph readGraph(File file) {

        if (file != null && file.exists() && file.canRead()) {

            try (BufferedInputStream bIn = new BufferedInputStream(new FileInputStream(file))) {

                try (ObjectInputStream ois = new ObjectInputStream(bIn)) {

                    return (Graph) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static File getFileFromResources(String fileName, Graph g) {

        if (fileName == null) {
            return null;
        }

        URL urlPath = g.getClass().getClassLoader().getResource(fileName);

        if (urlPath != null) {
            return new File(urlPath.getPath());
        }

        return null;
    }


    public static File getFile(String fileName){
        if (fileName == null){
            return null;
        }

        return new File("src/resources/"+fileName);
    }


    public static void main(String[] args) {

        GraphGenerator graphGenerator = new SimpleGraphGenerator(10, SimpleGraphGenerator.GraphType.SPARSE);
        Graph g = GraphUtils.generateGraph(graphGenerator);

        g.print();
//        writeGraph(getFile("graph100.ser"), g);


//        Graph g2 = readGraph(getFileFromResources("graph10.ser",g));
//
//        System.out.println(g2.getEdgeCount() + ',' + g2.getVertexCount() + ", " + g2.getCapacity());
    }

}
