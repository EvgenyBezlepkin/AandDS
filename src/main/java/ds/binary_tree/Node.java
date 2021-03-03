package ds.binary_tree;

import ds.Data;

import java.util.Objects;

/** This class presents node of the binary tree */
public class Node {

    private Data data;
    private Node leftChild;
    private Node rightChild;


    public Node() {
        leftChild = null;
        rightChild = null;
    }

    public Node(int i, String s) {
        this();
        this.data = new Data(i, s);
    }


    public Object getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public String nodeInfo() {
        return this.data.toString();
    }

    public String stringInfo() {
        return this.data.s;
    }

    public Integer intInfo() {
        return this.data.i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
