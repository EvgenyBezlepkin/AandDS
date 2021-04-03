package ds.list.linkedlist;

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

        ll.insertByPosition(3, d);

        ll.deleteNode(d);
        //System.out.println(d.previous);
        //System.out.println(d.next);
        ll.printLinkedList();
        //System.out.println(ll.getLastElem());
    }
}
