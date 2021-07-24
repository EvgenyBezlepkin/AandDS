package ds.graph;

import ds.Data;

public class Vertex {

    Data data;

    public Vertex(Data data) {
        this.data = data;
    }

    public Integer printInt() {
        return  data.i ;
    }

    public String printString() {
        return  data.s ;
    }
}
