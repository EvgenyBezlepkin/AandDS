package ds.lists.linkedlist;

import java.util.HashSet;

public class LinkedList implements LinkedListInterface {

    private Node firstElem;
    private int size;
    private Node lastElem;


    public LinkedList() {
    }

    public Node getFirstElem() {
        return firstElem;
    }

    public Node getLastElem() {
        return lastElem;
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
            node.previous = cNode;
            cNode.next = node;
        }
        lastElem = node;
        size++;
    }

    public Node getNext(Node node) {
        Node node1 = findNode(node);
        if (node1 != null) {
            if (node1.next != null) {
                return node1.next;
            }
        }
        return null;
    }

    public Node getPrevious(Node node) {
        Node node1 = findNode(node);
        if (node1 != null) {
            if (node1.previous != null) {
                return node1.previous;
            }
        }
        return null;
    }

    public void printLinkedList() {
        checkCycle();
        if (isEmpty()) return;
        printLinkedList(firstElem);
    }

    private void printLinkedList(Node node) {
        System.out.println(node);
        if (node.next != null) {
            printLinkedList(node.next);
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
        if (position == 0) {
            insertFirst(node);
            return true;
        }
        Node cNode = getNodeByPosition(position);
        if (cNode == null) return false;

        Node next = cNode.next;
        cNode.next = node;
        node.previous = cNode;
        if (next != null) {
            node.next = next;
            next.previous = node;
        }
        if (size == position) {
            lastElem = node;
        }
        size++;
        return true;
    }

    private void insertFirst(Node node) {
        Node cNode = firstElem;
        firstElem = node;
        firstElem.next = cNode;
        cNode.previous = node;
        size++;
    }

    public Node findNode(int position) {
        checkCycle();
        return getNodeByPosition(position+1);
    }

    public Node findNode(Node node) {
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
        Node node1 = findNode(node);
        if (node1 != null) {
            if (node1.previous == null) {
                removeFirst();
                return node;
            }
            if (node1.next == null) {
                Node cNode = node1.previous;
                cNode.next = null;
                lastElem = cNode;
                return node;
            }
            Node previous = node1.previous;
            Node next = node1.next;
            previous.next = next;
            next.previous = previous;
            return node;
        }

        return null;
    }

    public void removeFirst() {
        checkCycle();
        Node next = firstElem.next;
        firstElem = next;
        next.previous = null;
    }

    public void removeLast() {

    }

    private void checkCycle() {
        if (isEmpty()) throw new LinkedListException("linked list is empty");
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