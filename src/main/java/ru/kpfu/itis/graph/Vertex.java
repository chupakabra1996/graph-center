package ru.kpfu.itis.graph;

import java.io.Serializable;

public class Vertex implements Serializable {

    private int id;

    public Vertex(int id) {
        if (id < 0){
            throw new IllegalArgumentException("Vertex's id must be greater than -1.");
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return id == vertex.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}
