package java_notes.concurrency;

import java.util.concurrent.Callable;

public class CallableEx {

    /*
    public interface Callable<V> {
        V call() throws Exception;
    }
    */

    public static void main(String[] args) {

        Callable<Integer> c = () -> 5;

    }

}
