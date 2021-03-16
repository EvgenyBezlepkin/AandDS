package ds.lists.linkedlist;

public class App {

    public static void main(String[] args) {

        Node a = new Node(1, "A");
        Node b = new Node(2, "b");
        Node c = new Node(3, "c");
        Node d = new Node(4, "D");

        LinkedList ll = new LinkedList();
        ll.insert(a);
        ll.insert(b);
        ll.insert(c);
        //ll.insert(b);
        //System.out.println(ll.hasCycle(ll.getFirst()));
        ll.insertByPosition(1, d);
        //ll.traverse(ll.getFirst());
        //System.out.println(ll.getNode(5));
        //ll.getNode(b);
        //System.out.println(ll.size());
        ll.deleteNode(b);
        ll.traverse();
    }
}
