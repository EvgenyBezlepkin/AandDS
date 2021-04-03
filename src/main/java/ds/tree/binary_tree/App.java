package ds.tree.binary_tree;

public class App {

    public static void main(String[] args) {

        BinaryTree t = new BinaryTree(BinaryTree.NodeRepresentation.INT);

        t.insert(9, "A");
        t.insert(5, "B");
        t.insert(15, "C");
        t.insert(3, "D");
        t.insert(7, "E");
        t.insert(1, "F");
        t.insert(4, "G");
        t.insert(6, "H");
        t.insert(8, "G");
        t.insert(12, "G");
        t.insert(18, "G");
        t.insert(11, "G");
        t.insert(13, "G");
        t.insert(16, "G");
        t.insert(20, "G");
        //t.insert(17, "G");

        t.print(t.getRoot());

        System.out.println(t.checkBST(t.getRoot()));

    }
}
