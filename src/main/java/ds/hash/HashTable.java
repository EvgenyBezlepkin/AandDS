package ds.hash;

import ds.Data;

import java.util.ArrayList;
import java.util.stream.Stream;


public class HashTable {

    private ArrayList<ArrayList<Data>> table;
    int countNodesTable;


    public HashTable() {
        table = new ArrayList<>();
        countNodesTable = 10;
        for (int i = 0; i < countNodesTable; i++) {
            table.add(new ArrayList<>());
        }
    }

    public HashTable(int countNodesTable) {
        table = new ArrayList<>();
        this.countNodesTable = countNodesTable;
    }

    public boolean insert(int i) {
        return insert(i, "");
    }


    public boolean insert(int i, String s) {
        Data d = new Data(i, s);
        int pos = d.hashCode() % countNodesTable;
        ArrayList<Data> currentNode = table.get(pos);
        if (!currentNode.isEmpty()) {
            for (Data n : currentNode) {
                if (n.equals(d)) {
                    return false;
                }
            }
        }
        currentNode.add(d);
        return true;
    }


    public void printHasTable() {
        int pos = 0;
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Data> node: table) {
            if (node.isEmpty()) sb.append(pos + ": ");
            for (Data d : node) {
                sb.append(d).append(", ");
            }
            System.out.println(sb);
            sb = new StringBuilder();
        }
    }

    public Data find(int i) {
        return find(i, "");
    }

    public Data find(int i, String s) {
        Data d = new Data(i, s);
        int pos = d.hashCode() % countNodesTable;
        ArrayList<Data> dataNode = table.get(pos);
        if (!dataNode.isEmpty()) {
            for (Data dd : dataNode) {
                if (dd.equals(d)) {
                    return dd;
                }
            }
        }
        return null;
    }

}
