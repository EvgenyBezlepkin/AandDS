package ds.list.linkedlist;

import ds.Data;

public class Node {

    Data data;
    Node next;
    Node previous;

    public Node(int i, String s) {
        this.data = new Data(i, s);
    }

    public Node() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node"+ data ;
    }
}
