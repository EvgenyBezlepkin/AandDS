package ds.lists.linkedlist;

import java.util.HashSet;

public class LinkedList implements LinkedListInterface {

    private Node first;


    public LinkedList() {
    }

    public Node getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(Node node) {
        if (isEmpty()) {
            first = node;
        } else {
            Node cNode = first;
            while (cNode.next != null) {
                cNode = cNode.next;
            }
            cNode.next = node;
        }
    }

    public void traverse(Node node) {
        checkCycle();
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

    //TODO: do simplify
    public boolean insertByPosition(int position, Node node) {
        if (position < 0) return false;
        if (position == 0) {
            Node cNode = first;
            first = node;
            first.next = cNode;
            return true;
        }
        Node cNode;
        cNode = getNodeByPosition(position);
        if (cNode == null) {
            return false;
        }
        Node next = cNode.next;
        cNode.next = node;
        if (next != null) {
            node.next = next;
        }
        return true;
    }


    public Node getNode(int position){
        checkCycle();
        return getNodeByPosition(position+1);
    }

    public Node getNode(Node node) {
        checkCycle();
        Node cNode = first;
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

    private void checkCycle() {
        if (isEmpty()) return;
        if (hasCycle(first)) {
            throw new LinkedListException("linked list contains the circle");
        }
    }

    private Node getNodeByPosition(int position) {
        Node cNode = first;
        for (int i = 0; i < position - 1; i++) {
            if (cNode != null) {
                cNode = cNode.next;
            }
        }
        return cNode;
    }

}