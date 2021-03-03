package ds.graph;

public class Vertex {

    String label;
    boolean wasVisited;

    public Vertex(String label) {
        this.label = label;
        this.wasVisited = false;
    }
}
