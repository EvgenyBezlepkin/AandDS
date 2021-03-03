package ds.tree234;

import ds.Data;

public class Node {
    private static final int NUM=4;
    private Node[] childArr = new Node[NUM];
    private Data[] itemArr = new Data[NUM-1];
    int numItems;
    Node parent;

    public Node() {
    }

    public Node(int i1, int i2, int i3, int i4) {
        this.itemArr[0] = new Data(i1);
        this.itemArr[2] = new Data(i2);
        this.itemArr[3] = new Data(i3);
        this.itemArr[4] = new Data(i4);
    }

    public Node[] getChildArr() {
        return childArr;
    }

    public void setChildArr(Node[] childArr) {
        this.childArr = childArr;
    }

    public Data[] getItemArr() {
        return itemArr;
    }

    public void setItemArr(Data[] itemArr) {
        this.itemArr = itemArr;
    }


    /** methods */

    public Node connectionChild(int childNum, Node child) {
        childArr[childNum] = child;
        if (child != null) {
            child.parent = this;
        }
        return child;
    }

    public Node disconnectChild(int childNum) {
        Node tmp = childArr[childNum];
        childArr[childNum] = null;
        return tmp;
    }

    public Node getChild(int childNum) {
        return childArr[childNum];
    }

    public boolean isLeaf() {
        return childArr[0] == null;
    }

    public Data getData(int i) {
        return itemArr[i];
    }

    public boolean isFull() {
        return numItems == NUM - 1;
    }

    /** auxiliary crud methods */

}
