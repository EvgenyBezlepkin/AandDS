package ds.graph;

import java.util.*;

public class Graph {

    public enum AdjacencyType {
        MATRIX, LIST
    }


    // the number vertexes of the ds.graph
    private int MAX_VERTEX = 20;
    // the array of vertexes
    private Vertex[] vertexList;
    // adjacency matrix
    private int[][] adjMat;
    // adjacency list
    private Map<Vertex, List<Vertex>> adjLists;
    // the current number of vertexes
    private int nVertexes;
    // helper
    ArrayDeque<Vertex> ad = new ArrayDeque<>();


    public Graph() {
        vertexList = new Vertex[MAX_VERTEX];
        //TODO: cut in half
        adjMat = new int[MAX_VERTEX][MAX_VERTEX];
        adjLists = new HashMap<>();
        nVertexes = 0;
        bruteForce(0);
    }


    Vertex addVertex(String s) {
        Vertex v = new Vertex(s);
        vertexList[nVertexes++] = v;
        return v;
    }

    boolean addEdgeInMatrix(Vertex start, Vertex end) {
        int startI = -1;
        int endI = -1;
        for (int i = 0; i < nVertexes; i++) {
            if (vertexList[i].label.equalsIgnoreCase(start.label)) {
                startI = i;
            }
            if (vertexList[i].label.equalsIgnoreCase(end.label)) {
                endI = i;
            }
        }
        if (startI == -1 || endI == -1) {
            return false;
        }
        adjMat[startI][endI] = 1;
        adjMat[endI][startI] = 1;
        return true;
    }

    void addEdgeInList(Vertex v, Vertex ...linked) {
        ArrayList<Vertex> list = new ArrayList<>();
        list.addAll(Arrays.asList(linked));
        boolean b = adjLists.containsKey(v);
        if (b) {
            adjLists.get(v).addAll(Arrays.asList(linked));
        } else {
            adjLists.putIfAbsent(v, list);
        }
    }

    void printAdjacencyMatrix() {
        for (int i = 0; i < nVertexes; i++) {
            for (int j = 0; j < nVertexes; j++) {
                System.out.print(adjMat[i][j] + " ");
            }
            System.out.println();
        }
    }

    void printAdjacencyList() {
        adjLists.forEach((k, v) -> {
            System.out.print(k.label + " : ");
            v.forEach(x -> System.out.print(x.label + " -> "));
            System.out.println();
        });
    }

    void bruteForce(int k) {
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                adjMat[i][j] = k;
            }
        }
    }

    // Depth-first search
    // 1 visit adjacency vertex not visited early, mark it and push to stack
    // 2 if the first rule is impossible, receive vertex from the stack
    // 3 if the first and the second rules are impossible, the traverse is finished
    void dfs(Vertex v, AdjacencyType type) {

        switch (type) {
            case MATRIX: {

                break;
            }
            case LIST: {

                break;
            }
        }


    }
}
