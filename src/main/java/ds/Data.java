package ds;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(s, data.s) &&
                Objects.equals(i, data.i);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, i);
    }
}
