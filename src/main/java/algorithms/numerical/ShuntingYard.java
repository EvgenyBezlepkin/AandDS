package algorithms.numerical;

import java.util.ArrayDeque;

public class ShuntingYard {

    public static void main(String[] args) {

        new ShuntingYard().algorithm("4+(2*2)+2");
    }

    void algorithm(String s) {

        ArrayDeque<Double> numStack = new ArrayDeque<>();
        ArrayDeque<Double> opStack = new ArrayDeque<>();

        String[] split = s.split("*");
    }
}
