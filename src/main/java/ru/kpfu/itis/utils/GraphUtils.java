package ru.kpfu.itis.utils;

import ru.kpfu.itis.generators.DenseGraphGenerator;
import ru.kpfu.itis.generators.GraphGenerator;
import ru.kpfu.itis.graph.Graph;

import java.io.*;
import java.net.URL;

public class GraphUtils {


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

    public static File getFileFromResources(String fileName) {

        if (fileName == null) {
            return null;
        }

        URL urlPath = fileName.getClass().getClassLoader().getResource(fileName);

        if (urlPath != null) {
            return new File(urlPath.getFile());
        }

        return null;
    }


    public static void main(String[] args) {

        GraphGenerator graphGenerator = new DenseGraphGenerator(10);
        Graph g = GraphUtils.generateGraph(graphGenerator);

        writeGraph(getFileFromResources("graph10.ser"), g);

    }

}
