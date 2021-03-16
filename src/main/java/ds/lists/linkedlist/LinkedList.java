package ds.lists.linkedlist;

import java.util.HashSet;

public class LinkedList implements LinkedListInterface {

    private Node firstElem;
    private int size;


    public LinkedList() {
    }

    public Node getFirstElem() {
        return firstElem;
    }

    public boolean isEmpty() {
        return firstElem == null;
    }

    public int size() {
        return size;
    }

    public void insert(Node node) {
        if (isEmpty()) {
            firstElem = node;
        } else {
            Node cNode = firstElem;
            while (cNode.next != null) {
                cNode = cNode.next;
            }
            cNode.next = node;
        }
        size++;
    }

    public void traverse() {
        checkCycle();
        if (isEmpty()) return;
        traverse(firstElem);
    }

    public void traverse(Node node) {
        System.out.println(node);
        if (node.next != null) {
            traverse(node.next);
        }
    }

    public boolean hasCycle(Node node) {
        if (node == null) return false;
        HashSet<Node> nodesVisited = new HashSet<>();
        Node cNode = node;

        while (cNode != null) {
            if (nodesVisited.contains(cNode)) return true;
            nodesVisited.add(cNode);
            cNode = cNode.next;
        }
        return false;
    }

    public boolean insertByPosition(int position, Node node) {
        if (position < 0) return false;
        Node cNode = firstElem;
        if (position == 0) {
            insertInRoot(node, cNode);
            return true;
        }
        cNode = getNodeByPosition(position);
        if (cNode == null) {
            return false;
        }
        Node next = cNode.next;
        cNode.next = node;
        if (next != null) {
            node.next = next;
        }
        size++;
        return true;
    }

    private void insertInRoot(Node node, Node cNode) {
        firstElem = node;
        firstElem.next = cNode;
        size++;
    }

    public Node getNode(int position) {
        checkCycle();
        return getNodeByPosition(position+1);
    }

    public Node getNode(Node node) {
        checkCycle();
        Node cNode = firstElem;
        while (true) {
            if (cNode == null) {
                return null;
            }
            if (cNode.equals(node)) {
                return cNode;
            } else {
                cNode = cNode.next;
            }
        }
    }

    public Node deleteNode(Node node) {
        checkCycle();
        Node cNode = firstElem;
        Node previousNode = null;
        for (int i = 0; i < size; i++) {
            if (cNode != node) {
                previousNode = cNode;
                cNode = cNode.next;
            } else {
                if (previousNode == null) {
                    firstElem = null;
                } else {
                    previousNode.next = cNode.next;
                }
                return node;
            }
        }
        return null;
    }

    private void checkCycle() {
        if (isEmpty()) return;
        if (hasCycle(firstElem)) {
            throw new LinkedListException("linked list contains the circle");
        }
    }

    private Node getNodeByPosition(int position) {
        Node cNode = firstElem;
        for (int i = 0; i < position - 1; i++) {
            if (cNode != null) {
                cNode = cNode.next;
            } else return null;
        }
        return cNode;
    }

}