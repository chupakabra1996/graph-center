package ru.kpfu.itis.utils;

import ru.kpfu.itis.graph.AdjacentMatrixGraph;
import ru.kpfu.itis.graph.GraphType;

import java.io.*;
import java.net.URL;
import java.util.Random;

public class GraphUtils {

    private final static double MIN_DENSE = 0.89;
    private final static double MAX_SPARSE = 0.40;
    private final static double MIN_SPARSE = 0.30;

    public final static double MAX_EDGE_WEIGHT = 126.6;


    /**
     * Writes the graph into file, serialize it
     * @param file - file
     * @param g - graph
     */
    public static void writeGraph(File file, AdjacentMatrixGraph g) {

        System.out.println("writing graph...");

        if (file != null && file.exists() && file.canWrite() && g != null) {

            System.out.println("path to file : " + file.getPath());

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


    /**
     * Reads the fraph from serialized file (*.ser, for example)
     * @param file - file to be read
     * @return graph
     */
    public static AdjacentMatrixGraph readGraph(File file) {

        System.out.println("reading graph...");

        if (file != null && file.exists() && file.canRead()) {

            try (BufferedInputStream bIn = new BufferedInputStream(new FileInputStream(file))) {

                try (ObjectInputStream ois = new ObjectInputStream(bIn)) {

                    return (AdjacentMatrixGraph) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * @param fileName - file name
     * @param g - some class to get the path from classLoader
     * @return file
     */
    public static File getFileFromResources(String fileName, AdjacentMatrixGraph g) {

        if (fileName == null) {
            return null;
        }

        URL urlPath = g.getClass().getClassLoader().getResource(fileName);

        if (urlPath != null) {
            return new File(urlPath.getPath());
        }

        return null;
    }


    /**
     * @param fileName - name of the file to be used
     * @return File from resource directory
     */
    public static File getFile(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("file is null");
        }

        return new File("src/resources/" + fileName);
    }


    /**
     * Generates dense of graph depending on graphType (DENSE or SPARSE)
     * @param seed - random seed
     * @param graphType -  DENSE / SPARSE
     * @return dense
     */
    public static double generateDense(long seed, GraphType graphType) {

        double result;

        Random random = new Random(seed);

        if (graphType == GraphType.DENSE) {
            //dense between MIN_DENSE and 1
            result = random.nextDouble() * (1 - MIN_DENSE) + MIN_DENSE;
        } else {
            //dense between MIN_SPARSE and MAX_SPARSE
            result = random.nextDouble() * (MAX_SPARSE - MIN_SPARSE) + MIN_SPARSE;
        }

        return result;
    }


    /**
     * Computes the density of graph
     * @param vertices - the graph capacity
     * @param edges - current edges count
     * @return current density of graph
     */
    public static double computeDense(int vertices, int edges) {
        return (2 * edges) / (double) (vertices * (vertices - 1));
    }

}
