package ds;

/** This class stores data contains in the binary tree */

public class Data {

    public String s;
    public Integer i;

    public Data(int i, String s) {
        this.i = i;
        this.s = s;
    }

    public Data(Integer i) {
        this.i = i;
    }

    public Data(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "{" + s + ' ' + i + '}';
    }
}
