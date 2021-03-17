package ds.lists.linkedlist;

public interface LinkedListInterface {

    boolean isEmpty();
    void insert(Node node);
    void printLinkedList();
    boolean hasCycle(Node node);
    boolean insertByPosition(int position, Node node);
    Node findNode(int position);
    Node findNode(Node node);
    int size();
    Node deleteNode(Node node);

}
