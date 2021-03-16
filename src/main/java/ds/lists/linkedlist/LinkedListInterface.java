package ds.lists.linkedlist;

public interface LinkedListInterface {

    boolean isEmpty();
    void insert(Node node);
    void traverse();
    boolean hasCycle(Node node);
    boolean insertByPosition(int position, Node node);
    Node getNode(int position);
    Node getNode(Node node);
    int size();
    Node deleteNode(Node node);

}
