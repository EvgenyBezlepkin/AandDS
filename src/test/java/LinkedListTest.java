import ds.list.linkedlist.LinkedList;
import ds.list.linkedlist.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LinkedListTest {

    Node a = new Node(1, "A");
    Node b = new Node(2, "b");
    Node c = new Node(3, "c");
    Node d = new Node(4, "D");

    LinkedList ll = new LinkedList();


    @Test
    void insertByPosition() {
        ll.insert(a);
        ll.insert(b);
        ll.insert(c);

        assertEquals(ll.insertByPosition(2, d), true);
        assertEquals(ll.insertByPosition(5, d), false);

    }
}
