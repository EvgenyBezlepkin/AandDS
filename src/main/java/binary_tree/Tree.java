package binary_tree;

import java.util.List;

public interface Tree {

    boolean insert(Node node);
    Node findNode(Node node);
    void traverse(Node node, BinaryTree.TraverseOrder tr);
    Node findMinNode();
    Node findMaxNode();
    boolean deleteNode(Node node);
    int heightTree(Node n);
    Node getParent(Node node);
    void print(Node node);
    boolean isLeftChild(Node node);
    boolean isRightChild(Node node);
    void printGivenLevel (Node root ,int level);
    List<Node> pathToNode(Node root, Node search);
    Node getLowestCommonAncestor(Node root, int v1, int v2);
    boolean checkBST(Node root);

}
