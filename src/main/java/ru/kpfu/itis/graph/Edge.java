package ru.kpfu.itis.graph;

import java.util.Comparator;

public class Edge {

    private Vertex firstVertex;

    private Vertex secondVertex;

    private Double weight;

    public Edge(int firstVertex, int secondVertex, Double weight) {
        if (weight == null || weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero.");
        }

        this.weight = weight;
        this.firstVertex = new Vertex(firstVertex);
        this.secondVertex = new Vertex(secondVertex);
    }

    public static Comparator<Edge> compareByWeight() {
        return (o1, o2) -> {

            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null || o2 == null) {
                return o1 == null ? -1 : 1;
            }

            return Double.compare(o1.weight, o2.weight);
        };
    }

    public boolean isInfinityWeight() {
        return Double.isInfinite(weight);
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public void setSecondVertex(Vertex secondVertex) {
        this.secondVertex = secondVertex;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return ((firstVertex.equals(edge.firstVertex) && secondVertex.equals(edge.secondVertex))
                || (firstVertex.equals(edge.secondVertex) && secondVertex.equals(edge.firstVertex)))
                && (weight != null ? weight.equals(edge.weight) : edge.weight == null);

    }

    @Override
    public int hashCode() {
        int result = firstVertex.hashCode();
        result = 31 * result + secondVertex.hashCode();
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "| firstVertex = " + firstVertex.toString() + ", secondVertex = " + secondVertex.toString()
                + ", weight = " + weight + " |";
    }
}
