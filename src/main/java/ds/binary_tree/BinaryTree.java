package ds.binary_tree;

import java.util.*;

public class BinaryTree implements Tree {

    public enum NodeRepresentation {
        STRING, INT
    }

    public enum TraverseOrder {
        PREORDER, INORDER, POSTORDER
    }


    /** shows tree level */
    private int treeLevel = 0;
    /** contains the string representation of each tree level */
    private ArrayList<String> treeLevelsList = new ArrayList<>();
    /** metainformation about the node that must set at the start of the program */
    private NodeRepresentation nodeRepresentation;
    /** tree root */
    private Node root;
    /** it contains nodes that are met on the path from the root to the pointed node */
    private ArrayList<Node> pathNodeList = new ArrayList<>();


    public BinaryTree() {
        nodeRepresentation = NodeRepresentation.STRING;
        root = null;
    }

    public BinaryTree(NodeRepresentation nodeRepresentation) {
        this();
        this.nodeRepresentation = nodeRepresentation;
    }


    public ArrayList<String> getTreeLevelsList() {
        return treeLevelsList;
    }

    public Node getRoot() {
        return root;
    }

    public boolean insert(Node node) {
        return insert(node.intInfo(), node.stringInfo());
    }

    /**
     *
     * @param i
     * @param s
     * @return boolean
     */
    public boolean insert(int i, String s) {
        Node node = new Node(i, s);
        if (root == null) {
            root = node;
            return true;
        } else {
            Node currentNode = root;
            while (true) {
                if (currentNode.intInfo() > i) {
                    if (currentNode.getLeftChild() == null) {
                        currentNode.setLeftChild(node);
                        return true;
                    } else {
                        currentNode = currentNode.getLeftChild();
                    }
                } else if (currentNode.intInfo() < i) {
                    if (currentNode.getRightChild() == null) {
                        currentNode.setRightChild(node);
                        return true;
                    } else {
                        currentNode = currentNode.getRightChild();
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public Node findNode(Node node) {
        Node currentNode = root;
        while (true) {
            if (currentNode.intInfo().equals(node.intInfo())) {
                return currentNode;
            } else if (currentNode.intInfo() > node.intInfo()) {
                if (currentNode.getLeftChild() == null) {
                    return null;
                } else {
                    currentNode = currentNode.getLeftChild();
                }
            } else {
                if (currentNode.getRightChild() == null) {
                    return null;
                } else {
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }

    public void traverse(Node node, TraverseOrder tr) {
        if (node != null) {
            switch (tr) {
                case PREORDER:
                    traverse(node.getLeftChild(), TraverseOrder.PREORDER);
                    System.out.print(getNodeRepresentation(node) + " - ");
                    traverse(node.getRightChild(), TraverseOrder.PREORDER);
                    break;
                case INORDER:
                    System.out.print(getNodeRepresentation(node) + " - ");
                    traverse(node.getLeftChild(), TraverseOrder.INORDER);
                    traverse(node.getRightChild(), TraverseOrder.INORDER);
                    break;
                case POSTORDER:
                    traverse(node.getLeftChild(), TraverseOrder.POSTORDER);
                    traverse(node.getRightChild(), TraverseOrder.POSTORDER);
                    System.out.print(getNodeRepresentation(node) + " - ");
                    break;
            }
        }
    }

    public Node findMinNode() {
        Node currentNode = root;
        while (true) {
            if (currentNode.getLeftChild() == null) {
                return currentNode;
            } else {
                currentNode = currentNode.getLeftChild();
            }
        }
    }

    public Node findMaxNode() {
        Node currentNode = root;
        while (true) {
            if (currentNode.getRightChild() == null) {
                return currentNode;
            } else {
                currentNode = currentNode.getRightChild();
            }
        }
    }

    public boolean deleteNode(Node node) {

        Node deletedNode = findNode(node);

        if (deletedNode == null) {
            return false;
        } else {
            Node parent = getParent(deletedNode);

            // node hasn't children nodes
            if (deletedNode.getLeftChild() == null && deletedNode.getRightChild() == null) {
                if (parent.intInfo() > deletedNode.intInfo()) {
                    parent.setLeftChild(null);
                } else {
                    parent.setRightChild(null);
                }
                return true;
            }
            // node has one child
            else if (deletedNode.getLeftChild() == null && deletedNode.getRightChild() != null) {
                if (parent.intInfo() > deletedNode.intInfo()) {
                    parent.setLeftChild(deletedNode.getRightChild());
                } else {
                    parent.setRightChild(deletedNode.getRightChild());
                }
            } else if (deletedNode.getLeftChild() != null && deletedNode.getRightChild() == null) {
                if (parent.intInfo() > deletedNode.intInfo()) {
                    parent.setLeftChild(deletedNode.getLeftChild());
                } else {
                    parent.setRightChild(deletedNode.getLeftChild());
                }
            } else {
                // node has two children
                Node successor = getSuccessor(node);
                if (successor != null) {
                    // the successor is among of the right successors
                    if (isRightChild(successor)) {
                        if (parent.intInfo() > deletedNode.intInfo()) {
                            parent.setLeftChild(successor);
                        } else {
                            parent.setRightChild(successor);
                        }
                        Node leftBranch = deletedNode.getLeftChild();
                        successor.setLeftChild(leftBranch);
                    }
                    // the successor is among of the left successors
                    else {
                        Node successorParent = getParent(successor);
                        successorParent.setLeftChild(successor.getRightChild());
                        successor.setRightChild(deletedNode.getRightChild());
                        successor.setLeftChild(deletedNode.getLeftChild());
                        parent.setRightChild(successor);
                    }
                }
            }
        }
        return false;
    }

    public int heightTree(Node n) {
        if (n == null) {
            return -1;
        }
        return 1 +
                Math.max(heightTree(n.getLeftChild()), heightTree(n.getRightChild()));
    }

    public Node getParent(Node node) {
        Node currentNode = root;
        Node parent = currentNode;
        while (true) {
            if (currentNode.intInfo().equals(node.intInfo())) {
                return parent;
            } else if (currentNode.intInfo() > node.intInfo()) {
                if (currentNode.getLeftChild() == null) {
                    return null;
                } else {
                    parent = currentNode;
                    currentNode = currentNode.getLeftChild();
                }
            } else {
                if (currentNode.getRightChild() == null) {
                    return null;
                } else {
                    parent = currentNode;
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }

    //TODO: improve print method
    private void printTree(Node node) {
        treeLevelsList.add("");
        if (node != null) {
            ++treeLevel;
            printTree(node.getLeftChild());
            printTree(node.getRightChild());
            treeLevelsList.set(treeLevel - 1, treeLevelsList.get(treeLevel - 1) + " " + getNodeRepresentation(node));
            treeLevel--;
        } else {
            treeLevelsList.set(treeLevel, treeLevelsList.get(treeLevel) + " " + "+");
        }
    }

    public void print(Node node) {
        printTree(node);
        treeLevelsList.removeAll(Arrays.asList("", null));
        treeLevelsList.remove(treeLevelsList.size() - 1);
        int k = 0;
        for (int i = treeLevelsList.size() - 1; i >= 0; i--) {
            k += 2;
            treeLevelsList.set(i, treeLevelsList.get(i).replace(" ", replace(k)));
        }
        treeLevelsList.forEach(System.out::println);
        treeLevelsList = new ArrayList<>();
        treeLevelsList.forEach(System.out::println);
    }


    private String replace(int i) {
        String result = "";
        for (int u = 0; u < i; u++) {
            result = result + "_";
        }
        return result;
    }

    private Object getNodeRepresentation(Node node) {
        if (nodeRepresentation.equals(NodeRepresentation.INT)) {
            return node.intInfo();
        }
        return node.stringInfo();
    }

    public boolean isLeftChild(Node node) {
        Node parent = getParent(node);
        if (parent == null) {
            return false;
        }
        if (parent.intInfo() > node.intInfo()) {
            return true;
        }
        return false;
    }

    public boolean isRightChild(Node node) {
        Node parent = getParent(node);
        if (parent == null) {
            return false;
        }
        if (parent.intInfo() < node.intInfo()) {
            return true;
        }
        return false;
    }

    private Node getSuccessor(Node node) {
        Node current = findNode(node);
        if (current == null) {
            return null;
        }
        if (current.getRightChild() == null) {
            return null;
        }
        current = current.getRightChild();
        while (true) {
            if (current.getLeftChild() == null) {
                return current;
            } else {
                current = current.getLeftChild();
            }
        }
    }

    public void printGivenLevel(Node root, int level) {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.intInfo() + " ");
        else if (level > 1) {
            printGivenLevel(root.getLeftChild(), level - 1);
            printGivenLevel(root.getRightChild(), level - 1);
        }
    }

    public Node getLowestCommonAncestor(Node root, int v1, int v2) {
        //Solution is based on the following thought: The value of a common ancestor has to always be between the two values in question.
        //Decide if you have to call recursively
        //Samller than both
        if(root.intInfo() < v1 && root.intInfo() < v2){
            return getLowestCommonAncestor(root.getRightChild(),v1,v2);
        }
        //Bigger than both
        if(root.intInfo() > v1 && root.intInfo() > v2){
            return getLowestCommonAncestor(root.getLeftChild(),v1,v2);
        }
        //Else solution already found
        return root;
    }


    public List<Node> pathToNode(Node root, Node search) {
        Node node = findNode(search);
        if (node == null) {
            return null;
        }
        pathNodeList.add(root);
        if (root.intInfo() == search.intInfo()) {
            return pathNodeList;
        } else {
            if (root.intInfo() > search.intInfo()) {
                if (root.getLeftChild() != null) {
                    pathToNode(root.getLeftChild(), search);
                }
            } else {
                if (root.getRightChild() != null) {
                    pathToNode(root.getRightChild(), search);
                }
            }
        }
        return pathNodeList;
    }


    private  int last_data =Integer.MIN_VALUE;
    public boolean checkBST(Node root) {
        if (root == null) return true;
        if (checkBST(root.getLeftChild())) return true;
        if (root.intInfo() <= last_data) return false;
        last_data = root.intInfo();
        if (checkBST(root.getRightChild())) return true;
        return true;
    }
}
