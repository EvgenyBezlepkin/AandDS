package graph;

public class App {

    public static void main(String[] args) {

        Graph g = new Graph();

        Vertex a = g.addVertex("a");
        Vertex b = g.addVertex("b");
        Vertex c = g.addVertex("c");
        Vertex d = g.addVertex("d");
        Vertex e = g.addVertex("e");
        Vertex f = g.addVertex("f");

        g.addEdgeInMatrix(a, b);
        g.addEdgeInMatrix(a, c);
        g.addEdgeInMatrix(b, d);
        g.addEdgeInMatrix(b, e);
        g.addEdgeInMatrix(f, e);

        g.addEdgeInList(a, b, c);
        g.addEdgeInList(b, a, d, e);
        g.addEdgeInList(c, a);
        g.addEdgeInList(d, b);
        g.addEdgeInList(e, b, f);
        g.addEdgeInList(f, e);

        g.printAdjacencyMatrix();
        System.out.println();
        g.printAdjacencyList();
        //System.out.println();
        //g.dfs(a, Graph.AdjacencyType.LIST);
    }
}
