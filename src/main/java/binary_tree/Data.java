package binary_tree;

/** This class stores data contains in the binary tree */

public class Data {

    String s;
    Integer i;

    public Data(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return "Datas{" + s + ' ' + i + '}';
    }
}
